package properties;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public enum AppProperties {

    FIRST_TASK_BASE_URL("task.one.baseUrl"),
    SECOND_TASK_BASE_URL("task.two.baseUrl");

    private String key;
    private static final String PROPERTIES_FILE_NAME = "application.properties";
    private static final Properties properties;

    static {
        properties = getProperties();
    }

    AppProperties(String key) {
        this.key = key;
    }

    public String getValue() {
        return properties.getProperty(key);
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = getAppPropertiesInputStream();
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("There was a problem during loading process of properties file");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    private static InputStream getAppPropertiesInputStream() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(PROPERTIES_FILE_NAME);
    }

}
