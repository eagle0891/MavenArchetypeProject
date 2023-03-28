package stepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class APIStepsDefs {

    @Given("^request contains '(.*)' and '(.*)', and request is sent to '(.*)', and response returns status (.*) and body '(.*)' matches '(.*)'$")
    public void firstStarWarsTest(String contentType, String requestBody, String uri, int statusCode, String responseBody, String expectedResponse) {
        RestAssured.baseURI = "https://swapi-graphql.netlify.app"; //main part of the URI
//        String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\"}"; //retrieved from Network > index request > request payload > view source
//        String uri = "/.netlify/functions/index";

        given().log().all()
                .contentType(contentType)
                .body(requestBody)
                .when().log().all()
                .post(uri)
                .then().log().all()
                .assertThat()
                .statusCode(statusCode)
                .body(responseBody, equalTo(expectedResponse));
    }

}
