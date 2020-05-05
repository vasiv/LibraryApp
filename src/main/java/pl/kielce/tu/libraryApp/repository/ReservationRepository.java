package pl.kielce.tu.libraryApp.repository;

import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.model.User;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findAll();

    void add(Reservation reservation);

    List<Reservation> findByOwner(User user);
}
