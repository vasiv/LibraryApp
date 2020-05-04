package pl.kielce.tu.libraryApp.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author ciepluchs
 */
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int RESERVATION_PERIOD_IN_DAYS = 7;

    private User reservationOwner;
    private Book reservedBook;
    private ZonedDateTime creationDate;
    private ZonedDateTime reseravationDue;

    public Reservation(User reservationOwner, Book reservedBook) {
        this.reservationOwner = reservationOwner;
        this.reservedBook = reservedBook;
        creationDate = ZonedDateTime.now();
        reseravationDue = creationDate.plusDays(RESERVATION_PERIOD_IN_DAYS);
    }
}
