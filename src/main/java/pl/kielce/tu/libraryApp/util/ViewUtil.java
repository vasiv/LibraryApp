package pl.kielce.tu.libraryApp.util;

import pl.kielce.tu.libraryApp.action.Action;
import pl.kielce.tu.libraryApp.model.enumeration.Genre;

import java.util.Arrays;
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

    public static Genre getGenreInput(Scanner input){
        List<Genre> genres = Arrays.asList(Genre.values());
        for (int i = 0; i < genres.size(); i++) {
            System.out.println(i + 1 + ")" + genres.get(i));
        }
        int selectedGenre = input.nextInt();
        return genres.get(selectedGenre-1);
    }

}
