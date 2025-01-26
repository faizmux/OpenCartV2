package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

import java.io.IOException;

public class TC01_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void verify_account_registration() throws IOException {

        logger.info("***** Starting TC01_AccountRegistrationTest *****");
        try {
            HomePage home = new HomePage(driver);
            home.clickMyAccount();
            logger.info("Clicked on My Account Link");
            home.clickRegister();
            logger.info("Clicked on Register Link");

            RegistrationPage registerpage = new RegistrationPage(driver);

            logger.info("Providing customer details....");
            registerpage.setFirstName(randomString().toUpperCase());
            registerpage.setLastName(randomString().toUpperCase());
            registerpage.setEmail(randomString() + "@gmail.com");
            registerpage.setTelephone(randomNumber());

            String passWord = randomPassword();
            registerpage.setPassWord(passWord);
            registerpage.setConfirmPassword(passWord);

            registerpage.setPrivacyPolicy();
            registerpage.clickContinue();

            logger.info("Validating expected message...");
            String confmsg = registerpage.getConfirmationMessage();
            if (confmsg.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            } else {
                logger.error("Test Case Failed...");
                logger.debug("Debug Logs...");
                Assert.fail();
            }
        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("***** Finished TC01_AccountRegistrationTest *****");
    }
}
