package com.challenge.ooo;

public class ResearchPaper {
    private String title;
    private String author;
    private int year;

    public ResearchPaper(String title, String author, int year) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title");
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String title()  { return title; }
    public String author() { return author; }
    public int year()      { return year; }

}
