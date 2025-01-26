package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass {

    @Test(groups = {"Sanity", "Master"})
    public void verify_login() {
        logger.info("***** Starting the TC_02_LoginTest *****");
        try {
            //HomePage
            HomePage home = new HomePage(driver);
            home.clickMyAccount();
            logger.info("Clicked on My Account Link");
            home.clickLogin();
            logger.info("Clicked on Login Link");

            //LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setLoginemail(pro.getProperty("email"));
            lp.setLoginPassword(pro.getProperty("password"));
            lp.clickLoginBtn();
            logger.info("Clicked on Login Button");

            //My AccountPage
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPagePageExists();
            Assert.assertEquals(targetPage, true, "Login Failed");
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("***** Finished TC_02_LoginTest *****");
    }
}
