package com.mkyong.service;

import com.mkyong.Repository.RepositoryNote;
import com.mkyong.domain.Note;
import com.mkyong.domain.User;
import com.mkyong.service.iService.ServiceNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mtoader on 7/17/2017.
 */
@Service
public class ServiceNoteImpl implements ServiceNote{
    private final RepositoryNote repositoryNote;

    @Autowired
    public ServiceNoteImpl(RepositoryNote repositoryNote) {
        this.repositoryNote = repositoryNote;
    }


    @Override
    public void saveNote(Note n) {
        repositoryNote.save(n);
    }

    @Override
    public Iterable<Note> findByOwner(User u) {
        return repositoryNote.findByOwner(u);
    }

    @Override
    public void delete(Note u) {
        repositoryNote.delete(u);
    }

    @Override
    public Note findById(Long id) {
        return repositoryNote.findById(id);
    }

    @Override
    public Iterable<Note> findAll() {
        return repositoryNote.findAll();
    }
}
