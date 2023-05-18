package orthazone.runner;

import java.io.*;
import java.util.Properties;

public class ProjectUtils {

    private static final String PREFIX_PROP = "local.";

    private static final String PROP_ADMIN_USERNAME = PREFIX_PROP + "admin.username";

    private static final String PROP_ADMIN_PAS = PREFIX_PROP + "admin.password";

    public static String getUserName() throws IOException {
        FileReader reader = new FileReader("src/test/resources/local.properties");
        Properties properties = new Properties();
        properties.load(reader);
        return properties.getProperty(PROP_ADMIN_USERNAME);
    }

    public static String getPassword() throws IOException {
        FileReader reader = new FileReader("src/test/resources/local.properties");
        Properties properties = new Properties();
        properties.load(reader);
        return properties.getProperty(PROP_ADMIN_PAS);
    }
}