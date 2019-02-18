package com.javamaster.controller;

public class Message {

    private String message;
    private int id;

    public Message() {

    }

    public Message(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public Message(String str) {
        this.message = str;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
