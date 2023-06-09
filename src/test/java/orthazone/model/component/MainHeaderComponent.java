package orthazone.model.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import orthazone.model.AccountLoginPage;
import orthazone.model.AccountLogoutPage;
import orthazone.model.RegistrationPage;
import orthazone.model.base.BaseHeaderComponent;

public class MainHeaderComponent extends BaseHeaderComponent {

    public MainHeaderComponent(WebDriver driver) {
        super(driver);
    }

    public MainHeaderComponent clickAccountButton() {
        getDriver().findElement(By.className("y-header__user")).click();
        return this;
    }

    public AccountLoginPage clickLoginButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='y-header__user']//span[text()='Login']"))).click();
        return new AccountLoginPage(getDriver());
    }

    public AccountLogoutPage clickLogoutButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='y-header__user']//span[text()='Logout']"))).click();
        return new AccountLogoutPage(getDriver());
    }

    public RegistrationPage clickRegisterButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']"))).click();
        return new RegistrationPage(getDriver());
    }

    public String getCustomersName(){
        return getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[2]")).getText();
    }
}
