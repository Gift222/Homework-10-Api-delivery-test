import Dto.LoginDto;
import Dto.OrderDto;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RealOrder {

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnaWZ0LWFrdCIsImV4cCI6MTcyMDQwOTc4NiwiaWF0IjoxNzIwMzkxNzg2fQ.ZbAcd0QhAzWUsxpkxiNryaQMUUtfbkeImYjNbI2nJpDVSPfaocvNsPvI8gWa_LsViFy9HBkIZ04eCTCKNrpqJw"
    public static final String BASE_URL = "https://backend.tallinn-learning.ee";

    @BeforeAll
    public static void setup() {
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        Gson gson = new Gson();
        //LoginDto loginDtoJavaObject = new LoginDto("gift-akt", "pgd3VbfT2nt");
        LoginDto loginDtoJavaObject = new LoginDto(username,password);
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(gson.toJson(loginDtoJavaObject))
                .post(BASE_URL + "/login/student")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .body()
                .asString();


    }

    @Test
    public void createOrder() {
        Gson gson = new Gson();
        OrderDto realOderJavaObject = new OrderDto("Gift", "9095430982", "make me a coffee");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer" + token)
                .when()
                .body(gson.toJson(realOderJavaObject))
                .post(BASE_URL + "/orders")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void checkExistingOrderById() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .get(BASE_URL + "/orders/586")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }
}
