package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.B1;
import testBase.BaseClass;

public class TC_001 extends BaseClass {

    @Test
    public void userRegistration() {
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickRegister();

            AccountRegistrationPage ac = new AccountRegistrationPage(driver);

            ac.setFirstName(randomeString().toUpperCase());
            ac.setLastName(randomeString().toUpperCase());
            ac.setEmail(randomeString() + "@gmail.com");
            ac.setTelephone(randomeNumber());

            String psw = randomeAlphaNumeric();
            ac.setPassword(psw);
            ac.setConfirmPassword(psw);

            ac.setPrivacyPolicy();
            ac.clickContinue();

            String mess = ac.getConfirmationMsg();
            Assert.assertNotNull(mess, "Confirmation message is null");
            Assert.assertEquals(mess.trim(), "Your Account Has Been Created!",
                    "Account creation confirmation message mismatch! Found: " + mess);

        } catch (Exception e) {
            Assert.fail("Test failed due to exception: " + e.getMessage(), e);
        }
    }

 
}