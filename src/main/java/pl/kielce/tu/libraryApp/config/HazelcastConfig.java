package pl.kielce.tu.libraryApp.config;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import pl.kielce.tu.libraryApp.repository.BookRepository;
import pl.kielce.tu.libraryApp.repository.hazelcast.HazelcastBookRepository;
import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.service.hazelcast.HazelcastBookService;

/**
 * @author ciepluchs
 */
public class HazelcastConfig implements Config {

    private static HazelcastInstance hazelcastInstance;
    private static BookService bookService;
    private static BookRepository bookRepository;

    public HazelcastConfig() {
        hazelcastInstance = Hazelcast.newHazelcastInstance();
        bookRepository = new HazelcastBookRepository(hazelcastInstance);
        bookService = new HazelcastBookService(bookRepository);
    }

    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    @Override
    public BookService getBookService() {
        return bookService;
    }
}
