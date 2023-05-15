package orthazone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import orthazone.runner.BaseTest;

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
}
