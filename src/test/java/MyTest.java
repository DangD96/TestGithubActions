import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        // The name of the Docker selenium hub service is used as the hostname in the URL to access the Selenium Grid Hub
        driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);
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
    }
}
