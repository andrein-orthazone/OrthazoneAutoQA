package orthazone;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import orthazone.runner.BaseTest;
import orthazone.runner.ProjectUtils;
import java.io.IOException;

public class RegistrationTest extends BaseTest {

    @DataProvider(name = "wrong-email")
    public Object[][] provideWrongEmail(){
        return new Object[][]
                {{"!"}, {"@"}, {"$"}};
    }

    @Test(dataProvider = "wrong-email")
    public void testFieldsOnRegistrationPage(String wrongCharacters) {

        getDriver().findElement(By.className("y-header__user")).click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")));
        getDriver().findElement(By.xpath("//div[@class='y-header__user']//span[@class='zbtn__txt']")).click();

        getDriver().findElement(By.xpath("//input[@name='telephone']")).sendKeys(wrongCharacters);

        new Actions(getDriver()). scrollByAmount(0,600).perform();

        WebElement nextButton = getDriver().findElement(By.xpath("//button[@class='r-btn abtn abtn--next']"));
        nextButton.click();

        WebElement assertErrorMainPhone = getDriver().findElement(By.xpath("//input[@name='telephone']/../div"));
        Assert.assertEquals(assertErrorMainPhone.getText(),"Main Phone Number must contain at least 10 digits, and can have plus, numbers, dashes and brackets!");
    }

    @Test
    public void testRegistrationPagePersonalAccount() throws IOException, InterruptedException {

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

        deleteAccount(Email);
    }

    public void deleteAccount(String Email) throws IOException {
        getDriver().get("https://www.dentazone.com/admin/index.php?route=sale/customer");
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='username']")));

        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys(ProjectUtils.getUserName());
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(ProjectUtils.getPassword());

        WebElement loginButton = getDriver().findElement(By.xpath("//a[@class='button']"));
        loginButton.click();

        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='filter_email']")));

        getDriver().findElement(By.xpath("//input[@name='filter_email']")).sendKeys(Email);
        new Actions(getDriver()).keyDown(Keys.ENTER).perform();

        int numbersOfCustomers = getDriver().findElements(By.xpath("//tbody//tr")).size();
        Assert.assertEquals(numbersOfCustomers, 2);

        WebElement identifyCustomer = getDriver().findElement(By.xpath("//tbody//tr[2]//td[4]"));
        Assert.assertEquals(identifyCustomer.getText(), Email);

        WebElement checkBoxCustomer = getDriver().findElement(By.xpath("//input[@name='selected[]']"));
        checkBoxCustomer.click();

        WebElement deleteButton = getDriver().findElement(By.xpath("//a[text()='Delete']"));
        deleteButton.click();

        Alert confirmDeleting = getDriver().switchTo().alert();
        confirmDeleting.accept();

        WebElement checkDeleting = getDriver().findElement(By.xpath("//div[@class='success']"));
        Assert.assertEquals(checkDeleting.getText(), "Success: You have modified customers!");
    }
}
