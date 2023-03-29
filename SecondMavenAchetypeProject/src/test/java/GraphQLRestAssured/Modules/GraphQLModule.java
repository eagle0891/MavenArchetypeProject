package GraphQLRestAssured.Modules;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static GraphQLRestAssured.POJO.ModuleConfigQueries.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class GraphQLModule {

    static ValidatableResponse response;

    public static final  ValidatableResponse returnAPIResponseForAssertion(String contentType, String requestBody, String uri, Integer statusCode, String responseBody, String expectedResponse){

        RestAssured.baseURI = "https://swapi-graphql.netlify.app"; //main part of the URI

        response = given().log().all()
                .contentType(contentType)
                .body(requestBody)
                .when().log().all()
                .post(uri)
                .then().log().all()
                .assertThat()
                .statusCode(statusCode)
                .body(responseBody, equalTo(expectedResponse));
        return response;
    }

}
