package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private By inputFirstName = By.xpath("//input[@id='input-firstname']");
    private By inputLastName = By.xpath("//input[@id='input-lastname']");
    private By inputEmail = By.xpath("//input[@id='input-email']");
    private By inputTelephone = By.xpath("//input[@id='input-telephone']");
    private By inputPassWord = By.xpath("//input[@id='input-password']");
    private By inputCnfPassWord = By.xpath("//input[@id='input-confirm']");
    private By agreeToggle = By.xpath("//input[@name='agree']");
    private By continueBtn = By.xpath("//input[@value='Continue']");
    private By msgConfirmation = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");


    //Methods
    public void setFirstName(String firstName) {
        driver.findElement(inputFirstName).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    public void setEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    public void setTelephone(String telephone) {
        driver.findElement(inputTelephone).sendKeys(telephone);
    }

    public void setPassWord(String passWord) {
        driver.findElement(inputPassWord).sendKeys(passWord);
    }

    public void setConfirmPassword(String cnfPassword) {
        driver.findElement(inputCnfPassWord).sendKeys(cnfPassword);
    }

    public void setPrivacyPolicy() {
        driver.findElement(agreeToggle).click();
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }


    public String getConfirmationMessage() {
        try {
            return (driver.findElement(msgConfirmation).getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

}
