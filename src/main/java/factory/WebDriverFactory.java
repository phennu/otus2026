package factory;

import exceptions.BrowserNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private static String browser = System.getProperty("browser");

    public static WebDriver create(String... arguments){
        switch (browser){
            case "chrome":{
                return createChromeDriver(arguments);
            }
            default:{
                throw  new BrowserNotFoundException(browser);
            }
        }
    }

    private static WebDriver createChromeDriver(String... arguments){
        ChromeOptions options = new ChromeOptions();
        options.addArguments(arguments);

        return new ChromeDriver(options);

    }
}
