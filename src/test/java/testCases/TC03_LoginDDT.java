package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid - login is success - test passed - logout
Data is valid -- login failed -- test fail
Data is invalid -- login success - test fail -- logout
Data is invalid -- login fail - test pass
 */
public class TC03_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
    // getting data provider from different class
    public void verify_loginDDT(String email, String password, String exp) throws InterruptedException {

        logger.info("***** Starting TC_03_LoginDDT *****");
        try {
            //HomePage
            HomePage home = new HomePage(driver);
            home.clickMyAccount();
            logger.info("Clicked on My Account Link");
            home.clickLogin();
            logger.info("Clicked on Login Link");

            //LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setLoginemail(email);
            lp.setLoginPassword(password);
            lp.clickLoginBtn();
            logger.info("Clicked on Login Button");

            //My AccountPage
            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPagePageExists();

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    macc.clickLogout();
                    logger.info("Clicked on logout button");
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    macc.clickLogout();
                    logger.info("Click on logout button");
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
        finally {
            logger.info("***** Finished TC_03_LoginDDT *****");
        }
        Thread.sleep(2000);
    }
}
