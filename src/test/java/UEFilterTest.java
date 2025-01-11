import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UEFilterTest {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static String email;

    @BeforeAll
    public static void setUp() {
        Dotenv dotenv = Dotenv.load();
        String webDriverPath = dotenv.get("WEBDRIVER_URL");
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.ue.ba/";
        email = "faris.leventa@stu.ibu.edu.ba";  // Replace with a valid Adidas email
    }

    @Test
    public void testUELogin() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(1000);

        WebElement KorpaHover = webDriver.findElement(By.linkText("RAČUNARI")); // Update with actual button's locator

        Actions actions = new Actions(webDriver);

        actions.moveToElement(KorpaHover).perform();

        Thread.sleep(1000);

        WebElement notebookclick = webDriver.findElement(By.cssSelector(".col-sm-3:nth-child(1) li:nth-child(1) > a"));
        notebookclick.click();

        Thread.sleep(1000);

        WebElement Filter1 = webDriver.findElement(By.cssSelector("ul:nth-child(1) > label:nth-child(3)"));
        Filter1.click();

        Thread.sleep(1000);

        WebElement Filter2 = webDriver.findElement(By.cssSelector("li:nth-child(5) label"));
        Filter2.click();

        Thread.sleep(1000);

        WebElement Filter3 = webDriver.findElement(By.name("177[]"));
        Filter3.click();

        Thread.sleep(1000);

        WebElement Laptop = webDriver.findElement(By.cssSelector("li.product"));
        Laptop.click();

        Thread.sleep(1000);

        WebElement Laptopime = webDriver.findElement(By.xpath("//*[@id=\"prodload\"]/div[1]/div/div/div/div/div[1]/div[2]/div/h1"));
        assertEquals("APPLE MacBook Air 2024 laptop MRXT3LL/A", Laptopime.getText());

        Thread.sleep(5000);


    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}