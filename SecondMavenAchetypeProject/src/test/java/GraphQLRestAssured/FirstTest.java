package GraphQLRestAssured;
import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class FirstTest {

    private String limit;

    public FirstTest(String limit) {
        this.limit = limit;
    }

    @Test
    public void firstStarWarsTest(){
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
                                .statusCode(200)
                                    .and()
                                        .body("data.allFilms.films[0].title", equalTo("A New Hope"));
    }

    @Test
    public void firstHasuraTest(){
        RestAssured.baseURI = "https://great-ladybug-56.hasura.app";
        String query = "{\"query\":\"query fetchAlbum {\\n  Album {\\n    AlbumId\\n    Title\\n    Tracks {\\n      TrackId\\n      Name\\n    }\\n  }\\n}\\n\",\"variables\":null,\"operationName\":\"fetchAlbum\"}";
        String uri = "/v1/graphql";

        given().log().all()
                .contentType("application/json")
                .header("x-hasura-admin-secret","3m1kpYOAi6QkJsjCC1qpzHe0KTd1cDVffdlkqKq3DMrFHnpXxAAtpMNym7ZNHzKk")
                .body(query)
                .when().log().all()
                .post(uri)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("data.Album.Tracks[0].TrackId[0]", equalTo(1))
                .body("data.Album.Tracks[0].Name[0]", equalTo("For Those About To Rock (We Salute You)"));

    }

    @Parameterized.Parameters
    public static Collection<Object[]> getQueryData(){
        return Arrays.asList(new Object[][]{{"3"}});
    }

    @Test
    //Test is run using the Junit "parameterized" data approach
    //Set the class with annotation @RunWith(Parameterized.class)
    //Construct data parameters
    //Set up a static method that returns a list of parameterized data with the annotation @Parameterized.Parameters (in TestNG this is @DataProvider)
    public void hasuraTestWithLimitSet(){
        RestAssured.baseURI = "https://great-ladybug-56.hasura.app";
        String query = "{\"query\":\"query fetchAlbum {\\n  Album(limit: " + limit + ") {\\n    AlbumId\\n    Title\\n    Tracks {\\n      TrackId\\n      Name\\n    }\\n  }\\n}\\n\",\"variables\":null,\"operationName\":\"fetchAlbum\"}";
        String uri = "/v1/graphql";

        given().log().all()
                .contentType("application/json")
                .header("x-hasura-admin-secret","3m1kpYOAi6QkJsjCC1qpzHe0KTd1cDVffdlkqKq3DMrFHnpXxAAtpMNym7ZNHzKk")
                .body(query)
                .when().log().all()
                .post(uri)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("data.Album.Tracks[0].TrackId[0]", equalTo(1))
                .body("data.Album.Tracks[0].Name[0]", equalTo("For Those About To Rock (We Salute You)"));

    }
}
