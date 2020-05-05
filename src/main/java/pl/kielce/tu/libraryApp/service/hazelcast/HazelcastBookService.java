package pl.kielce.tu.libraryApp.service.hazelcast;

import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import org.apache.commons.lang3.StringUtils;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Genre;
import pl.kielce.tu.libraryApp.repository.BookRepository;
import pl.kielce.tu.libraryApp.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class HazelcastBookService implements BookService {

    private BookRepository bookRepository;

    public HazelcastBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addNewBook(Book book) {
        bookRepository.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksBySearchCriteria(Map<String, Object> searchCriteria) {
        return bookRepository.findBy(searchCriteria);
    }
}
