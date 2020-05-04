package pl.kielce.tu.libraryApp.service;

import pl.kielce.tu.libraryApp.model.Book;

import java.util.List;

public interface BookService {

    void addNewBook(Book book);
    List<Book> getAllBooks();
    List<Book> getAuthorBooks(String author);

}
