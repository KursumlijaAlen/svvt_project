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

public class UEPogledajGamingTest {
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
    public void TestGaming() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement GamingHover = webDriver.findElement(By.linkText("GAMING"));

        Actions actions = new Actions(webDriver);

        actions.moveToElement(GamingHover).perform();

        Thread.sleep(2000);



        WebElement AULAClick = webDriver.findElement(By.linkText("AULA"));
        AULAClick.click();

        Thread.sleep(2000);


        WebElement ProizvodClick = webDriver.findElement(By.xpath("//div[@id='page']/ul/li[2]/div/span/div[2]"));
        ProizvodClick.click();

        Thread.sleep(2000);

        WebElement Polja = webDriver.findElement(By.xpath("//*[@id=\"prodload\"]/div[1]/div/div/div/div/div[1]/div[2]/div/h1"));
        assertEquals("AULA Gaming MiÅ¡ RGB S18", Polja.getText());

        Thread.sleep(5000);
    }




    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
