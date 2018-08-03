package rest.base;

import org.junit.BeforeClass;
import rest.configuration.RestAssuredConfiguration;

public class TestBase {

    @BeforeClass
    public static void restAssuredConfiguration() {
        RestAssuredConfiguration.RestAssuredSetup();
    }

}
