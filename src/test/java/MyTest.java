import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class MyTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        String env = System.getProperty("env");
        String browser = System.getProperty("browser");
        switch (browser) {
            case "Chrome":
                ChromeOptions options = new ChromeOptions();
                if ("local".equals(env)) {driver = new ChromeDriver(options);}
                else {
                    // The name of the Docker selenium hub service is used as the hostname in the URL to access the Selenium Grid Hub
                    driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);
                }
                break;
            case "Edge":
                EdgeOptions options2 = new EdgeOptions();
                if ("local".equals(env)) {driver = new EdgeDriver(options2);}
                else {
                    // The name of the Docker selenium hub service is used as the hostname in the URL to access the Selenium Grid Hub
                    driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options2);
                }
                break;
            case "Firefox":
                FirefoxOptions options3 = new FirefoxOptions();
                if ("local".equals(env)) {driver = new FirefoxDriver(options3);}
                else {
                    // The name of the Docker selenium hub service is used as the hostname in the URL to access the Selenium Grid Hub
                    driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options3);
                }
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        driver.manage().window().maximize();
    }

    @Test
    public void testMethod() {
        driver.get("https://www.google.com");
        Assert.assertEquals(driver.getTitle(), "Google");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        System.out.println("I JUST RAN ON: " + System.getProperty("browser").toUpperCase());
    }
}
