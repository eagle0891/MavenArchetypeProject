package GraphQLRestAssured;
import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
public class FirstTest {

    @Test
    public void firstTest(){
        //https://swapi-graphql.netlify.app/.netlify/functions/index //retrieved via Network > index request > Request URL

        RestAssured.baseURI = "https://swapi-graphql.netlify.app"; //main part of the URI
        String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\"}"; //retrieved from Network > index request > request payload > view source
        String uri = "/.netlify/functions/index";

        given().log().all()
                .contentType("application/json")
                .body(query)
                    .when().log().all()
                        .post(uri)
                            .then().log().all()
                                .assertThat()
                                    .statusCode(200);


    }
}
