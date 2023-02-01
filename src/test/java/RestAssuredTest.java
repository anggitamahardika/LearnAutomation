import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {

    @Test
    public void checkHitAPISuccess(){
        given().
            when().
                get("http://api.zippopotam.us/us/90210").
            then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void checkLogRequestResponse(){
        given().
            log().parameters().
        when().
            get("http://api.zippopotam.us/us/90210").
        then().
            log().body();
    }

    @Test
    public void checkContentType(){
        given().
            when().
                get("http://api.zippopotam.us/us/90210").
            then().
                assertThat().
                contentType("application/json");
    }

    @Test
    public void checkPlaceName(){
        given().
            when().
                get("http://api.zippopotam.us/us/90210").
            then().
                body("places[0].'place name'",equalTo("Beverly Hills"));
    }

    @Test
    public void checkIsThereItem(){
        given().
            when().
                get("http://api.zippopotam.us/us/90210").
            then().
                assertThat().
                body("places.'state'",hasItem("California"));
    }

    @Test
    public void checkCollectionSize(){
        given().
            when().
                get("http://api.zippopotam.us/us/90210").
            then().
                assertThat().
                body("places",hasSize(1));
    }
}
