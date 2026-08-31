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
import pages.WishlistPage;
import utility.GeneratedTestData;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishlistPageTests {

    private WebDriver driver = null;

    private String login = "inurtazin";
    private String password = "123456";
    private String listName;

    @BeforeEach
    public void init() {
        this.driver = WebDriverFactory.create("--start-fullscreen");

        this.listName = GeneratedTestData.generatedListName();
    }

    @AfterEach
    void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Создание нового списка")
    void addNewList() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.enterText("text", login);
        loginPage.enterText("password",password);
        loginPage.submitForm("submit");

        WishlistPage wishlistPage = new WishlistPage(driver);

        wishlistPage.createNewList();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal-title.h4")));

        wishlistPage.enterText("text", listName);

        wishlistPage.createList();

        WebElement card = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".g-4.row > .col:first-child .card-title")));

        WebElement cardNew = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'card-title') and normalize-space()='"
                                        + listName + "']/ancestor::div[contains(@class,'card')]")));

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                cardNew.findElement(By.cssSelector("button.btn.btn-danger"))));

       deleteButton.click();

        assertEquals(listName,card.getText());

    }

}
