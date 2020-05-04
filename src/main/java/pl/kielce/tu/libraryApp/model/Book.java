package pl.kielce.tu.libraryApp.model;

import pl.kielce.tu.libraryApp.model.enumeration.Genre;

import java.io.Serializable;

/**
 * @author ciepluchs
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String author;
    private Genre genre;
    private String isbn;
    private int quantity;

    public Book(String title, String author, Genre genre, String isbn, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
