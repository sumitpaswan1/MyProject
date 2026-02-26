package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	  public WebDriver driver;
	  public Logger logger;
	  
	  public Properties p; // properties file
	    

	  	@BeforeClass(groups={"Regression","Sanity","DataDriven","Master"})
	    @Parameters({"browser", "os"})
	    public void setUp(String br, String os) throws IOException {
	    	//loading config.properties file
	    	
			FileReader file = new FileReader("./src//test//resources/config.properties");
			//load file
			p = new Properties();
			p.load(file);
	    	
	    	switch(br.toLowerCase()) {
			case "chrome" :driver = new ChromeDriver();break;
			case "edge" : driver= new EdgeDriver();break;
			default:System.out.println("Invalid Browser");return;
			
			
			}
			
	        // WebDriverManager.chromedriver().setup(); // optional, recommended
	    	logger = LogManager.getLogger(this.getClass());
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get(p.getProperty("appUrl"));
	    }

	    @AfterClass(groups= {"Regression","Sanity","Master"})
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
