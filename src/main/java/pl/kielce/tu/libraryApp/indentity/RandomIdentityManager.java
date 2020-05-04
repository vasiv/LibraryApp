package pl.kielce.tu.libraryApp.indentity;

import java.util.Random;

/**
 * @author ciepluchs
 */
public class RandomIdentityManager{

    private static final Random random = new Random(System.currentTimeMillis());

    public static long generateNewId() {
        return Math.abs(random.nextInt());
    }
}
