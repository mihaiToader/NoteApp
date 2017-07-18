package com.mkyong.service.iService;

import com.mkyong.domain.Note;
import com.mkyong.domain.User;

import java.util.List;

/**
 * Created by mtoader on 7/17/2017.
 */
public interface ServiceNote {
    void saveNote(Note n);

    Iterable<Note> findByOwner(User u);

    void delete(Note u);

    Note findById(Long id);

    Iterable<Note> findAll();
}
