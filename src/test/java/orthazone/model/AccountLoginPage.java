package orthazone.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import orthazone.model.base.BaseModel;

public class AccountLoginPage extends BaseModel {

    public AccountLoginPage(WebDriver driver) {
        super(driver);
    }

    public AccountLoginPage fillFieldByName(String fieldName, String data){
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name(fieldName)));
        getDriver().findElement(By.name(fieldName)).sendKeys(data);
        return this;
    }

    public HomePage clickLoginButton(){
        getDriver().findElement(By.xpath("//button[text()='Login']")).click();
        return new HomePage(getDriver());
    }
}
