package pl.kielce.tu.libraryApp.service.hazelcast;

import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.model.User;
import pl.kielce.tu.libraryApp.repository.BookRepository;
import pl.kielce.tu.libraryApp.repository.ReservationRepository;
import pl.kielce.tu.libraryApp.service.ReservationService;
import pl.kielce.tu.libraryApp.session.Session;

import java.util.List;

/**
 * @author ciepluchs
 */
public class HazelcastReservationService implements ReservationService {

    private ReservationRepository reservationRepository;
    private BookRepository bookRepository;

    public HazelcastReservationService(ReservationRepository reservationRepository, BookRepository bookRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public String makeReservation(Book book) {
        int bookQuantityInLibrary = book.getQuantity();
        if(book.getQuantity() <= 0) {
            return "Cannot reserve book if it's unavailable in a library...";
        }
        User loggedUser = Session.getUser();
        Reservation reservation = new Reservation(loggedUser, book);
        reservationRepository.add(reservation);
        book.setQuantity(--bookQuantityInLibrary);
        bookRepository.update(book);
        return "Reservation created!";
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByOwner(User owner) {
        return reservationRepository.findByOwner(owner);
    }
}
