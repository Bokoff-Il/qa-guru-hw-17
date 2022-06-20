package bokoff.il.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import bokoff.il.specs.Specs;
import org.junit.jupiter.api.Test;

public class GroovyTests {
  @Test
  void checkResource() {
    given()
        .spec(Specs.request)
        .get("/unknown")
        .then()
        .body("data.findAll{it.year=2000}.name", hasItem("cerulean"))
        .body("data.findAll{it.color='#BF1932'}.pantone_value", hasItem("19-1664"))
        .log().all();
  }
}
