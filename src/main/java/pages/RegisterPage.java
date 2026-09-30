package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterPage extends AbsBasePage {

    private static final String PATH = "/register";
    private final By createListButton = By.xpath("//button[normalize-space()='Создать новый список']");
    private final By tagName = By.cssSelector("h2");

    public RegisterPage(WebDriver driver) {
        super(driver, PATH);
    }

    public void enterText(String byCss, String enterText) {
        String selector = String.format("input[type='%s']", byCss);
        driver.findElement(By.cssSelector(selector)).sendKeys(enterText);
    }

    public void submitForm(String byCss) {
        String selector = String.format("button[type='%s']", byCss);
        driver.findElement(By.cssSelector(selector)).click();
    }

    public String getTagNameText() {
        return waiter.waitForElement(tagName).getText();
    }

    public boolean isCreateListButtonVisible() {
        return waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(createListButton));
    }

    public void assertForTagName(String tagName){
        assertEquals("Мои списки желаний", tagName);
    }

}
