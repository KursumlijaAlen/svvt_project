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

public class UEBrzaKupovinaTest {
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
    public void testUEBrzaKupovina() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        Actions actions = new Actions(webDriver);

        actions.scrollByAmount(0, 1500).perform();

        Thread.sleep(1000);

        WebElement Proizvod = webDriver.findElement(By.cssSelector(".related:nth-child(2) .product:nth-child(1)"));
        Proizvod.click();

        Thread.sleep(3000);

        WebElement Brza = webDriver.findElement(By.cssSelector(".button:nth-child(2)"));
        Brza.click();

        Thread.sleep(3000);

        WebElement brzaKupovina = webDriver.findElement(By.cssSelector(".col-sm-12 > .button"));
        brzaKupovina.click();

        WebElement Polja = webDriver.findElement(By.id("upozorenje"));
        assertEquals("Neka polja nisu unesena!", Polja.getText());

        Thread.sleep(5000);

    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}