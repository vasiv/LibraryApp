package pl.kielce.tu.libraryApp.service;

import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.model.User;

import java.util.List;

public interface ReservationService {

    String makeReservation(Book book);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByOwner(User owner);
}
