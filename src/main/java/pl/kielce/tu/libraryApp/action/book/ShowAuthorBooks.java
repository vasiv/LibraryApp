package pl.kielce.tu.libraryApp.action.book;

import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.util.BookUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author ciepluchs
 */
public class ShowAuthorBooks implements Action {

    private static final String HEADER = "############################################## AUTHOR BOOKS ##############################################";
    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.READER, Role.LIBRARIST, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Show author books";
    private BookService bookService;

    public ShowAuthorBooks(Config config) {
        bookService = config.getBookService();
    }

    @Override
    public void execute() {
        String author = getAuthorInputFromUser();
        List<Book> authorBooks = bookService.getAuthorBooks(author);
        String tableWithBooks = BookUtil.getTableWithBooks(authorBooks);
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

    private String getAuthorInputFromUser() {
        System.out.println("Author: ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

}
