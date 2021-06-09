import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequest;
import io.github.kobylynskyi.starwars.graphql.LaunchQueryRequest;
import io.github.kobylynskyi.starwars.graphql.LaunchQueryResponse;
import io.github.kobylynskyi.starwars.graphql.LaunchResponseProjection;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestGraphql {

    @Test
    public void test() {
        LaunchQueryRequest launchQueryRequest = LaunchQueryRequest.builder().setId("83").build();

        LaunchResponseProjection launchResponseProjection = new LaunchResponseProjection().isBooked().site();

        GraphQLRequest request = new GraphQLRequest(launchQueryRequest, launchResponseProjection);

        String resp = given()
                .baseUri("https://apollo-fullstack-tutorial.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(request.toHttpJsonBody())
                .post("/graphql")
                .then()
                .extract().response().asString();

        System.out.println("======== " + resp + " ========");

        LaunchQueryResponse response = given()
                .baseUri("https://apollo-fullstack-tutorial.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(request.toHttpJsonBody())
                .post("/graphql")
                .then()
                .extract()
                .response()
                .as(LaunchQueryResponse.class);

        System.out.println("1");

        GraphQLRequest request2 = new GraphQLRequest(launchQueryRequest);

        LaunchQueryResponse response2 = given()
                .baseUri("https://apollo-fullstack-tutorial.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(request.toHttpJsonBody())
                .post("/graphql")
                .then()
                .extract()
                .response()
                .as(LaunchQueryResponse.class);

        System.out.println("");

    }
}
