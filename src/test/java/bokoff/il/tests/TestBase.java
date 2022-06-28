package bokoff.il.tests;

import bokoff.il.helpers.Attach;
import bokoff.il.owner.RemoteWebDriver;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;



public class TestBase {
  @BeforeAll
  static void configureBaseUrl() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    Configuration.baseUrl = "http://demowebshop.tricentis.com";
    RestAssured.baseURI = "http://demowebshop.tricentis.com";

    String remote = System.getProperty("remote", "selenoid.autotests.cloud/wd/hub");
    String browser = System.getProperty("browser", "chrome");
    String browserSize = System.getProperty("browserSize", "1920x1080");

    RemoteWebDriver remoteWebDriver = ConfigFactory.create(RemoteWebDriver.class);
    String login = remoteWebDriver.login();
    String password = remoteWebDriver.password();

    Configuration.remote = "https://" + login + ":" + password + "@" + remote;
    Configuration.browser = browser;
    Configuration.browserSize = browserSize;

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", true);
    Configuration.browserCapabilities = capabilities;
  }


  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Скриншот выполненного теста");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
    Selenide.closeWebDriver();
  }
}