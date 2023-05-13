package orthazone;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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

        new Actions(getDriver()). scrollByAmount(0,300).perform();
//        ((JavascriptExecutor)getDriver()).executeScript("window.scrollBy(0,300)");
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
        //((JavascriptExecutor)getDriver()).executeScript("window.scrollBy(0,600)");

        //new Actions(getDriver()). scrollByAmount(0,3000).perform();

        WebElement minimizeBottomMenu = getDriver().findElement(By.xpath("//button[@class='y-btn y-menu-sticky__btn-state']"));
        minimizeBottomMenu.click();

        WebElement mini = getDriver().findElement(By.xpath("//label[@for='engine']"));
        Assert.assertEquals(mini.getText(),"Search engine");


//        WebElement radioPrivacyPolicy = getDriver().findElement(By.xpath("//p[@style='text-align: left;']/../.."));
//        radioPrivacyPolicy.click();
//        WebElement registerButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--send is_show']"));
//        registerButton.click();
//
//        WebElement successRegistration = getDriver().findElement(By.xpath("//div[@class='asteps__head']"));
//        Assert.assertEquals(successRegistration,"YOUR ACCOUNT HAS BEEN CREATED!");


    }

    @DataProvider(name = "wrong-email")
    public Object[][] provideWrongEmail(){
        return new Object[][]
                {{"!"}, {"@"}, {"$"}};
    }

    @Test(dataProvider = "wrong-email")
    public void testFieldsOnRegistrationPage(String wrongCharacters) throws InterruptedException {

        getDriver().findElement(By.className("y-header__user")).click();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")));
        getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")).click();

        getDriver().findElement(By.xpath("//input[@name='telephone']")).sendKeys(wrongCharacters);

        new Actions(getDriver()). scrollByAmount(0,600).perform();

        WebElement nextButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--next']"));
        nextButton.click();

        WebElement assertErrorMainPhone = getDriver().findElement(By.xpath("//input[@name='telephone']/../div"));
        Assert.assertEquals(assertErrorMainPhone.getText(),"Main Phone Number must contain at least 10 digits, and can have plus, numbers, dashes and brackets!");
    }
}
