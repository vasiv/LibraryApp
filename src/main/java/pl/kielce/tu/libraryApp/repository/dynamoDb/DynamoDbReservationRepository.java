package pl.kielce.tu.libraryApp.repository.dynamoDb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.model.User;
import pl.kielce.tu.libraryApp.repository.ReservationRepository;

import java.util.List;

/**
 * @author ciepluchs
 */
public class DynamoDbReservationRepository implements ReservationRepository {

    DynamoDBMapper dbMapper;

    public DynamoDbReservationRepository(AmazonDynamoDB client) {
        dbMapper = new DynamoDBMapper(client);
    }

    @Override
    public List<Reservation> findAll() {
        return dbMapper.scan(Reservation.class, new DynamoDBScanExpression());
    }

    @Override
    public void add(Reservation reservation) {
        dbMapper.save(reservation);
    }

    @Override
    public List<Reservation> findByOwner(User user) {
        return null;
    }
}
