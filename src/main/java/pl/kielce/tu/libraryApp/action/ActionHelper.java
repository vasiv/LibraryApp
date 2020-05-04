package pl.kielce.tu.libraryApp.action;

import pl.kielce.tu.libraryApp.session.Session;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ciepluchs
 */
public class ActionHelper {

    private static final String BACK_SYMBOL = "<-";
    private static final String BACK = "back";

    public static List<Action> getAvailableActions(List<Action> actions) {
        return actions.stream()
                .filter(action -> !Collections.disjoint(Session.getUser().getRoles(), action.getAllowedRoles()))
                .collect(Collectors.toList());
    }

    public static boolean isBackOptionSelected(String selectedOption){
        return BACK_SYMBOL.equals(selectedOption) || BACK.equals(selectedOption);
    }

    public static Action getSelectedAciton(List<Action> actions, String selectedOption){
        return actions.get(Integer.parseInt(selectedOption) - 1);
    }
}
