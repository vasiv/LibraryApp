package pl.kielce.tu.libraryApp.repository.dynamoDb;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.repository.BookRepository;

import java.util.List;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class DynamoDbBookRepository implements BookRepository {

    private static final String BOOK = "book";
    private Table bookTable;

    public DynamoDbBookRepository(DynamoDB dynamoDb) {
        bookTable = dynamoDb.getTable(BOOK);
    }

    @Override
    public void add(Book book) {

    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findBy(Map<String, Object> searchCriteria) {
        return null;
    }

    @Override
    public void update(Book book) {

    }
}
