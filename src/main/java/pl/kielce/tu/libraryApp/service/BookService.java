package pl.kielce.tu.libraryApp.service;

import pl.kielce.tu.libraryApp.model.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    void addNewBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBooksBySearchCriteria(Map<String, Object> searchCriteria);
}
