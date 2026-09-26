import factory.WebDriverFactory;
import pages.LoginPage;
import pages.WishlistGiftsPage;
import pages.WishlistsPage;
import utility.GeneratedTestData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishlistPageTests {

    private WebDriver driver = null;
    private static final Logger logger = LogManager.getLogger(WishlistPageTests.class);

    private String login = System.getProperty("login");
    private String password = System.getProperty("password");
    private String listName;

    @BeforeEach
    public void init() {
        this.driver = WebDriverFactory.create("--start-fullscreen");

        this.listName = GeneratedTestData.generatedListName();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.enterText("text", login);
        loginPage.enterText("password",password);
        loginPage.submitForm("submit");
    }

    @AfterEach
    void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Создание нового списка")
    void addNewList() {

        WishlistsPage wishlistsPage = new WishlistsPage(driver);
        logger.info("---Starting test of creating new wishlist---");
        logger.info("Creating new wishlist");
        wishlistsPage.createNewListText();
        wishlistsPage.isModalTitleVisible();
        wishlistsPage.enterText("text", listName);
        wishlistsPage.createListButton();

        WebElement newCardTile = wishlistsPage.waitForCardToBeVisible(listName);
        String newCardTileText = newCardTile.findElement(By.cssSelector(".card-title")).getText();
        logger.info("New wishlist {} created", listName);
        logger.info("Deleting wishlist {}", listName);
        WebElement deleteButton = newCardTile.findElement(By.cssSelector("button.btn.btn-danger"));
        deleteButton.click();

        wishlistsPage.waitForCardToBeInvisible(listName);

        assertEquals(listName,newCardTileText);

    }

    @Test
    @DisplayName("Создание нового списка и добавление подарка")
    void addNewListWithGift() {

        logger.info("---Starting test of adding new gift to wishlist---");
        WishlistsPage wishlistsPage = new WishlistsPage(driver);
        wishlistsPage.isModalTitleVisible();

        final String wishlistNameInput = GeneratedTestData.generatedGiftName();
        final String wishlistDescriptionInput = GeneratedTestData.generatedDescription();
        final String shopUrlInput = GeneratedTestData.generatedUrl();
        final String priceInput = GeneratedTestData.generatedGiftPrice();
        final String imageUrlInput = GeneratedTestData.generatedUrl();
        WebElement cardTile;

        if(driver.findElements(By.cssSelector(".g-4.row > .col")).isEmpty()){
            logger.info("Creating new wishlist {}", listName);
            wishlistsPage.createNewListText();
            wishlistsPage.enterText("text", listName);
            wishlistsPage.createListButton();

            cardTile = wishlistsPage.waitForCardToBeVisible(listName);

        }else{
            cardTile = wishlistsPage.getFirstCardTileInfo();
            String cardTileText = cardTile.findElement(By.cssSelector(".card-title")).getText();
            logger.info("Got existing wishlist with name {}", cardTileText);
        }

        addGiftToWishlist(
                wishlistsPage,
                cardTile,
                wishlistNameInput,
                wishlistDescriptionInput,
                shopUrlInput,
                priceInput,
                imageUrlInput);

    }

    private void addGiftToWishlist(
            WishlistsPage wishlistsPage,
            WebElement cardTile,
            String wishlistNameInput,
            String wishlistDescriptionInput,
            String shopUrlInput,
            String priceInput,
            String imageUrlInput)
    {
        logger.info("Creating new gift");
        logger.info("Gift name: {}",wishlistNameInput);
        logger.info("Gift description: {}",wishlistDescriptionInput);
        logger.info("Shop URL: {}",shopUrlInput);
        logger.info("Gift price: {}",priceInput);
        logger.info("Image URL: {}",imageUrlInput);

        String cardTileText = cardTile.findElement(By.cssSelector(".card-title")).getText();
        String cardTileGiftsText = cardTile.findElement(By.cssSelector(".text-muted")).getText();
        int giftsNumber = Integer.parseInt(cardTileGiftsText.replaceAll("\\D+", ""));
        logger.info("Current gift number: {}", giftsNumber);

        WebElement checkButton = cardTile.findElement(By.cssSelector("button.btn.btn-primary"));
        checkButton.click();
        wishlistsPage.isModalTitleVisible();
        logger.info("Getting URL of wishlist");
        String wishlistUrl = URI.create(driver.getCurrentUrl()).getPath();
        WishlistGiftsPage wishlistGiftsPage = new WishlistGiftsPage(driver, wishlistUrl);

        wishlistGiftsPage.waitForWishlistPageToBeVisible();
        wishlistGiftsPage.addButtonToBeClickable().click();
        wishlistGiftsPage.fillWishlistForm(
                wishlistNameInput, wishlistDescriptionInput, shopUrlInput, priceInput, imageUrlInput);
        wishlistGiftsPage.addNewGiftButtonToBeClickable().click();
        WebElement createdGift = wishlistGiftsPage.getGiftByName(wishlistNameInput);
        String createdGiftName = createdGift.findElement(By.cssSelector(".card-title")).getText();
        String createdGiftDescription = createdGift.findElements(By.cssSelector(".card-text"))
                .getFirst().getText();
        String createdGiftPrice = createdGift.findElements(By.cssSelector(".card-text")).get(1).getText();
        String createdGiftImageUrl = createdGift.findElement(By.cssSelector("img")).getAttribute("src");
        String createdGiftShopUrl = createdGift.findElement(By.cssSelector("a.btn-outline-info"))
                .getAttribute("href");
        wishlistGiftsPage.returnToWishlistsPage();
        wishlistsPage.waitForCardToBeVisible(cardTileText);

        WebElement newCardTile = wishlistsPage.waitForCardToBeVisible(cardTileText);
        String newCardTileGiftsText = newCardTile.findElement(By.cssSelector(".text-muted")).getText();
        int newGiftsNumber = Integer.parseInt(newCardTileGiftsText.replaceAll("\\D+",""));
        logger.info("New gifts number: {}", newGiftsNumber);

        assertEquals(wishlistNameInput, createdGiftName);
        assertEquals(wishlistDescriptionInput, createdGiftDescription);
        assertEquals(shopUrlInput + "/", createdGiftShopUrl);
        assertEquals("Цена: " + priceInput + " руб.", createdGiftPrice);
        assertEquals(imageUrlInput + "/", createdGiftImageUrl);
        assertTrue(newGiftsNumber > giftsNumber);

    }
}
