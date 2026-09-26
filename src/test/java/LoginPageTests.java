import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTests {

    private WebDriver driver = null;
    private static final Logger logger = LogManager.getLogger(LoginPageTests.class);

    @BeforeEach
    public void init(){
        this.driver = WebDriverFactory.create("--headless=new");
    }

        @AfterEach
        void close() {
        if (driver != null) {
            driver.quit();
        }
    }

        @Test
        @DisplayName("Вход незарегистрированным пользователем")
        void unregisteredUserLogin() {

            logger.info("---Starting test of incorrect login in system---");
            LoginPage page = new LoginPage(driver);
            page.open();
            logger.info("Using incorrect data to login");

            page.enterText("text", "test");
            page.enterText("password","test");
            page.submitForm("submit");

            String alert = page.getAlertText();
            logger.info("Got error of incorrect data to login");
            assertEquals("Неверное имя пользователя или пароль",alert);
        }
}
