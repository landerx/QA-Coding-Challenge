package rest.configuration;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.parsing.Parser;

public class RestAssuredConfiguration {

    public static void RestAssuredSetup() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)
                        .gsonObjectMapperFactory((cls, charset) -> new Gson()));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

}
