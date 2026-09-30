package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends AbsBasePage {

    private static final String PATH = "/login";
    private final By alert = By.cssSelector("div[role='alert']");
    private final By login = By.cssSelector("input[type='text']");
    private final By submitButton = By.cssSelector("button[type='password']");

    public LoginPage(WebDriver driver) {
        super(driver, PATH);
    }

    public void enterText(String enterText) {
        waiter.waitForElement(login).sendKeys(enterText);
    }

    public void submitButtonClick() {
        waiter.waitUntilClickable(submitButton).click();
    }

    public String getAlertText() {
        return waiter.waitForElement((alert)).getText();
    }

    public void assertionForText(String alert){
        assertEquals("Неверное имя пользователя или пароль", alert);
    }

}
