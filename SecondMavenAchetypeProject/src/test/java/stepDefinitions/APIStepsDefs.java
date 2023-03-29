package stepDefinitions;

import GraphQLRestAssured.Modules.ConsoleLogger;
import GraphQLRestAssured.Modules.GraphQLDataFields;
import GraphQLRestAssured.Modules.GraphQLModule;
import GraphQLRestAssured.POJO.ModuleConfigQueries;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

public class APIStepsDefs extends ModuleConfigQueries{

//    GraphQLModule gqlModule = new GraphQLModule();
ConsoleLogger consoleLogger = new ConsoleLogger();
String jsonResponse;
JsonPath jsonPath;
public String loggedResponses;

    @Given("^request contains '(.*)' and '(.*)', and request is sent to '(.*)', and response returns status '(.*)' and body '(.*)' matches '(.*)'$")
    public void firstStarWarsTest(String contentType, String requestModuleToTest, String uri, Integer statusCode, String responseBody, String expectedResponse) {
        RestAssured.baseURI = "https://swapi-graphql.netlify.app"; //main part of the URI

//        given().log().all()
//                .contentType(contentType)
//                .body(returnModuleConfigAllStarWarsFilmsQuery())
//                .when().log().all()
//                .post(uri)
//                .then().log().all()
//                .assertThat()
//                .statusCode(statusCode)
//                .body(responseBody, equalTo(expectedResponse));

        jsonResponse = GraphQLModule.returnAPIResponseForAssertion(contentType, requestModuleToTest, uri, statusCode, responseBody, expectedResponse).extract().jsonPath().prettify();
        jsonPath = new JsonPath(jsonResponse);

//        GsonConstructor(requestModuleToTest);

        for (String dataField : GraphQLDataFields.returnModuleArray(requestModuleToTest)) {
            assertTrue("ERROR : data Field not matched : " + dataField,jsonResponse.contains(dataField));
            consoleLogger.API_Response_Data_Logger(requestModuleToTest, jsonPath, dataField, this);
        }
        consoleLogger.printMessageToConsole(loggedResponses);
    }

}
