package pl.kielce.tu.libraryApp.util;

import pl.kielce.tu.libraryApp.action.Action;

import java.util.List;
import java.util.Scanner;

/**
 * @author ciepluchs
 */
public abstract class ViewUtil {

    private ViewUtil() {
    }

    public static void displaySubMenu(List<Action> availableActions) {
        int i = 0;
        for (Action action : availableActions) {
            System.out.println(++i + ") " + action.getDisplayName());
        }
        System.out.println("<- back");
        System.out.println("What you want to do: ");
    }

    public static String getSelectedOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static int getSelectedNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
