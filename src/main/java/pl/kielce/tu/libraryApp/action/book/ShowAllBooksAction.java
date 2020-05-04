package pl.kielce.tu.libraryApp.action.book;

import org.apache.commons.lang3.StringUtils;
import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.BookService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ciepluchs
 */
public class ShowAllBooksAction implements Action {

    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.READER, Role.LIBRARIST, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Show all books";
    private BookService bookService;

    public ShowAllBooksAction(Config config) {
        bookService = config.getBookService();
    }

    @Override
    public void execute() {
        List<Book> allBooks = bookService.getAllBooks();
        String tableWithBooks = getTableWithBooks(allBooks);
        System.out.println(getHeader());
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

    @Override
    public List<Action> getSubActions() {
        return new ArrayList<>();
    }

    //TODO refactor
    private String getHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("################################################## BOOKS ##################################################\n");
        sb.append("-------------------------|".repeat(4) + "\n");
        sb.append("          TITLE          |");
        sb.append("          AUTHOR         |");
        sb.append("          GENRE          |");
        sb.append("          ISBN           |\n");
        sb.append("-------------------------|".repeat(4) + "\n");
        return sb.toString();
    }

    //TODO refactor
    private String getTableWithBooks(List<Book> books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            String title = book.getTitle();
            String author = book.getAuthor();
            String genre = book.getGenre().toString();
            String isbn = book.getIsbn();
            sb.append(title).append(" ".repeat(30 - title.length()));
            sb.append(author).append(" ".repeat(30 - author.length()));
            sb.append(genre).append(" ".repeat(30 - genre.length()));
            sb.append(isbn).append(" ".repeat(30 - isbn.length()));
        }
        return sb.toString();
    }
}
