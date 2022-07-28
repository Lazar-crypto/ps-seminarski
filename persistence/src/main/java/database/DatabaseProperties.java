package database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
    
    private final Properties properties;
    private DatabaseProperties(){
        this.properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("database.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("Could not load database properties");
        }
    }

    //lazy loading i thread safe
    private static class Singleton{
        private static final DatabaseProperties INSTANCE = new DatabaseProperties();
    }
    public static DatabaseProperties getInstance(){
        return Singleton.INSTANCE;
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
     
}
