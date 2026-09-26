package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbsBasePage {

    private static final String PATH = "/login";
    private final By alert = By.cssSelector("div[role='alert']");

    public LoginPage(WebDriver driver) {
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

        public String getAlertText(){
            return waiter.waitForElement((alert)).getText();
        }

}
