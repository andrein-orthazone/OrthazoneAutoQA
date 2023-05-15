package orthazone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import orthazone.runner.BaseTest;

public class NotReadyTests extends BaseTest {

    @Test
    public void testRegistrationPersonalAccount() throws InterruptedException {

        getDriver().findElement(By.className("y-header__user")).click();
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")));
        getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")).click();

        WebElement stepRegistrationInformation = getDriver().findElement(By.xpath("//div[@class='aform__head']"));
        Assert.assertEquals(stepRegistrationInformation.getText(),"Registration Information");

        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("autotest-new@orthazone.com");
        getDriver().findElement(By.xpath("//input[@name='telephone']")).sendKeys("1234567890");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//input[@name='confirm']")).sendKeys("123456789");
        WebElement clickPersonalAccount = getDriver().findElement(By.xpath("//label[@for='personal']"));
        clickPersonalAccount.click();

        Actions scroll = new Actions(getDriver());
        scroll.scrollByAmount(0,300).perform();
        WebElement nextButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--next']"));

        nextButton.click();
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//div[@data-block-acc='personal']//input[@name='firstname']")).sendKeys("test");
        getDriver().findElement(By.xpath("//div[@data-block-acc='personal']//input[@name='lastname']")).sendKeys("test");
        nextButton.click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='aform__h50 ml-20']//p[@class='atitle atitle--bold']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class='aform__h50 ml-20']//p[@class='atitle atitle--bold']")).getText(),"How did");



//        WebElement checkboxPrivacyPolicy = getDriver().findElement(By.xpath("//div[@class='aform__fieldwrap aform__fieldwrap--rect block_agree']//label"));
//        checkboxPrivacyPolicy.click();
//
//        WebElement registerButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--send is_show']"));
//        registerButton.click();

    }
}
