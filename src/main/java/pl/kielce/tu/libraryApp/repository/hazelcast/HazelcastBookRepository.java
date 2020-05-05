package pl.kielce.tu.libraryApp.repository.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;
import org.apache.commons.lang3.StringUtils;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Genre;
import pl.kielce.tu.libraryApp.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class HazelcastBookRepository implements BookRepository {

    private static final String BOOK = "book";
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
    public List<Book> findBy(Map<String, Object> searchCriteria) {
        //TODO proof of concept
        List<Predicate> predicates = transformSearchCriteriaToPredicates(searchCriteria);
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

    private List<Predicate> transformSearchCriteriaToPredicates(Map<String, Object> searchCriteria) {
        List<Predicate> predicates = new ArrayList<>();
        searchCriteria.forEach((key, value) -> {
            if (value instanceof Genre) {
                Predicate<String, Genre> genrePredicate = Predicates.equal(key.toLowerCase(), (Genre) value);
                predicates.add(genrePredicate);
            } else if (value instanceof String && StringUtils.isNotEmpty((String) value)) {
                Predicate<String, String> predicate = Predicates.equal(key.toLowerCase(), (String) value);
                predicates.add(predicate);
            }
        });
        return predicates;
    }
}
