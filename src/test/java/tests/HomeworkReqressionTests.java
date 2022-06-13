package tests;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class HomeworkReqressionTests {

  @Test
  void successRegisterTest(){
    String body="{\n"
                + "    \"email\": \"eve.holt@reqres.in\",\n"
                + "    \"password\": \"pistol\"\n"
                + "}";

    given()
        .log().uri()
        .log().body()
        .body(body)
        .contentType(JSON)
        .when()
        .post("https://reqres.in/api/register")
        .then()
        .log().status()
        .log().body()
        .statusCode(200)
        .body("id", is(4))
        .body("token", is("QpwL5tke4Pnpja7X4"));
  }

  @Test
  void missingPasswordlRegisterTest(){
    String body="{\n"
                + "    \"email\": \"sydney@fife\"\n"
                + "}";

    given()
        .log().uri()
        .log().body()
        .body(body)
        .contentType(JSON)
        .when()
        .post("https://reqres.in/api/register")
        .then()
        .log().status()
        .log().body()
        .statusCode(400)
        .body("error", is("Missing password"));
  }

  @Test
  void missingEmailAndPasswordlRegisterTest(){
    String body="{}";

    given()
        .log().uri()
        .log().body()
        .body(body)
        .contentType(JSON)
        .when()
        .post("https://reqres.in/api/register")
        .then()
        .log().status()
        .log().body()
        .statusCode(400)
        .body("error", is("Missing email or username"));
  }

  @Test
  void getListUserTest(){
    Integer page=3;
    given()
        .log().uri()
        .when()
        .get("https://reqres.in/api/users?page="+page)
        .then()
        .log().status()
        .log().body()
        .statusCode(200)
        .body("page", is(page))
        .body("support.url", is("https://reqres.in/#support-heading"))
        .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
  }

  @Test
  void getSingleUserNotFoundTest(){
    Integer user=23;
    get("https://reqres.in/api/users/"+user)
        .then()
        .statusCode(404);
  }

  @Test
  void updateUserPutRequestsTest(){
    String body="{\n"
                + "    \"name\": \"ilya\",\n"
                + "    \"job\": \"QAA\"\n"
                + "}";

    given()
        .log().uri()
        .log().body()
        .body(body)
        .contentType(JSON)
        .when()
        .put("https://reqres.in/api/users/2")
        .then()
        .log().status()
        .log().body()
        .statusCode(200)
        .body("name", is("ilya"))
        .body("job", is("QAA"));
  }

  @Test
  void updateUserPatchRequestsTest(){
    String body="{\n"
                + "    \"name\": \"ilya\",\n"
                + "    \"job\": \"QAA\"\n"
                + "}";

    given()
        .log().uri()
        .log().body()
        .body(body)
        .contentType(JSON)
        .when()
        .patch("https://reqres.in/api/users/2")
        .then()
        .log().status()
        .log().body()
        .statusCode(200)
        .body("name", is("ilya"))
        .body("job", is("QAA"));
  }

  @Test
  void deleteUserTest(){
    delete("https://reqres.in/api/users/2")
        .then()
        .statusCode(204);
  }
}
