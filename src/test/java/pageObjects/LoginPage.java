package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private By enterEmail = By.xpath("//input[@id='input-email']");
    private By enterPassword = By.xpath("//input[@id='input-password']");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By forgotPassword = By.xpath("(//a[contains(text(),'Forgotten Password')])[1]");

    //Methods
    public void setLoginemail(String email) {
        driver.findElement(enterEmail).sendKeys(email);
    }

    public void setLoginPassword(String password) {
        driver.findElement(enterPassword).sendKeys(password);
    }

    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }
}
