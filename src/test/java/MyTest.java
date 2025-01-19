import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        String env = System.getProperty("env");
        ChromeOptions options = new ChromeOptions();
        if ("local".equals(env)) {driver = new ChromeDriver(options);}
        else {
            // The name of the Docker selenium hub service is used as the hostname in the URL to access the Selenium Grid Hub
            driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);
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
        System.out.println("DID IT WORK");
    }
}
