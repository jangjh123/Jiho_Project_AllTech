package com.example.jiho_project_alltech;

public class Memo {
    private int id;

    public Memo(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    private String content;

    public Memo(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
