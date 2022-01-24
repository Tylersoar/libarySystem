package com.company;

public class Books extends Main {
    protected static String title;
    protected static int isbn;
    protected static String author;
    protected static String genre;

    @Override
    public String toString() {
        return "title" + title +
                "isbn" + isbn +
                "author" + author +
                "genre" + genre;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        Books.title = title;
    }

    public static int getIsbn() {
        return isbn;
    }

    public static void setIsbn(int isbn) {
        Books.isbn = isbn;
    }

    public static String getAuthor() {
        return author;
    }

    public static void setAuthor(String author) {
        Books.author = author;
    }

    public static String getGenre() {
        return genre;
    }

    public static void setGenre(String genre) {
        Books.genre = genre;
    }
}
