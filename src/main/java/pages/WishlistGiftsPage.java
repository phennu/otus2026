package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishlistGiftsPage extends AbsBasePage {

    private static final Logger logger = LogManager.getLogger(WishlistGiftsPage.class);
    private final By wishlistPage = By.cssSelector(".mt-5.container");
    private final By addButton = By.cssSelector(".mb-4.btn.btn-primary");

    private final By wishlistNameInputField = By.cssSelector("input.form-control[type='text']");
    private final By wishlistDescriptionInputField = By.cssSelector("textarea.form-control");
    private final By shopUrlInputField = By.cssSelector("input[type='url'][placeholder='https://example.com/product']");
    private final By priceInputField = By.cssSelector("input[type='number']");
    private final By imageUrlInputField = By.cssSelector("input[type='url'][placeholder='https://example.com/image.jpg']");
    private final By addNewGiftButton = By.cssSelector("button[type='submit'].btn.btn-primary");


    public WishlistGiftsPage(WebDriver driver, String path) {
        super(driver, path);
    }

    public WebElement waitForWishlistPageToBeVisible() {
        return waiter.waitForElement(wishlistPage);

    }

    public WebElement addButtonToBeClickable() {
        return waiter.waitUntilClickable(addButton);
    }

    public void fillWishlistForm(String name, String description, String shopUrl, String price, String imageUrl) {
        logger.info("Filling gift form with data");
        driver.findElement(wishlistNameInputField).sendKeys(name);
        driver.findElement(wishlistDescriptionInputField).sendKeys(description);
        driver.findElement(shopUrlInputField).sendKeys(shopUrl);
        driver.findElement(priceInputField).sendKeys(price);
        driver.findElement(imageUrlInputField).sendKeys(imageUrl);

    }

    public WebElement addNewGiftButtonToBeClickable() {
        return waiter.waitUntilClickable(addNewGiftButton);

    }

    public WebElement getGiftByName(String name) {
        String giftLocator = String.format("//div[contains(@class,'card-body')]" +
                "[.//div[contains(@class,'card-title') and normalize-space()='%s']]", name);
        return waiter.waitForElement(By.xpath(giftLocator));
    }

    public void returnToWishlistsPage() {
        logger.info("Returning to wishlist page");
        driver.findElement(By.cssSelector("a.nav-link[href='/wishlists']")).click();
    }
}

