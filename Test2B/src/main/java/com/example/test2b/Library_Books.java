package com.example.test2b;

public class Library_Books {

    private int BookID;

    private String Title;

    private String Author;

    private String Genre;

    public Library_Books(int BookID, String Title ,String Author, String Genre) {
        this.BookID = BookID;
        this.Title = Title;
        this.Author = Author;
        this.Genre = Genre;

    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }


    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }


    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }
}