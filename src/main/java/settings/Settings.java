package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 To work with settings
 */
public class Settings {
    /**
     Gets a setting from a settings configuration
     Params: setting name
     If the setting is found, we return the string value of the setting, otherwise we return an empty string
     */
    public static String getSetting(String setting){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);

            return property.getProperty(setting);

        } catch (IOException e) {
            System.err.println("Properties file missing!");
            return "";
        }
    }
}
