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

public class UENewsletterTest {
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
        email = "faris.leventa@stu.ibu.edu.ba";
    }


    @Test
    public void testUENewsletter() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        Actions actions = new Actions(webDriver);

        actions.scrollByAmount(0, 1700).perform();

        Thread.sleep(1000);

        WebElement newsletter = webDriver.findElement(By.id("EMAIL"));
        newsletter.click();
        newsletter.sendKeys(email);

        Thread.sleep(2000);

        WebElement subscribe = webDriver.findElement(By.name("subscribe"));
        subscribe.click();

        Thread.sleep(2000);

        String fail = webDriver.findElement(By.xpath("//*[@id=\"NLinput\"]/p[2]")).getText();
        assertEquals(fail, "Nećemo Vas spamovati!");

        Thread.sleep(2000);
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}