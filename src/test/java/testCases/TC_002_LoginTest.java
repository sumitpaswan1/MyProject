package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
	
	//usename and password read from properties file
	@Test(groups = {"Sanity","Master"})
	public void verify_Login() {
		
		logger.info("***starting TC_002_LoginTest ***");
		
		try {
		//Home Page
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Click on My Account Link......");
		hp.clickLogin();
		logger.info("Click on login ......");
		
		//Login Page 
		LoginPage lp = new LoginPage(driver);
		
		logger.info("Providing the login info......");
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount Page
		MyAccountPage ma = new MyAccountPage(driver);
		
		boolean target = ma.isMyAccountPageExist();
		//Assert.assertEquals(target, true,"Login failed");
		Assert.assertTrue(target);
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}
		
		logger.info("***Finished TC_002_LoginTest ***");
	}
		
}
	
	
	


