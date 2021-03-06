package pl.kielce.tu.libraryApp.action.book;

import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.action.ActionHelper;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.util.BookUtil;
import pl.kielce.tu.libraryApp.util.ViewUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author ciepluchs
 */
public class ShowAllBooksAction implements Action {

    private static final String HEADER = "############################################### ALL BOOKS #################################################";
    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.READER, Role.LIBRARIST, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Show all books";
    private BookService bookService;
    private List<Action> subActions;

    public ShowAllBooksAction(Config config) {
        subActions = Arrays.asList(new SelectBookAction(config));
        bookService = config.getBookService();
    }

    @Override
    public void execute() {
        List<Book> allBooks = bookService.getAllBooks();
        String tableWithBooks = BookUtil.getTableWithBooks(allBooks);
        System.out.println(HEADER);
        System.out.println(tableWithBooks);
        List<Action> availableActions = ActionHelper.getAvailableActions(subActions);
        while (true) {
            ViewUtil.displaySubMenu(availableActions);
            String selectedOption = ViewUtil.getSelectedOption();
            if (ActionHelper.isBackOptionSelected(selectedOption)) {
                return;
            } else {
                Action selectedAction = ActionHelper.getSelectedAction(availableActions, selectedOption);
                if(selectedAction instanceof SelectBookAction){
                    ((SelectBookAction) selectedAction).setBooks(allBooks);
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

}
