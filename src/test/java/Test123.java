
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Test123 {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alen2\\OneDrive\\Desktop\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://eu.puma.com/de/en/home";
    }
}
