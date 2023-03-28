package stepDefinitions;

import GraphQLRestAssured.POJO.ModuleConfigQueries;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class APIStepsDefs extends ModuleConfigQueries{

    @Given("^request contains '(.*)' and Star Wars films query, and request is sent to '(.*)', and response returns status (.*) and body '(.*)' matches '(.*)'$")
    public void firstStarWarsTest(String contentType, String uri, int statusCode, String responseBody, String expectedResponse) {
        RestAssured.baseURI = "https://swapi-graphql.netlify.app"; //main part of the URI

        given().log().all()
                .contentType(contentType)
                .body(returnModuleConfigQueryAllStarWarsFilms())
                .when().log().all()
                .post(uri)
                .then().log().all()
                .assertThat()
                .statusCode(statusCode)
                .body(responseBody, equalTo(expectedResponse));
    }

}
