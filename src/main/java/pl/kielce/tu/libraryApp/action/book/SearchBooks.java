package pl.kielce.tu.libraryApp.action.book;

import org.apache.commons.lang3.StringUtils;
import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.action.ActionHelper;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.Book;
import pl.kielce.tu.libraryApp.model.enumeration.Genre;
import pl.kielce.tu.libraryApp.model.enumeration.Role;
import pl.kielce.tu.libraryApp.service.BookService;
import pl.kielce.tu.libraryApp.util.BookUtil;
import pl.kielce.tu.libraryApp.util.ViewUtil;

import java.util.*;

/**
 * @author ciepluchs
 */
public class SearchBooks implements Action {

    private static final String HEADER = "############################################## SEARCH ##############################################";
    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.READER, Role.LIBRARIST, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Search books";
    private static final String GENRE = "Genre";
    private Map<String, Object> searchCriteria;
    private BookService bookService;
    private List<Action> subActions;

    public SearchBooks(Config config) {
        bookService = config.getBookService();
        searchCriteria = new HashMap<>();
        searchCriteria.put("Title", StringUtils.EMPTY);
        searchCriteria.put("Author", StringUtils.EMPTY);
        searchCriteria.put("Genre", StringUtils.EMPTY);
        subActions = Arrays.asList(new SelectBookAction(config));
    }

    @Override
    public void execute() {
        System.out.println(HEADER);
        System.out.println("Fill search criteria: (if you don't want to use this criteria, press ENTER)");
        getSearchCriteriaFromUser();
        List<Book> foundBooks = bookService.getBooksBySearchCriteria(searchCriteria);
        String tableWithBooks = BookUtil.getTableWithBooks(foundBooks);
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
                    ((SelectBookAction) selectedAction).setBooks(foundBooks);
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

    private void getSearchCriteriaFromUser() {
        Scanner input = new Scanner(System.in);
        searchCriteria.forEach((key, value) -> {
            System.out.println(key + ": ");
            if(GENRE.equals(key)){
                Genre genre = ViewUtil.getGenreInput(input);
                searchCriteria.put(key, genre);
            }
            searchCriteria.put(key, input.nextLine());
        });
    }



}
