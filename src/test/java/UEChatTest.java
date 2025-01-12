import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UEChatTest {
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
    public void testChat() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(4000);

        WebElement chatOpen = webDriver.findElement(By.id("f84cenbq3vf81736703707117"));
        chatOpen.click();

        Thread.sleep(3000);

        WebElement inputMessage = webDriver.findElement(By.id("tawk-bubble-container"));
        inputMessage.click();
        inputMessage.sendKeys("Zdravo");
        inputMessage.sendKeys(Keys.ENTER);

        Thread.sleep(3000);


    }




    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}