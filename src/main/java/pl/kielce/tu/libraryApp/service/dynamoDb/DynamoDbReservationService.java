package pl.kielce.tu.libraryApp.service.dynamoDb;

import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.model.User;
import pl.kielce.tu.libraryApp.repository.BookRepository;
import pl.kielce.tu.libraryApp.repository.ReservationRepository;
import pl.kielce.tu.libraryApp.service.ReservationService;

import java.util.List;

/**
 * @author ciepluchs
 */
public class DynamoDbReservationService implements ReservationService {

    private ReservationRepository reservationRepository;
    private BookRepository bookRepository;

    public DynamoDbReservationService(ReservationRepository reservationRepository, BookRepository bookRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public String makeReservation(Book book) {
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return null;
    }

    @Override
    public List<Reservation> getReservationsByOwner(User owner) {
        return null;
    }
}
