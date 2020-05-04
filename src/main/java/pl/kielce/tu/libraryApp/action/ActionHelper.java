package pl.kielce.tu.libraryApp.action;

import pl.kielce.tu.libraryApp.session.Session;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ciepluchs
 */
public class ActionHelper {

    public static List<Action> getAvailableActions(List<Action> actions) {
        return actions.stream()
                .filter(action -> !Collections.disjoint(Session.getUser().getRoles(), action.getAllowedRoles()))
                .collect(Collectors.toList());
    }
}
