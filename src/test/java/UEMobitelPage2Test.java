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

public class UEMobitelPage2Test {
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
    public void testMobitel2Page() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement Mobitel = webDriver.findElement(By.linkText("MOBITELI"));
        Mobitel.click();

        Thread.sleep(2000);

        Actions actions = new Actions(webDriver);

        actions.scrollByAmount(0, 1500).perform();


        Thread.sleep(3000);

        WebElement page2 = webDriver.findElement(By.cssSelector(".paging:nth-child(3) > span"));
        page2.click();

        Thread.sleep(3000);

        WebElement proizvodMobitel = webDriver.findElement(By.cssSelector(".kw-details-title"));
        proizvodMobitel.click();

        Thread.sleep(3000);

        WebElement proizvodTest = webDriver.findElement(By.xpath("//*[@id=\"prodload\"]/div[1]/div/div/div/div/div[1]/div[2]/div/h1"));
        assertEquals("Xiaomi Smartphone Redmi Note 12S 8/256 Green", proizvodTest.getText());
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}