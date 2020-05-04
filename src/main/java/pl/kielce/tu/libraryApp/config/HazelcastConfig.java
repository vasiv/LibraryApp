package pl.kielce.tu.libraryApp.config;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import pl.kielce.tu.libraryApp.repository.BookRepository;
import pl.kielce.tu.libraryApp.repository.ReservationRepository;
import pl.kielce.tu.libraryApp.repository.hazelcast.HazelcastBookRepository;
import pl.kielce.tu.libraryApp.repository.hazelcast.HazelcastReservationRepository;
import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.service.ReservationService;
import pl.kielce.tu.libraryApp.service.hazelcast.HazelcastBookService;
import pl.kielce.tu.libraryApp.service.hazelcast.HazelcastReservationService;


/**
 * @author ciepluchs
 */
public class HazelcastConfig implements Config {

    private static HazelcastInstance hazelcastInstance;
    private static BookService bookService;
    private static ReservationService reservationService;

    public HazelcastConfig() {
        com.hazelcast.config.Config config = new com.hazelcast.config.Config();
        config.getProperties().setProperty("hazelcast.logging.type", "none");
        hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        HazelcastBookRepository bookRepository = new HazelcastBookRepository(hazelcastInstance);
        bookService = new HazelcastBookService(bookRepository);
        reservationService = new HazelcastReservationService(new HazelcastReservationRepository(hazelcastInstance), bookRepository);
    }

    @Override
    public BookService getBookService() {
        return bookService;
    }

    @Override
    public ReservationService getReservationService() {
        return reservationService;
    }
}
