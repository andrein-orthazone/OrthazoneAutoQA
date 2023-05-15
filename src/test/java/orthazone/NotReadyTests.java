package orthazone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import orthazone.runner.BaseTest;

public class NotReadyTests extends BaseTest {

//    @Test
//    public void testLandingAAOGetMyFreeTrialPersonal(){
//
//        WebElement learnAboutAAOLandingButton = getDriver().findElement(By.xpath("//a[@class='z-aao__btn']"));
//        learnAboutAAOLandingButton.click();
//
//        WebElement getMyFreeTrialAccountButton = getDriver().findElement(By.xpath("//button[@data-name='access-section-1']"));
//        getMyFreeTrialAccountButton.click();
//
//        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='firstname']")));
//
//        getDriver().findElement(By.xpath("//input[@id='firstname']")).sendKeys("test");
//        getDriver().findElement(By.xpath("//input[@id='lastname']")).sendKeys("test");
//        getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys("autotest-new@orthazone.com");
//        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("123456789");
//        getDriver().findElement(By.xpath("//input[@id='conf-password']")).sendKeys("123456789");
//        getDriver().findElement(By.xpath("//input[@id='phone']")).sendKeys("12345678901");
//
//        WebElement registrationTypeSelect = getDriver().findElement(By.xpath("//select[@id='register-type']"));
//        Select select = new Select(registrationTypeSelect);
//        select.selectByIndex(4);
//
//        WebElement registerNowButton = getDriver().findElement(By.xpath("//span[@class='form__btn-text2']"));
//        registerNowButton.click();
//
//        WebElement checkRegistration = getDriver().findElement(By.xpath("//div[@class='popup registertext open']//div[@class='popup__title']"));
//        Assert.assertEquals(checkRegistration.getText(),"CONGRATS! YOU CAN NOW SHOP!");
//    }
}
