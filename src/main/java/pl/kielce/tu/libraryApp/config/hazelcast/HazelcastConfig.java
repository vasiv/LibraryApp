package pl.kielce.tu.libraryApp.config.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import pl.kielce.tu.libraryApp.config.Config;
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

    private BookService bookService;
    private ReservationService reservationService;

    public HazelcastConfig() {
        com.hazelcast.config.Config config = new com.hazelcast.config.Config();
        config.getProperties().setProperty("hazelcast.logging.type", "none");
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
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
