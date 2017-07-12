package com.eleks.model;

/**
 * Created by ivan.hrynchyshyn on 03.07.2017.
 */
public class Post {
    private int id;
    private String description;
    private int userId;

    public Post() {
    }
    public Post(String description) {
        this.description = description;
    }

    public Post(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
