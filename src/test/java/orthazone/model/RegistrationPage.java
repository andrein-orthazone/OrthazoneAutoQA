package orthazone.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import orthazone.model.base.BaseModel;

public class RegistrationPage extends BaseModel {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage fillFieldByName(String fieldName, String data){
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name(fieldName)));
        getDriver().findElement(By.name(fieldName)).sendKeys(data);
        return this;
    }

    public RegistrationPage scrollToNextButton(){
        new Actions(getDriver()).scrollByAmount(0,600).perform();
        return this;
    }

    public RegistrationPage clickNextButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='r-btn abtn abtn--next']"))).click();
        return new RegistrationPage(getDriver());
    }

    public String getErrorMainPhone(){
        return getDriver().findElement(By.xpath("//input[@name='telephone']/../div")).getText();
    }
}
