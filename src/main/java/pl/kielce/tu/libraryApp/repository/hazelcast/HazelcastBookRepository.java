package pl.kielce.tu.libraryApp.repository.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
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
    private static final String AUTHOR = "author";
    private IMap<String, Book> map;

    public HazelcastBookRepository(HazelcastInstance hazelcastInstance) {
        map = hazelcastInstance.getMap(BOOK);
    }

    @Override
    public void add(Book book) {
        map.put(book.getIsbn(), book);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public List<Book> findBy(List<Predicate> predicates) {
        if (predicates.size() == 3) {
            return new ArrayList<>(map.values(Predicates.and(predicates.get(0), predicates.get(1), predicates.get(2))));
        } else if (predicates.size() == 2) {
            return new ArrayList<>(map.values(Predicates.and(predicates.get(0), predicates.get(1))));
        } else {
            return new ArrayList<>(map.values(Predicates.and(predicates.get(0))));
        }
    }

    @Override
    public void update(Book book) {
        map.put(book.getIsbn(), book);
    }
}
