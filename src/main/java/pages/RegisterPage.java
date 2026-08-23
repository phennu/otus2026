package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends AbsBasePage {

    private static  String PATH = "/register";

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

}
