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

public class UEUsloviTest {
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
    }


    @Test
    public void testUEUslovi() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        Actions actions = new Actions(webDriver);

        actions.scrollByAmount(0, 1700).perform();

        Thread.sleep(1000);

        WebElement poslovanje = webDriver.findElement(By.xpath("//*[@id=\"footer\"]/div/div[1]/div[2]/div/div[2]/strong/a"));
        poslovanje.click();

        Thread.sleep(2000);

        String titl = webDriver.findElement(By.cssSelector("h1")).getText();
        assertEquals(titl, "Opšti uslovi poslovanja");

        Thread.sleep(2000);
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}