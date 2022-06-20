package bokoff.il.tests;

import static bokoff.il.specs.Specs.request;
import static bokoff.il.specs.Specs.responseFailed;
import static bokoff.il.specs.Specs.responseSuccess;
import static bokoff.il.specs.Specs.successRegisterTest;
import static bokoff.il.specs.Specs.updateUserResponse;
import static io.restassured.RestAssured.given;

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
        .spec(request)
        .body(body)

        .when()
        .post("/register")

        .then()
        .spec(successRegisterTest);
  }

  @Test
  void missingPasswordlRegisterTest(){
    String body="{\n"
                + "    \"email\": \"sydney@fife\"\n"
                + "}";

    given()
        .spec(request)
        .body(body)

        .when()
        .post("/register")

        .then()
        .spec(responseFailed)
        .body("error", is("Missing password"));
  }

  @Test
  void missingEmailAndPasswordRegisterTest(){
    String body="{}";

    given()
        .spec(request)
        .body(body)

        .when()
        .post("/register")

        .then()
        .spec(responseFailed)
        .body("error", is("Missing email or username"));
  }

  @Test
  void getListUserTest(){
    Integer page=3;
    given()
        .spec(request)

        .when()
        .get("/users?page="+page)

        .then()
        .spec(responseSuccess)
        .body("page", is(page))
        .body("support.url", is("https://reqres.in/#support-heading"))
        .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
  }

  @Test
  void getSingleUserNotFoundTest(){
    Integer user=23;
    given()
        .spec(request)

        .when()
        .get("/users/"+user)

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
        .spec(request)
        .body(body)

        .when()
        .put("users/2")

        .then()
        .spec(updateUserResponse);
  }

  @Test
  void updateUserPatchRequestsTest(){
    String body="{\n"
                + "    \"name\": \"ilya\",\n"
                + "    \"job\": \"QAA\"\n"
                + "}";

    given()
        .spec(request)
        .body(body)

        .when()
        .patch("/users/2")

        .then()
        .spec(updateUserResponse);
  }

  @Test
  void deleteUserTest(){
    given().
        when().
        delete("https://reqres.in/api/users/2")

        .then()
        .statusCode(204);
  }
}
