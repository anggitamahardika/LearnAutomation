package restassure;

import entity.Location;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Chapter4DeserializationObjects {

    @Test
    public void getPlaceName(){
        Location location =
                given().
                        when().
                        get("http://api.zippopotam.us/us/90210").
                        as(Location.class);

        assertEquals("Beverly Hills", location.getPlaces().get(0).getPlaceName());
    }

    @Test
    public void tryToSeCountry(){
        Location location = new Location();
        location.setCountry("Netherlands");

        given().
            contentType(ContentType.JSON).
            body(location).
            log().body().
        when().
            post("http://api.zippopotam.us/lv/1050").
        then().
            assertThat().
            statusCode(405);

    }

}
