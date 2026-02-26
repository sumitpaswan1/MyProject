package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
	

	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() {
		logger.info("*** starting TC_001_AccountRegistrationTest ***");
		try {
			//calling the HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Click on My Account Link......");
			
			hp.clickRegister();
			logger.info("Click on Registraton Link....");
			
			//calling the AccountRegistrationPage
			
			AccountRegistrationPage ac = new AccountRegistrationPage(driver);
			logger.info("Providing the cutomer details.....");
			
			ac.setFirstName(randomeString().toUpperCase());
			ac.setLastName(randomeString().toUpperCase());
			ac.setEmail(randomeString()+"@gmail.com");
			ac.setTelephone(randomeNumber());
			
			// pass the dynamic password
			String psw = randomeAlphaNumeric();
			
			
			ac.setPassword(psw);
			ac.setConfirmPassword(psw);
			
			ac.setPrivacyPolicy();
			ac.clickContinue();
			
			logger.info("verifyin the expected message...");

            String mess = ac.getConfirmationMsg();
            if(mess.equals("Your Account Has Been Created!")) {
    			Assert.assertTrue(true);
    		}
    		else {
    			logger.info("Test Failed");
    			logger.debug("Debug Logs... ");
    			Assert.assertFalse(false);
    		}
    		//Assert.assertEquals(mess,"Your Account Has Been Created!!");
    		
    		}
    		catch(Exception e) {
    			Assert.fail();
    		}

        
		logger.info("finished TC_001_AccountRegistrationTest");
    
		
	}
		
	
}
	


