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

public class UEPogledajTest {
    private static WebDriver webDriver;
    private static String baseUrl;

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
    }

    @Test
    public void testUEPogledaj() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement PogledajHover = webDriver.findElement(By.cssSelector("h3.kw-details-title"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(PogledajHover).perform();

        Thread.sleep(2000);

        WebElement PogledajClick = webDriver.findElement(By.linkText("POGLEDAJ"));
        PogledajClick.click();

        Thread.sleep(2000);


    }




    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}