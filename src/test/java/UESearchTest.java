import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UESearchTest {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static String email;
    private static String ime;


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
        email = "faris.leventa+test2@stu.ibu.edu.ba";  // Svaki put inkrementirati
        ime = "Fareleva+test2"; // Svaki put inkrementirati
    }


    @Test
    public void testSearch() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement SearchIcon = webDriver.findElement(By.id("search"));
        SearchIcon.click();

        Thread.sleep(2000);

        WebElement inputSearch = webDriver.findElement(By.name("searchfor"));
        inputSearch.click();
        inputSearch.sendKeys("laptop");

        Thread.sleep(2000);

        WebElement inputSearchClick = webDriver.findElement(By.id("searchsubmit"));
        inputSearchClick.click();


        Thread.sleep(2000);

        WebElement inputLaptop = webDriver.findElement(By.cssSelector(".product:nth-child(3)"));
        inputLaptop.click();

        Thread.sleep(2000);

        WebElement proizvodTest = webDriver.findElement(By.cssSelector("h2.fs-18"));
        assertEquals("Tehnička specifikacija", proizvodTest.getText());

        Thread.sleep(2000);
        }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

