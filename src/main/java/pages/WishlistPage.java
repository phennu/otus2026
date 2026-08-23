package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishlistPage extends AbsBasePage {

    private static  String PATH = "/wishlist";

    public WishlistPage(WebDriver driver) {
        super(driver, PATH);
    }
    public void enterText(String byCss, String enterText) {
        String selector = String.format("input[type='%s']", byCss);
        driver.findElement(By.cssSelector(selector)).sendKeys(enterText);
    }

    public void createNewList() {
        String createNewListButton = "//button[normalize-space()='Создать новый список']";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(createNewListButton))).click();
    }

    public void createList() {
        String createListButton = "button[type='submit'].btn.btn-primary";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(createListButton))).click();
    }

}
