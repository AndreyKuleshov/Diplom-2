package config;

import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public abstract class BaseClient extends Config {
        protected static RequestSpecification getSpec() {
            return given()
                    .contentType(ContentType.JSON)
                    .baseUri(Config.BASE_URL);
        }
        protected RequestSpecification getSpec(String token) {
            return given()
                    .contentType(ContentType.JSON)
                    .header(AUTHORIZATION_HEADER, token)
                    .baseUri(Config.BASE_URL);
        }
}
