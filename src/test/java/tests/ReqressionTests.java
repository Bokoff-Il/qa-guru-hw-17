package tests;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

public class ReqressionTests {

  @Test
  void loginTest(){
    String body="{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

    given()
        .log().uri()
        .log().body()
        .body(body)
        .contentType(JSON)
        .when()
        .post("https://reqres.in/api/login")
        .then()
        .log().status()
        .log().body()
        .body("token", is("QpwL5tke4Pnpja7X4"));
  }

  @Test
  void missingPasswordLoginTest(){
    String body="{ \"email\": \"eve.holt@reqres.in\"}";

    given()
        .log().uri()
        .log().body()
        .body(body)
        .contentType(JSON)
        .when()
        .post("https://reqres.in/api/login")
        .then()
        .log().status()
        .log().body()
        .statusCode(400)
        .body("error", is("Missing password"));
  }
}
