package pl.kielce.tu.libraryApp.action.book;

import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Genre;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.BookService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author ciepluchs
 */
public class AddBookAction implements Action {

    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.LIBRARIST, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Add book";
    private BookService bookService;

    public AddBookAction(Config config) {
        bookService = config.getBookService();
    }

    @Override
    public void execute() {
        System.out.println(getHeader());
        Book bookToBeAdded = getBookToBeAdded();
        bookService.addNewBook(bookToBeAdded);
        System.out.println("Book added!");
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

    private Book getBookToBeAdded(){
        Scanner input = new Scanner(System.in);
        System.out.println("Title: ");
        String title = input.nextLine();
        System.out.println("Author: ");
        String author = input.nextLine();
        System.out.println("ISBN: ");
        String isbn = input.nextLine();
        System.out.println("Genre: (enter number)");
        Genre genre = getGenreInput(input);
        return new Book(title, author, genre, isbn);
    }

    private Genre getGenreInput(Scanner input){
        List<Genre> genres = Arrays.asList(Genre.values());
        for (int i = 0; i < genres.size(); i++) {
            System.out.println(i + 1 + ")" + genres.get(i));
        }
        int selectedGenre = input.nextInt();
        return genres.get(selectedGenre-1);
    }

    private String getHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("################################################ ADD BOOK ################################################\n");
        sb.append("Fill books information: ");
        return sb.toString();
    }
}
