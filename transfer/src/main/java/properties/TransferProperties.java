package properties;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j
public class TransferProperties {
    private static TransferProperties instance;

    private final Properties properties;

    private TransferProperties() {
        this.properties = new Properties();
        try {
            InputStream reader = this.getClass().getClassLoader().getResourceAsStream("transfer.properties");
            properties.load(reader);
        } catch (IOException e) {
           log.error("Cannot load transfer properties file");
        }
    }

    public static TransferProperties getInstance() {
        if (instance == null) instance = new TransferProperties();
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
