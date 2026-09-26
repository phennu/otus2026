package pages;

import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {

    private String path;
    private String baseUrl = System.getProperty("baseUrl");

    public AbsBasePage(WebDriver driver, String path) {
        super(driver);
        this.path = path;
    }

    public void open() {

        driver.get(baseUrl + path);

    }

}
