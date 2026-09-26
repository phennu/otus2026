import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utility.GeneratedTestData;
import pages.RegisterPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegisterPageTests {

    private WebDriver driver = null;
    private final static Logger logger = LogManager.getLogger(RegisterPageTests.class);
    private String login;
    private String email;
    private String password;

    @BeforeEach
    public void init() {
        this.driver = WebDriverFactory.create("--kiosk");

        this.login = GeneratedTestData.generatedLogin();
        this.email = GeneratedTestData.generatedEmail();
        this.password = GeneratedTestData.generatedPassword();
    }

    @AfterEach
    void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Регистрация нового пользователя и вход")
    void registerNewUserAndLogin() throws InterruptedException {
        RegisterPage page = new RegisterPage(driver);
        page.open();
        logger.info("---Starting test of creating new user and login in---");
        logger.info("Using generated data to create user");
        page.enterText("text", login);
        page.enterText("email", email);
        page.enterText("password",password);
        page.submitForm("submit");
        Thread.sleep(1000);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        logger.info("Login with created user: {}", login);
        loginPage.enterText("text", login);
        loginPage.enterText("password",password);
        loginPage.submitForm("submit");

        page.isCreateListButtonVisible();

        String tagName = page.getTagNameText();
        logger.info("User wishlist page is loaded");
        assertEquals("Мои списки желаний",tagName);
    }

}
