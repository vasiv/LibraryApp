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

    private static final List<Role> ALLOWED_ROLES = Arrays.asList(Role.READER, Role.LIBRARIST, Role.ADMINISTRATOR);
    private static final String DISPLAY_NAME = "Show books";
    private static List<Action> subActions;
    private static Config config;

    public ShowBooksAction(Config config) {
        this.config = config;
        subActions = Arrays.asList(new ShowAllBooksAction(config));
    }

    @Override
    public void execute() {
        List<Action> availableActions = ActionHelper.getAvailableActions(subActions);
        while(true){
            displayMenu(availableActions);
            String selectedOption = getSelectedOption();
            if(ActionHelper.isBackOptionSelected(selectedOption)){
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
        System.out.println("################################################## BOOKS ##################################################\n");
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

    @Override
    public List<Action> getSubActions() {
        return subActions;
    }

}
