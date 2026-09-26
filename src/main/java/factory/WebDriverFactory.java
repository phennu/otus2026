package factory;

import exceptions.BrowserNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private static String browser = System.getProperty("browser");

    public static WebDriver create(String... arguments) {
        switch (browser.trim().toLowerCase()) {
            case "chrome": {
                return createChromeDriver(arguments);
            }
            case "firefox": {
                return createFirefoxDriver(arguments);
            }
            default: {
                throw new BrowserNotFoundException(browser);
            }
        }
    }

    private static WebDriver createChromeDriver(String... arguments) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(arguments);

        return new ChromeDriver(options);

    }

    private static WebDriver createFirefoxDriver(String... arguments) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(arguments);

        return new FirefoxDriver(options);

    }
}
