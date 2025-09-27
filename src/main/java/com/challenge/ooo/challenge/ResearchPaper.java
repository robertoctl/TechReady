package com.challenge.ooo.challenge;

public class ResearchPaper {
    private String title;
    private String author;
    private int year;

    public ResearchPaper(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String title()  { return title; }
    public String author() { return author; }
    public int year()      { return year; }

}
