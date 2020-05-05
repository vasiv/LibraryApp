package pl.kielce.tu.libraryApp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ciepluchs
 */
public abstract class PropertiesUtil {

    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Cannot open properties file.");
                //TODO add logger
            }
        } catch (IOException e) {

        }
    }

    private PropertiesUtil() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
