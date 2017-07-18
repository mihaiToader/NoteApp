package com.mkyong.Repository;

import com.mkyong.domain.Note;
import com.mkyong.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mtoader on 7/17/2017.
 */
public interface RepositoryNote extends CrudRepository<Note, Long> {
    public Iterable<Note> findByOwner(User owner);

    Note findById(Long id);
}
