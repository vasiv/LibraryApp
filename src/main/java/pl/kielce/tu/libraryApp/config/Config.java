package pl.kielce.tu.libraryApp.config;

import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.service.ReservationService;

public interface Config {

    BookService getBookService();
    ReservationService getReservationService();
}
