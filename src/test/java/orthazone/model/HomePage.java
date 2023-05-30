package orthazone.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import orthazone.model.base.BaseModel;

public class HomePage extends BaseModel {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickAccountButton(){
        getDriver().findElement(By.className("y-header__user")).click();
        return this;
    }

    public AccountLoginPage clickLoginButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='y-header__user']//span[text()='Login']"))).click();
        return new AccountLoginPage(getDriver());
    }

    public AccountLogoutPage clickLogoutButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='y-header__user']//span[text()='Logout']"))).click();
        return new AccountLogoutPage(getDriver());
    }

    public String getCustomersName(){
        return getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[2]")).getText();
    }
}
