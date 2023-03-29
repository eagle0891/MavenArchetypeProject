package GraphQLRestAssured.Modules;

import Helpers.ReadFrom;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static GraphQLRestAssured.POJO.ModuleConfigQueries.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class GraphQLModule {

    static ValidatableResponse response;
    ConsoleLogger consoleLogger;
    //Boolean debugLogging = false;

    public GraphQLModule() {
        consoleLogger = new ConsoleLogger();
        setQueryURI();
    }

    protected String setQueryURI(){
        return RestAssured.baseURI = ReadFrom.propertiesFile("data", "baseURI");
    }

    public static final  ValidatableResponse returnAPIResponseForAssertion(String contentType, String requestModuleToTest, String uri, Integer statusCode, String responseBody, String expectedResponse){

        RestAssured.baseURI = "https://swapi-graphql.netlify.app"; //main part of the URI

        response = given().log().all()
                .contentType(contentType)
                .body(QuerySwitch.getRequestModuleQuery(requestModuleToTest))
                .when().log().all()
                .post(uri)
                .then().log().all()
                .assertThat()
                .statusCode(statusCode)
                .body(responseBody, equalTo(expectedResponse));
        return response;
    }

}
