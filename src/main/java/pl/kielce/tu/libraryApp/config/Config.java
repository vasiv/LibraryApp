package pl.kielce.tu.libraryApp.config;

import pl.kielce.tu.libraryApp.service.BookService;

public interface Config {

    BookService getBookService();
}
