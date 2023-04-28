package orthazone;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import orthazone.runner.BaseTest;

public class RegistrationTest extends BaseTest {

    @Test
    public void testRegistrationPersonalAccount() throws InterruptedException {

        getDriver().findElement(By.className("y-header__user")).click();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")).click();

        WebElement stepRegistrationInformation = getDriver().findElement(By.xpath("//div[@class='aform__head']"));
        Assert.assertEquals(stepRegistrationInformation.getText(),"Registration Information");

        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("autotest-new@orthazone.com");
        getDriver().findElement(By.xpath("//input[@name='telephone']")).sendKeys("1234567890");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//input[@name='confirm']")).sendKeys("123456789");
        WebElement clickPersonalAccount = getDriver().findElement(By.xpath("//label[@for='personal']"));
        clickPersonalAccount.click();

        ((JavascriptExecutor)getDriver()).executeScript("window.scrollBy(0,300)");

//        Actions actions = new Actions(getDriver());
//        actions.moveToElement(getDriver().findElement(By.xpath("//div[@class='y-aao-row__head']"))).build().perform();
        WebElement nextButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--next']"));

        nextButton.click();
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//div[@data-block-acc='personal']//input[@name='firstname']")).sendKeys("test");
        getDriver().findElement(By.xpath("//div[@data-block-acc='personal']//input[@name='lastname']")).sendKeys("test");
        nextButton.click();

        //JavascriptExecutor js = (JavascriptExecutor) getDriver();
        //getDriver().get("http://demo.guru99.com/test/guru99home/");
        //js.executeScript("window.scrollBy(0,1500)");
        //js.executeScript("arguments[0].scrollIntoView();", getDriver().findElement(By.xpath("//div[@class='y-aao-row__head']")));
        //WebElement scroll = getDriver().findElement(By.xpath("//p[@style='text-align: left;']"));
        ((JavascriptExecutor)getDriver()).executeScript("window.scrollBy(0,600)");

        WebElement radioPrivacyPolicy = getDriver().findElement(By.xpath("//p[@style='text-align: left;']"));
        radioPrivacyPolicy.click();
//        WebElement registerButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--send is_show']"));
//        registerButton.click();
//
//        WebElement successRegistration = getDriver().findElement(By.xpath("//div[@class='asteps__head']"));
//        Assert.assertEquals(successRegistration,"YOUR ACCOUNT HAS BEEN CREATED!");


    }

    @Test
    public void testScroll(){
        WebElement scroll = getDriver().findElement(By.xpath("//div[@class='y-aao-row__head']"));
        ((JavascriptExecutor)getDriver()).executeScript("window.scrollBy(0,10000)");


    }

}
