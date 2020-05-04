package pl.kielce.tu.libraryApp.action.book;

import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.action.ActionHelper;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.util.ViewUtil;

import javax.swing.text.View;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ciepluchs
 */
public class SelectBookAction implements Action {

    private static final String HEADER = "########################################### SELECT BOOK #################################################";
    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.LIBRARIST, Role.READER, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Select book";
    private BookService bookService;
    private List<Action> subActions;
    private List<Book> books;

    public SelectBookAction(Config config) {
        subActions = Arrays.asList(new MakeReservationAction(config));
        bookService = config.getBookService();
    }

    @Override
    public void execute() {
        System.out.println(HEADER);
        System.out.println("Which book you want to select: ");
        int selectedBookIdx = ViewUtil.getSelectedNumber() - 1;
        Book selectedBook = books.get(selectedBookIdx);
        System.out.println("You have selected " + selectedBook.getTitle());
        List<Action> availableActions = ActionHelper.getAvailableActions(subActions);
        while (true) {
            ViewUtil.displaySubMenu(availableActions);
            String selectedOption = ViewUtil.getSelectedOption();
            if (ActionHelper.isBackOptionSelected(selectedOption)) {
                return;
            } else {
                Action selectedAction = ActionHelper.getSelectedAction(availableActions, selectedOption);
                if(selectedAction instanceof MakeReservationAction){
                    ((MakeReservationAction) selectedAction).setBook(selectedBook);
                    selectedAction.execute();
                }
            }
        }

    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public List<Role> getAllowedRoles() {
        return ALLOWED_ROLES;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
