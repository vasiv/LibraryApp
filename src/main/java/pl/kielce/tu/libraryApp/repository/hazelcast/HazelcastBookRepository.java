package pl.kielce.tu.libraryApp.repository.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ciepluchs
 */
public class HazelcastBookRepository implements BookRepository {

    private static final String BOOK = "book";
    private static final String BOOK_ID = "bookId";
    private static final String AUTHOR = "author";
    private IMap<Long, Book> map;
    private FlakeIdGenerator idGenerator;

    public HazelcastBookRepository(HazelcastInstance hazelcastInstance) {
        map = hazelcastInstance.getMap(BOOK);
        idGenerator = hazelcastInstance.getFlakeIdGenerator(BOOK_ID);
    }

    @Override
    public void add(Book book) {
        map.put(idGenerator.newId(), book);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Predicate<String, String> namePredicate = Predicates.equal(AUTHOR, author);
        return new ArrayList<>(map.values(Predicates.and(namePredicate)));
    }

}
