package pl.kielce.tu.libraryApp.config.dynamoDb;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.repository.BookRepository;
import pl.kielce.tu.libraryApp.repository.ReservationRepository;
import pl.kielce.tu.libraryApp.repository.dynamoDb.DynamoDbBookRepository;
import pl.kielce.tu.libraryApp.repository.dynamoDb.DynamoDbReservationRepository;
import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.service.ReservationService;
import pl.kielce.tu.libraryApp.service.dynamoDb.DynamoDbBookService;
import pl.kielce.tu.libraryApp.service.dynamoDb.DynamoDbReservationService;

/**
 * @author ciepluchs
 */
public class DynamoDbConfig implements Config {

    private static final String US_WEST_2 = "us-west-2";
    private static final String SERVICE_ENDPOINT = "http://localhost:8000";
    private BookService bookService;
    private ReservationService reservationService;

    public DynamoDbConfig() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, US_WEST_2))
                .build();
        DynamoDB dynamoDb = new DynamoDB(client);
        ReservationRepository reservationRepository = new DynamoDbReservationRepository(dynamoDb);
        BookRepository bookRepository = new DynamoDbBookRepository(dynamoDb);
        bookService = new DynamoDbBookService(bookRepository);
        reservationService = new DynamoDbReservationService(reservationRepository, bookRepository);
    }

    @Override
    public BookService getBookService() {
        return bookService;
    }

    @Override
    public ReservationService getReservationService() {
        return reservationService;
    }
}
