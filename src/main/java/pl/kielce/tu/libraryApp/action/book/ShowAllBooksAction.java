package pl.kielce.tu.libraryApp.action.book;

import org.apache.commons.lang3.StringUtils;
import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.util.BookUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ciepluchs
 */
public class ShowAllBooksAction implements Action {

    private static final String HEADER = "################################################## BOOKS ##################################################";
    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.READER, Role.LIBRARIST, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Show all books";
    private BookService bookService;

    public ShowAllBooksAction(Config config) {
        bookService = config.getBookService();
    }

    @Override
    public void execute() {
        List<Book> allBooks = bookService.getAllBooks();
        String tableWithBooks = BookUtil.getTableWithBooks(allBooks);
        System.out.println(HEADER);
        System.out.println(tableWithBooks);
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public List<Role> getAllowedRoles() {
        return ALLOWED_ROLES;
    }

}
