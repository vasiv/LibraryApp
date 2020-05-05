package pl.kielce.tu.libraryApp.action.reservation;

import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.model.User;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.ReservationService;
import pl.kielce.tu.libraryApp.session.Session;
import pl.kielce.tu.libraryApp.util.ReservationUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ciepluchs
 */
public class ShowReservationsAction implements Action {

    private static final String HEADER = "#".repeat(126) + " RESERVATIONS " + "#".repeat(125);
    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.READER, Role.LIBRARIST, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "RESERVATIONS";
    private ReservationService reservationService;

    public ShowReservationsAction(Config config) {
        reservationService = config.getReservationService();
    }

    @Override
    public void execute() {
        System.out.println(HEADER);
        User user = Session.getUser();
        List<Reservation> reservations;
        if(user.getRoles().contains(Role.LIBRARIST) || user.getRoles().contains(Role.ADMINISTRATOR)){
            reservations = reservationService.getAllReservations();
        } else {
            reservations = reservationService.getReservationsByOwner(user);
        }
        String tableWithReservations = ReservationUtil.getTableWithReservations(reservations);
        System.out.println(tableWithReservations);
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public List<Role> getAllowedRoles() {
        return ALLOWED_ROLES;
    }
}
