package stepDefinitions;

import GraphQLRestAssured.GQLCalls.GQLCalls;
import com.sun.jna.IntegerType;
import io.cucumber.java.en.Given;

public class APIStepsDefs {
    GQLCalls gqlCalls;

    public APIStepsDefs(GQLCalls gqlCalls) {
        this.gqlCalls = gqlCalls;
    }

    @Given("^request contains '(.*)' and '(.*)', and request is sent to '(.*)', and response returns status '(.*)' and body '(.*)'$")
    public void firstStarWarsTest(String contentType, String query, String uri, Integer statusCode, String responseBody){
        //https://swapi-graphql.netlify.app/.netlify/functions/index //retrieved via Network > index request > Request URL

//        RestAssured.baseURI = "https://swapi-graphql.netlify.app"; //main part of the URI
//        String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\"}"; //retrieved from Network > index request > request payload > view source
//        String uri = "/.netlify/functions/index";
//
//        given().log().all()
//                .contentType("application/json")
//                .body(query)
//                    .when().log().all()
//                        .post(uri)
//                            .then().log().all()
//                                .assertThat()
//                                    .statusCode(200)
//                                    .body("data.allFilms.films[0].title", equalTo("A New Hope"));
        gqlCalls.firstStarWarsTestCall(contentType, query, uri, statusCode, responseBody);
    }
}
