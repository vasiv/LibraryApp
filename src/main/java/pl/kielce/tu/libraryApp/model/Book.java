package pl.kielce.tu.libraryApp.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import pl.kielce.tu.libraryApp.model.enumeration.Genre;

import java.io.Serializable;

/**
 * @author ciepluchs
 */
@DynamoDBTable(tableName = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
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

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "genre")
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @DynamoDBAttribute(attributeName = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @DynamoDBAttribute(attributeName = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
