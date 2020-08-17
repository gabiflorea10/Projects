package com.example.rehearsalapp;

public class User {

    private String name, email, type, bbid;

    public User() {
    }

    public User(String name, String email, String type, String bbid) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.bbid = bbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBbid() {
        return bbid;
    }

    public void setBbid(String bbid) {
        this.bbid = bbid;
    }
}
