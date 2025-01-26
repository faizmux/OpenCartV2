package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private By msgHeading = By.xpath("//h2[contains(text(),'My Account')]");
    private By logoutBtn = By.xpath("//div[@class='list-group']//a[text()='Logout']");

    //Methods
    public boolean isMyAccountPagePageExists() {
        try {
            return (driver.findElement(msgHeading).isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        driver.findElement(logoutBtn).click();
    }


}
