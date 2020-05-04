package pl.kielce.tu.libraryApp.model;

import pl.kielce.tu.libraryApp.indentity.RandomIdentityManager;
import pl.kielce.tu.libraryApp.model.enumeration.Genre;

import java.io.Serializable;

/**
 * @author ciepluchs
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String isbn;
    private String title;
    private String author;
    private Genre genre;

    public Book(String title, String author, Genre genre, String isbn) {
        id = RandomIdentityManager.generateNewId();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
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
}
