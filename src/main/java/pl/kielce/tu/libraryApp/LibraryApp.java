package pl.kielce.tu.libraryApp;

import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.action.ActionHelper;
import pl.kielce.tu.libraryApp.action.book.ShowBooksMenuAction;
import pl.kielce.tu.libraryApp.action.reservation.ShowReservationsAction;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.config.dynamoDb.DynamoDbConfig;
import pl.kielce.tu.libraryApp.config.hazelcast.HazelcastConfig;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.User;
import pl.kielce.tu.libraryApp.model.enumeration.Genre;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.service.ReservationService;
import pl.kielce.tu.libraryApp.session.Session;
import pl.kielce.tu.libraryApp.util.PropertiesUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author ciepluchs
 */
public class LibraryApp {

    private static final String STORE = "store";
    private static final String DYNAMODB_STORE = "dynamodb";
    private static final String HAZELCAST_STORE = "hazelcast";

    public static void main(String[] args) {
        setUserInSession();
        String storeType = PropertiesUtil.getProperty(STORE);
        Config config;
        try {
            config = setConfig(storeType);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        List<Action> mainMenuActions = getMainMenuActions(config);
        List<Action> availableActions = ActionHelper.getAvailableActions(mainMenuActions);

        //FOR DEVELOPMENT/TESTING/DEMO
        initializeWithDemoData(config);

        while (true) {
            System.out.println("########## LIBRARY ##########");
            int i = 0;
            for (Action action : availableActions) {
                System.out.println(++i + ") " + action.getDisplayName());
            }
            System.out.println("Where you want go: ");
            Scanner scanner = new Scanner(System.in);
            int nextOperation = scanner.nextInt() - 1;
            availableActions.get(nextOperation).execute();

        }
    }

    private static Config setConfig(String storeType) throws Exception {
        if (DYNAMODB_STORE.equals(storeType)) {
            return new DynamoDbConfig();
        } else if (HAZELCAST_STORE.equals(storeType)) {
            return  new HazelcastConfig();
        } else {
            throw new Exception("Incorrect configuration!");
        }
    }

    private static void initializeWithDemoData(Config config) {
        List<Book> books = Arrays.asList(
                new Book("Pan Tadeusz", "Adam Mickiewicz", Genre.POETRY, "9781984061874", 4),
                new Book("Dziady", "Adam Mickiewicz", Genre.POETRY, "9788378876274", 2)
        );
        BookService bookService = config.getBookService();
        bookService.addNewBook(books.get(0));
        bookService.addNewBook(books.get(1));
        ReservationService reservationService = config.getReservationService();
        reservationService.makeReservation(books.get(0));
        reservationService.makeReservation(books.get(1));

    }

    private static List<Action> getMainMenuActions(Config hazelcastConfig) {
        return Arrays.asList(new ShowBooksMenuAction(hazelcastConfig), new ShowReservationsAction(hazelcastConfig));
    }

    private static void setUserInSession() {
        User admin =
                new User("Szymon", "Ciepluch", "12345678910", "pass", Role.READER);
        Session.setUser(admin);
    }


}
