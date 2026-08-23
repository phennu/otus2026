import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utility.GeneratedTestData;
import pages.RegisterPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegisterPageTests {

    private WebDriver driver = null;
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

        page.enterText("text", login);
        page.enterText("email", email);
        page.enterText("password",password);
        page.submitForm("submit");
        Thread.sleep(1000);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.enterText("text", login);
        loginPage.enterText("password",password);
        loginPage.submitForm("submit");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[normalize-space()='Создать новый список']")));

        WebElement tagName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h2")));

        assertEquals("Мои списки желаний",tagName.getText());
    }

}
