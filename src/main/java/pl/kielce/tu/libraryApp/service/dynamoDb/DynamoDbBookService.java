package pl.kielce.tu.libraryApp.service.dynamoDb;

import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.repository.BookRepository;
import pl.kielce.tu.libraryApp.service.BookService;

import java.util.List;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class DynamoDbBookService implements BookService {

    private BookRepository bookRepository;

    public DynamoDbBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addNewBook(Book book) {

    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public List<Book> getBooksBySearchCriteria(Map<String, Object> searchCriteria) {
        return null;
    }
}
