package pl.kielce.tu.libraryApp.action.book;

import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.action.ActionHelper;
import pl.kielce.tu.libraryApp.config.Config;
import pl.kielce.tu.libraryApp.model.enumeration.Role;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author ciepluchs
 */
public class ShowBooksAction implements Action {

    private static final String HEADER = "################################################## BOOKS ##################################################";
    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.READER, Role.LIBRARIST, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Show books";
    private static List<Action> subActions;

    public ShowBooksAction(Config config) {
        subActions = Arrays.asList(new ShowAllBooksAction(config), new ShowAuthorBooks(config));
    }

    @Override
    public void execute() {
        List<Action> availableActions = ActionHelper.getAvailableActions(subActions);
        System.out.println(HEADER);
        while (true) {
            displayMenu(availableActions);
            String selectedOption = getSelectedOption();
            if (ActionHelper.isBackOptionSelected(selectedOption)) {
                return;
            } else {
                ActionHelper.getSelectedAciton(availableActions, selectedOption).execute();
            }
        }
    }

    private String getSelectedOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private void displayMenu(List<Action> availableActions) {
        int i = 0;
        for (Action action : availableActions) {
            System.out.println(++i + ") " + action.getDisplayName());
        }
        System.out.println("<-) back");
        System.out.println("What you want to do: ");
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
