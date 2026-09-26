package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WishlistsPage extends AbsBasePage {

    private static final Logger logger = LogManager.getLogger(WishlistsPage.class);
    private static final String PATH = "/wishlist";
    private final By modalTitle = By.cssSelector("div.modal-title.h4");
    private final By cardTile = By.cssSelector(".g-4.row.row-cols-lg-3.row-cols-md-2.row-cols-1 .card");
    private final By deleteButton = By.cssSelector("button.btn.btn-danger");


    public WishlistsPage(WebDriver driver) {
        super(driver, PATH);
    }

    public void enterText(String byCss, String enterText) {
        String selector = String.format("input[type='%s']", byCss);
        driver.findElement(By.cssSelector(selector)).sendKeys(enterText);
    }

    public void createNewListText() {
        String createNewListButton = "//button[normalize-space()='Создать новый список']";
        logger.info("Pressing 'Create new wishlist' button");
        waiter.waitUntilClickable(By.xpath(createNewListButton)).click();
    }

    public void createListButton() {
        String createListButton = "button[type='submit'].btn.btn-primary";
        logger.info("Sumbiting wishlist creation");
        waiter.waitUntilClickable(By.cssSelector(createListButton)).click();
    }

    public boolean isModalTitleVisible(){
        return waiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(modalTitle));
    }

    public WebElement getFirstCardTileInfo(){
        return waiter.waitForElement(cardTile);
    }

    public WebElement waitForCardToBeVisible(String listName) {
        String newCardTile = "//div[contains(@class,'card-title') and normalize-space()='%s']" +
                "/ancestor::div[contains(@class,'card')]";
        String xpath = String.format(newCardTile,listName);

        return waiter.waitForElement(By.xpath(xpath));
    }

    public boolean waitForCardToBeInvisible(String listName) {
        String newCardTile = "//div[contains(@class,'card-title') and normalize-space()='%s']" +
                "/ancestor::div[contains(@class,'card')]";
        String xpath = String.format(newCardTile,listName);

        return waiter.waitForElementToBeInvisible(By.xpath(xpath));
    }

}
