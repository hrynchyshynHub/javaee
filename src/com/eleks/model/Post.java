package com.eleks.model;

/**
 * Created by ivan.hrynchyshyn on 03.07.2017.
 */
public class Post {
    private String description;


    public Post() {
    }

    public Post(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
