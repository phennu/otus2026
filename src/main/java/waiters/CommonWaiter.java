package waiters;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonWaiter {

    private final WebDriverWait wait;

    public CommonWaiter(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean waitForCondition(ExpectedCondition<?> condition) {
        try {
            wait.until(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public WebElement waitForElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitForElementToBeInvisible(By locator){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitUntilClickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForNumberOfElementsToBe(By locator, int number) {
         wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
    }

}
