package pl.kielce.tu.libraryApp.action.book;

import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.ReservationService;

import java.util.Arrays;
import java.util.List;

/**
 * @author ciepluchs
 */
public class MakeReservationAction implements Action {

    private static final String HEADER = "######################################## MAKE A RESERVATION ##############################################";
    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.READER, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Make a reservation";
    private ReservationService reservationService;
    private Book book;

    public MakeReservationAction(Config config) {
        reservationService = config.getReservationService();
    }

    @Override
    public void execute() {
        String operationResult = reservationService.makeReservation(book);
        System.out.println(operationResult);
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public List<Role> getAllowedRoles() {
        return ALLOWED_ROLES;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
