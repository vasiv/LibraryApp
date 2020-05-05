package pl.kielce.tu.libraryApp.repository.dynamoDb;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.model.User;
import pl.kielce.tu.libraryApp.repository.ReservationRepository;

import java.util.List;

/**
 * @author ciepluchs
 */
public class DynamoDbReservationRepository implements ReservationRepository {

    private static final String RESERVATION = "reservation";
    private Table reservationTable;

    public DynamoDbReservationRepository(DynamoDB dynamoDB) {
        reservationTable = dynamoDB.getTable(RESERVATION);
    }

    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public void add(Reservation reservation) {

    }

    @Override
    public List<Reservation> findByOwner(User user) {
        return null;
    }
}
