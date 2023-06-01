package orthazone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import orthazone.model.HomePage;
import orthazone.runner.BaseTest;

public class RegistrationTest extends BaseTest {

    @DataProvider(name = "wrong-email")
    public Object[][] provideWrongEmail(){
        return new Object[][]
                {{"!"}, {"@"}, {"$"}, {"123456789"}};
    }

    @Test(dataProvider = "wrong-email")
    public void testFieldsOnRegistrationPage(String wrongCharacters) {

        String errorMainPhone = new HomePage(getDriver())
                .clickAccountButton()
                .clickRegisterButton()
                .fillFieldByName("telephone", wrongCharacters)
                .scrollToNextButton()
                .clickNextButton()
                .getErrorMainPhone();

        Assert.assertEquals(errorMainPhone, "Main Phone Number must contain at least 10 digits, and can have plus, numbers, dashes and brackets!");
    }

    @Test
    public void testRegistrationPagePersonalAccount() throws InterruptedException {

        String Email = "autotest-new@orthazone.com";

        getDriver().findElement(By.className("y-header__user")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")));
        getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")).click();

        WebElement stepRegistrationInformation = getDriver().findElement(By.xpath("//div[@class='aform__head']"));
        Assert.assertEquals(stepRegistrationInformation.getText(),"Registration Information");

        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(Email);
        getDriver().findElement(By.xpath("//input[@name='telephone']")).sendKeys("1234567890");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//input[@name='confirm']")).sendKeys("123456789");
        WebElement clickPersonalAccount = getDriver().findElement(By.xpath("//label[@for='personal']"));
        clickPersonalAccount.click();

        Actions scroll = new Actions(getDriver());
        scroll.scrollByAmount(0,300).perform();

        WebElement nextButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--next']"));
        nextButton.click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-block-acc='personal']//input[@name='firstname']")));

        getDriver().findElement(By.xpath("//div[@data-block-acc='personal']//input[@name='firstname']")).sendKeys("test");
        getDriver().findElement(By.xpath("//div[@data-block-acc='personal']//input[@name='lastname']")).sendKeys("test");
        nextButton.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='aform__fieldwrap aform__fieldwrap--rect block_agree']//div")));

        Thread.sleep(2000);
        scroll.scrollByAmount(0,300).perform();

        WebElement checkboxPrivacyPolicy = getDriver().findElement(By.xpath("//div[@class='aform__fieldwrap aform__fieldwrap--rect block_agree']//div"));
        checkboxPrivacyPolicy.click();

        WebElement registerButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--send is_show']"));
        registerButton.click();

        WebElement confirmRegistration = getDriver().findElement(By.xpath("//div[@class='asteps__head']"));
        Assert.assertEquals(confirmRegistration.getText(),"YOUR ACCOUNT HAS BEEN CREATED!");
    }

    @Test
    public void testEmailValidationOnRegPage (){
        getDriver().findElement(By.className("y-header__user")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")));
        getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")).click();

        WebElement stepRegistrationInformation = getDriver().findElement(By.xpath("//div[@class='aform__head']"));
        Assert.assertEquals(stepRegistrationInformation.getText(),"Registration Information");

        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("autotest-old@orthazone.com");

        getDriver().findElement(By.xpath("//input[@name='telephone']")).sendKeys("1234567890");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//input[@name='confirm']")).sendKeys("123456789");
        WebElement clickPersonalAccount = getDriver().findElement(By.xpath("//label[@for='personal']"));
        clickPersonalAccount.click();

        Actions scroll = new Actions(getDriver());
        scroll.scrollByAmount(0,300).perform();

        WebElement nextButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--next']"));
        nextButton.click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']/../div[@class='aerror']")));
        WebElement warningMessage = getDriver().findElement(By.xpath("//input[@name='email']/../div[@class='aerror']"));
        Assert.assertEquals(warningMessage.getText(), "Warning: E-Mail Address is already registered!");
    }
}