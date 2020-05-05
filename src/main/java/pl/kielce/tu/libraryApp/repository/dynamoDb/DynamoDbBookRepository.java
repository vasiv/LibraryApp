package pl.kielce.tu.libraryApp.repository.dynamoDb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.repository.BookRepository;

import java.util.List;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class DynamoDbBookRepository implements BookRepository {

    DynamoDBMapper dbMapper;

    public DynamoDbBookRepository(AmazonDynamoDB client) {
        dbMapper = new DynamoDBMapper(client);
    }

    @Override
    public void add(Book book) {
        dbMapper.save(book);
    }

    @Override
    public List<Book> findAll() {
        return dbMapper.scan(Book.class, new DynamoDBScanExpression());
    }

    @Override
    public List<Book> findBy(Map<String, Object> searchCriteria) {
        return null;
    }

    @Override
    public void update(Book book) {
        dbMapper.save(book);
    }
}
