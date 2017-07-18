package com.mkyong.domain.fto;

import java.util.Calendar;

/**
 * Created by mtoader on 7/16/2017.
 */
public class NoteFto {
    private String name;

    private String description;

    private Boolean done;

    private Calendar created;

    private String timeLimit;

    public NoteFto() {
    }

    public NoteFto(String name, String description, String timeLimit) {
        this.name = name;
        this.description = description;
        this.timeLimit = timeLimit;
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

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }
}
