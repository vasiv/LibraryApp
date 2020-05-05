package pl.kielce.tu.libraryApp.repository;

import com.hazelcast.query.Predicate;
import pl.kielce.tu.libraryApp.model.Book;

import java.util.List;
import java.util.Map;

public interface BookRepository {

    void add(Book book);

    List<Book> findAll();

    List<Book> findBy(Map<String, Object> searchCriteria);

    void update(Book book);
//    void delete(Persistable persistable);
//    List<Persistable> find();
}
