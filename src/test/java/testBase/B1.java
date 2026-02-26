package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.Logger; //Log4j
import org.apache.logging.log4j.LogManager; //Log4j
// Optional:
// import io.github.bonigarcia.wdm.WebDriverManager;

public class B1 {

    public WebDriver driver;
    public Logger logger;

    @BeforeClass
    public void setUp() {
        // WebDriverManager.chromedriver().setup(); // optional, recommended
    	logger = LogManager.getLogger(this.getClass());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/");
    }

    @AfterClass
    public void quite() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    // Utils
    public String randomeString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomeNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomeAlphaNumeric() {
        String letters = RandomStringUtils.randomAlphabetic(5);
        String digits = RandomStringUtils.randomNumeric(5); // keep password reasonable length
        return letters + "@" + digits;
    }
}