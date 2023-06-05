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

    public RegistrationPage fillFieldByNameStepOne(String fieldName, String data){
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.name(fieldName)));
        getDriver().findElement(By.name(fieldName)).sendKeys(data);
        return this;
    }

    public RegistrationPage chooseRadioButtonByName (String radioButtonName){
        String xpath = "//label[@for='" + radioButtonName + "']";
        getDriver().findElement(By.xpath(xpath)).click();
        return this;
    }

    public RegistrationPage fillFieldByNameStepTwo(String accountType, String fieldName, String data){
        String xpath = "//div[@data-block-acc='" + accountType + "']//input[@name='" + fieldName + "']";
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(data);
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

    public RegistrationPage scrollAndClickCheckboxPrivacyPolicy() throws InterruptedException {
        Thread.sleep(2000);
        new Actions(getDriver()).scrollByAmount(0,600).perform();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='aform__fieldwrap aform__fieldwrap--rect block_agree']//div"))).click();
        return this;
    }

    public String getErrorMainPhone(){
        return getDriver().findElement(By.xpath("//input[@name='telephone']/../div")).getText();
    }

    public AccountCreationSuccessPage clickRegisterButton(){
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='r-btn abtn abtn--send is_show']"))).click();
        return new AccountCreationSuccessPage(getDriver());
    }
}
