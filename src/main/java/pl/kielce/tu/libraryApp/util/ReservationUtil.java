package pl.kielce.tu.libraryApp.util;

import org.apache.commons.lang3.StringUtils;
import pl.kielce.tu.libraryApp.model.Reservation;

import java.util.List;

/**
 * @author ciepluchs
 */
public abstract class ReservationUtil {

    private static final String NEXT_LINE = "\n";
    private static final String SPACE_DELIMITER = " ";
    private static final int TABLE_WIDTH = 264;
    private static final int COLUMN_WIDTH = 35;
    private static final int NUMBER_OF_COLUMNS = 8;
    private static final String DASH = "-";
    private static final String PIPE = "|";

    private ReservationUtil() {
    }

    //TODO refactor, fix numbering
    public static String getTableWithReservations(List<Reservation> reservations) {
        String tableHeader = getTableHeader();
        String tableContent = getTableContent(reservations);
        return tableHeader + "\n" + tableContent;
    }

    private static String getTableContent(List<Reservation> reservations) {
        String tableContent = StringUtils.EMPTY;
        for (Reservation reservation : reservations) {
            StringBuilder sb = new StringBuilder();
            String ownerLastName = reservation.getReservationOwner().getLastName();
            String ownerFirstName = reservation.getReservationOwner().getFirstName();
            String ownerPersonalId = reservation.getReservationOwner().getPersonalId();
            String bookTitle = reservation.getReservedBook().getTitle();
            String bookAuthor = reservation.getReservedBook().getAuthor();
            String bookIsbn = reservation.getReservedBook().getIsbn();
            String createdOn = reservation.getCreationDate().toLocalDate().toString();
            String reservationDue = reservation.getReservationDue().toLocalDate().toString();
            sb.append(reservations.indexOf(reservation)+1);
            sb.append(SPACE_DELIMITER.repeat(9 - sb.length())).append(ownerLastName);
            sb.append(SPACE_DELIMITER.repeat(COLUMN_WIDTH - ownerFirstName.length())).append(ownerFirstName);
            sb.append(SPACE_DELIMITER.repeat(COLUMN_WIDTH - ownerPersonalId.length())).append(ownerPersonalId);
            sb.append(SPACE_DELIMITER.repeat(COLUMN_WIDTH - bookTitle.length())).append(bookTitle);
            sb.append(SPACE_DELIMITER.repeat(COLUMN_WIDTH - bookAuthor.length())).append(bookAuthor);
            sb.append(SPACE_DELIMITER.repeat(COLUMN_WIDTH - bookIsbn.length())).append(bookIsbn);
            sb.append(SPACE_DELIMITER.repeat(COLUMN_WIDTH - createdOn.length())).append(createdOn);
            sb.append(SPACE_DELIMITER.repeat(COLUMN_WIDTH - reservationDue.length())).append(reservationDue);
            sb.append(NEXT_LINE);
            sb.append(DASH.repeat(TABLE_WIDTH)).append(PIPE);
            sb.append(NEXT_LINE);
            tableContent += sb.toString();
        }
        return tableContent;
    }

    private static String getTableHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------|");
        sb.append("-------------------------------|".repeat(NUMBER_OF_COLUMNS) + "\n");
        sb.append("   No.  |");
        sb.append("       OWNER LAST NAME         |");
        sb.append("       OWNER FIRST NAME        |");
        sb.append("      OWNER PERSONAL ID        |");
        sb.append("          BOOK TITLE           |");
        sb.append("          BOOK AUTHOR          |");
        sb.append("          BOOK ISBN            |");
        sb.append("    RESERVATION CREATED ON     |");
        sb.append("         RESERVATION DUE       |\n");
        sb.append("--------|");
        sb.append("-------------------------------|".repeat(NUMBER_OF_COLUMNS) + "\n");
        return sb.toString();
    }
}
