package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private By linkMyAccount = By.xpath("//span[normalize-space()='My Account']");
    private By linkRegsiter = By.xpath("//a[normalize-space()='Register']");
    private By linkLogin = By.xpath("//a[normalize-space()='Login']");

    //Methods
    public void clickMyAccount() {
        driver.findElement(linkMyAccount).click();
    }

    public void clickRegister() {
        driver.findElement(linkRegsiter).click();
    }

    public void clickLogin() {
        driver.findElement(linkLogin).click();
    }
}
