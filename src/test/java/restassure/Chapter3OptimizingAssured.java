package restassure;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Chapter3OptimizingAssured {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us/").build();
    }

    @BeforeClass
    public static void responseSpecification() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType("application/json").
                build();
    }

    @Test
    public void checkHitAPISuccess(){
        given().
            spec(requestSpec).
        when().
            get("us/90210").
        then().
            spec(responseSpec);
    }

    @Test
    public void checkPlaceName(){
        String placeName =
        given().
            spec(requestSpec).
        when().
            get("us/90210").
        then().
            extract().
            path("places[0].'place name'");

        assertEquals(placeName, "Beverly Hills");
    }
}
