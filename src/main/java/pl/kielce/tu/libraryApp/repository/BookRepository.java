package pl.kielce.tu.libraryApp.repository;

import pl.kielce.tu.libraryApp.model.Book;

import java.util.List;

public interface BookRepository {

    void add(Book book);
    List<Book> findAll();
//    void update(Persistable persistable);
//    void delete(Persistable persistable);
//    List<Persistable> find();
}
