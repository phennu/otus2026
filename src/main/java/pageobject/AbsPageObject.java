package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import waiters.CommonWaiter;

public class AbsPageObject {
    protected WebDriver driver;
    protected CommonWaiter waiter;

    public AbsPageObject(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);

        waiter = new CommonWaiter(driver);
    }
}
