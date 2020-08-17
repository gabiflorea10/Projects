package com.example.rehearsalapp;

public class NotesItem {
    private String title;
    private String text;

    public NotesItem() {
    }

    public NotesItem(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
