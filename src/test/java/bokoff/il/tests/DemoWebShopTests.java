package bokoff.il.tests;

import static bokoff.il.helpers.CustomApiListener.withCustomTemplates;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

@Tag("demowebshop")
public class DemoWebShopTests extends TestBase {

  static String login = "qaguru@qa.guru",
      password = "qaguru@qa.guru1",
      authorizationCookieName = "NOPCOMMERCE.AUTH";

  @Test
  @DisplayName("Successful authorization to some demowebshop (UI)")
  void loginTest() {
    step("Open main page", () ->
        open("/login"));

    step("Fill login form", () -> {
      $("#Email").setValue(login);
      $("#Password").setValue(password).pressEnter();
    });

    step("Verify successful authorization", () ->
        $(".account").shouldHave(text(login)));
  }

  @Test
  @DisplayName("Successful authorization to some demowebshop (UI+API)")
  void loginWithApiTest() {
    step("Get cookie by api and set it to browser", () -> {
      String authorizationCookieValue = given()
          .contentType("application/x-www-form-urlencoded")
          .formParam("Email", login)
          .formParam("Password", password)
          .log().all()
          .when()
          .post("/login")
          .then()
          .log().all()
          .statusCode(302)
          .extract().cookie(authorizationCookieName);

      step("Open minimal content", () ->
          open("/Themes/DefaultClean/Content/images/logo.png"));

      step("set cookie", () -> {
        Cookie ck = new Cookie(authorizationCookieName, authorizationCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
      });
    });

    step("Open main page", () ->
        open("/"));

    step("Verify successful authorization", () ->
        $(".account").shouldHave(text(login)));
  }

  @Test
  @DisplayName("Successful authorization to some demowebshop (UI+API+Listener)")
  void loginWithApiAndAllureListenerTest() {
    step("Get cookie by api and set it to browser", () -> {
      String authorizationCookieValue = given()
          .filter(new AllureRestAssured())
          .contentType("application/x-www-form-urlencoded")
          .formParam("Email", login)
          .formParam("Password", password)
          .log().all()
          .when()
          .post("/login")
          .then()
          .log().all()
          .statusCode(302)
          .extract().cookie(authorizationCookieName);

      step("Open minimal content", () ->
          open("/Themes/DefaultClean/Content/images/logo.png"));

      step("set cookie", () -> {
        Cookie ck = new Cookie(authorizationCookieName, authorizationCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
      });
    });

    step("Open main page", () ->
        open("/"));

    step("Verify successful authorization", () ->
        $(".account").shouldHave(text(login)));
  }

  @Test
  @DisplayName("Successful authorization to some demowebshop (UI+API+CustomListener)")
  void loginWithApiAndCustomListenerTest() {
    step("Get cookie by api and set it to browser", () -> {
      String authorizationCookieValue = given()
          .filter(withCustomTemplates())
          .contentType("application/x-www-form-urlencoded")
          .formParam("Email", login)
          .formParam("Password", password)
          .log().all()
          .when()
          .post("/login")
          .then()
          .log().all()
          .statusCode(302)
          .extract().cookie(authorizationCookieName);

      step("Open minimal content", () ->
          open("/Themes/DefaultClean/Content/images/logo.png"));

      step("set cookie", () -> {
        Cookie ck = new Cookie(authorizationCookieName, authorizationCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
      });
    });

    step("Open main page", () ->
        open("/"));

    step("Verify successful authorization", () ->
        $(".account").shouldHave(text(login)));
  }
}
