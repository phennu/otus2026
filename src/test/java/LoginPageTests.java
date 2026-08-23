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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTests {

    private WebDriver driver = null;

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
            LoginPage page = new LoginPage(driver);
            page.open();

            page.enterText("text", "test");
            page.enterText("password","test");
            page.submitForm("submit");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div[role='alert']")));

            assertEquals("Неверное имя пользователя или пароль",alert.getText());
        }
}
