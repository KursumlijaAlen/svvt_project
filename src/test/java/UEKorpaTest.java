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

public class UEKorpaTest {
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
    public void testUEKorpa() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement accountIcon = webDriver.findElement(By.id("signin2"));
        accountIcon.click();

        Thread.sleep(2000);

        WebElement inputEmail = webDriver.findElement(By.id("username2"));
        inputEmail.click();
        inputEmail.sendKeys(email);

        Thread.sleep(1000);

        WebElement inputPassword = webDriver.findElement(By.id("userpassx"));
        inputPassword.click();
        inputPassword.sendKeys("fakultet");

        Thread.sleep(1000);

        WebElement signIn = webDriver.findElement(By.id("signin"));
        signIn.click();

        Thread.sleep(2000);

        webDriver.navigate().refresh();


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

        WebElement inputKorpa = webDriver.findElement(By.cssSelector(".glyphicon-shopping-cart"));
        inputKorpa.click();

        Thread.sleep(2000);


        WebElement KorpaHover = webDriver.findElement(By.linkText("MOJA KORPA")); // Update with actual button's locator

        // Create an Actions instance
        Actions actions = new Actions(webDriver);

        // Perform hover action
        actions.moveToElement(KorpaHover).perform();

        Thread.sleep(2000);

        WebElement inputPogledaj = webDriver.findElement(By.cssSelector(".hidden-xs:nth-child(1)"));
        inputPogledaj.click();

        Thread.sleep(2000);

        WebElement korpatest = webDriver.findElement(By.cssSelector(".fs-34"));
        assertEquals("VAŠA KORPA", korpatest.getText());

        Thread.sleep(2000);
    }




    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}