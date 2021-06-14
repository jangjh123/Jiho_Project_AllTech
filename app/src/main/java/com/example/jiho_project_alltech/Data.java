package com.example.jiho_project_alltech;

public class Data {
    private String title, content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Data(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
