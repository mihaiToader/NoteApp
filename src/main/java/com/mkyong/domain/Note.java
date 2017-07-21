package com.mkyong.domain;

/**
 * Created by mtoader on 7/15/2017.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @NotNull
    private String name;

    private String description;

    private Boolean done;

    private Calendar created;

    private Calendar timeLimit;

    public Note() {
    }

    public Note(User owner, String name, String description, Boolean done, Calendar timeLimit) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.done = done;
        this.timeLimit = timeLimit;
        this.created = Calendar.getInstance();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Calendar timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
