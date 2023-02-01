package restassure;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class Chapter2MultipleData {

    @DataProvider
    public static Object[][] listData() {
        return new Object[][] {
                {"us", "90210", "Beverly Hills"},
                {"ca", "B2R", "Waverley"},
                {"ar", "1601", "ISLA MARTIN GARCIA"}
        };
    }

    @Test
    @UseDataProvider("listData")
    public void checkPlaceName(String countryCode, String zipCode, String placeName){
        given().
            pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
        when().
            get("http://api.zippopotam.us/{countryCode}/{zipCode}").
        then().
            body("places[0].'place name'",equalTo(placeName));
    }
}
