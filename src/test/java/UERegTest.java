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

public class UERegTest {
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
        email = "faris.leventa+test4@stu.ibu.edu.ba";  // Svaki put inkrementirati
        ime = "Fareleva+test4"; // Svaki put inkrementirati
    }


    @Test
    public void testUEReg() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(2000);

        WebElement accountIcon = webDriver.findElement(By.id("signin2"));
        accountIcon.click();

        Thread.sleep(2000);

        WebElement inputEmail = webDriver.findElement(By.id("email"));
        inputEmail.click();
        inputEmail.sendKeys(email);

        Thread.sleep(2000);

        WebElement inputIme = webDriver.findElement(By.id("name"));
        inputIme.click();
        inputIme.sendKeys("Faris");

        Thread.sleep(2000);

        WebElement inputAdresa = webDriver.findElement(By.id("address"));
        inputAdresa.click();
        inputAdresa.sendKeys("Husage Cisica");

        Thread.sleep(2000);

        WebElement inputKorisnicko = webDriver.findElement(By.id("username"));
        inputKorisnicko.click();
        inputKorisnicko.sendKeys(ime);

        Thread.sleep(2000);

        WebElement inputPostBroj = webDriver.findElement(By.id("zip"));
        inputPostBroj.click();
        inputPostBroj.sendKeys("71000");

        Thread.sleep(2000);

        WebElement inputMjesto = webDriver.findElement(By.id("city"));
        inputMjesto.click();
        inputMjesto.sendKeys("Sarajevo");

        Thread.sleep(2000);

        WebElement inputPassword = webDriver.findElement(By.id("userpass"));
        inputPassword.click();
        inputPassword.sendKeys("fakultet");

        Thread.sleep(1000);

        WebElement inputTelefon = webDriver.findElement(By.id("phone"));
        inputTelefon.click();
        inputTelefon.sendKeys("061059131");

        Thread.sleep(1000);

        WebElement inputPassword2 = webDriver.findElement(By.id("userpass2"));
        inputPassword2.click();
        inputPassword2.sendKeys("fakultet");

        Thread.sleep(1000);

        WebElement signIn = webDriver.findElement(By.id("regsubmit"));
        signIn.click();

        Thread.sleep(5000);

        webDriver.navigate().refresh();

        Thread.sleep(2000);

        WebElement mojKonto = webDriver.findElement(By.linkText("MOJ KONTO"));
        mojKonto.click();

        Thread.sleep(2000);

        WebElement mojKontoTest = webDriver.findElement(By.tagName("h1"));
        assertEquals("Moj konto", mojKontoTest.getText());

        Thread.sleep(2000);
    }


    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

