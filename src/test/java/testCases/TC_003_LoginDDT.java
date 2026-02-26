package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{
	
	@Test(dataProvider ="LoginData" , dataProviderClass= DataProviders.class, groups = {"DDT"})// getting the dataProvider from different class
	public void verify_LoginDDT(String email, String pwd, String exp) throws InterruptedException {
		
		logger.info("***starting TC_003_LoginTest ***");
		//Home Page
		
				try {
				HomePage hp = new HomePage(driver); 
				
				hp.clickMyAccount();
				logger.info("Click on My Account Link......");
				hp.clickLogin();
				logger.info("Click on login ......");
				
				//Login Page 
				LoginPage lp = new LoginPage(driver);
				
				logger.info("Providing the login info......");
				lp.setEmail(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
				//MyAccount Page
				MyAccountPage ma = new MyAccountPage(driver);	
				boolean target = ma.isMyAccountPageExist();
				
				/* 
				 * Data is Valid -> login suceess -> Test Pass -> Logout
				 * 				 -> login failed  -> Test Failed
				 * 
				 * Data is InValid -> login suceess -> Test Fail -> Logout
				 * 				 -> login faild  -> Test pass
				 * 
				 * 
				
				
				*/
				if (exp.equalsIgnoreCase("Valid")) {
				    if (target == true) { // Login successful as expected
				        Assert.assertTrue(true);
				        ma.logoutButton();
				    } else { // Login failed unexpectedly
				        Assert.assertTrue(false, "Login failed for valid credentials!");
				    }
				}

				if (exp.equalsIgnoreCase("Invalid")) {
				    if (target == true) { // Login unexpectedly succeeded for invalid credentials
				        ma.logoutButton();
				        Assert.assertTrue(false, "Login succeeded with invalid credentials!");
				    } else { // Login failed as expected
				        Assert.assertTrue(true);
				    }
				}

				}catch(Exception e) {
					Assert.fail();
				}
				Thread.sleep(300);
				logger.info("***Finished TC_003_LoginTest ***");
				
	}
	

}
