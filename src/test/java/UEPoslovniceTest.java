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

public class UEPoslovniceTest {
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
    public void testPoslovnice() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);


        Actions actions = new Actions(webDriver);

        actions.scrollByAmount(0, 1500).perform();


        Thread.sleep(3000);

        WebElement poslovnicaClick = webDriver.findElement(By.linkText("prodajnih mjesta"));
        poslovnicaClick.click();

        Thread.sleep(3000);

        WebElement nameClick = webDriver.findElement(By.id("cf_name"));
        nameClick.click();
        nameClick.sendKeys("Faris");

        Thread.sleep(3000);


        WebElement surnameClick = webDriver.findElement(By.id("cf_lastname"));
        surnameClick.click();
        surnameClick.sendKeys("Leventa");

        Thread.sleep(3000);

        WebElement emailClick = webDriver.findElement(By.id("cf_email"));
        emailClick.click();
        emailClick.sendKeys("faris.leventa@stu.ibu.edu.ba");

        Thread.sleep(3000);

        WebElement subjectClick = webDriver.findElement(By.id("cf_subject"));
        subjectClick.click();
        subjectClick.sendKeys("lorem ipsum...");

        Thread.sleep(3000);

        WebElement messageClick = webDriver.findElement(By.id("cf_message"));
        messageClick.click();
        messageClick.sendKeys("lorem ipsum..");

        Thread.sleep(3000);
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}