package pl.kielce.tu.libraryApp.repository;

import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.model.User;

import java.util.List;

public interface ReservationRepository {

    void add(Reservation reservation);
    List<Reservation> getUserReservations(User user);
}
