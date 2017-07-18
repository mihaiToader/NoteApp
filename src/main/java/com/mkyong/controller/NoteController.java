package com.mkyong.controller;

import com.mkyong.domain.Note;
import com.mkyong.domain.User;
import com.mkyong.domain.fto.NoteFto;
import com.mkyong.service.iService.ServiceNote;
import com.mkyong.service.iService.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class NoteController {
    final private ServiceNote serviceNote;
    final private ServiceUser serviceUser;

    @Autowired
    public NoteController(ServiceNote serviceNote, ServiceUser serviceUser) {
        this.serviceNote = serviceNote;
        this.serviceUser = serviceUser;
    }

    @GetMapping("/user/addNote")
    public String addNote(Model model, Principal principal)
    {
        String name = principal.getName();
        model.addAttribute("user", serviceUser.findByName(principal.getName()));
        model.addAttribute("tNote", name);
        return "views/addNote";
    }

    @PostMapping("/user/addNoteSubmit")
    public String addNoteSubmit(@Valid NoteFto cFto, Model model, Principal principal)
    {
        String name = principal.getName();
        User user = serviceUser.findByName(name);
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            cal.setTime(sdf.parse(cFto.getTimeLimit()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Note n = new Note(user, cFto.getName(), cFto.getDescription(), false, cal);
        serviceNote.saveNote(n);
        model.addAttribute("user", user);
        model.addAttribute("tNote", name);
        model.addAttribute("noteCreated", true);
        return "views/addNote";
    }

    @GetMapping("/user")
    public String user(ModelMap model, Principal principal) {
        User u = serviceUser.findByName(principal.getName());
        ArrayList<Note> notes = new ArrayList<>();
        serviceNote.findByOwner(u).forEach(notes::add);
        notes.sort(Comparator.comparing(Note::getTimeLimit));
        model.addAttribute("user", u);
        model.addAttribute("notes", notes);
        return "views/user";
    }

    @GetMapping("/user/doneNote")
    public String userDoneNote(Long idNote, ModelMap model, Principal principal) {
        User u = serviceUser.findByName(principal.getName());
        serviceNote.delete(serviceNote.findById(idNote));
        model.addAttribute("user", u);
        model.addAttribute("notes", serviceNote.findByOwner(u));
        return "views/user";
    }

    @GetMapping("/admin/seeAllNotes")
    public String getAllNotes(ModelMap model, Principal principal) {
        User u = serviceUser.findByName(principal.getName());
        HashMap<User, ArrayList<Note>> notes = new HashMap<>();
        serviceNote.findAll().forEach(note -> {
            if (!notes.containsKey(note.getOwner())){
                notes.put(note.getOwner(), new ArrayList<>());
            }
            notes.get(note.getOwner()).add(note);
        });
        for (User user : notes.keySet()){
            notes.get(user).sort(Comparator.comparing(Note::getTimeLimit));
        }
        model.addAttribute("notes", notes);
        model.addAttribute("user", u);
        return "views/admin_see_all_notes";
    }
}
