package orthazone.runner;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.io.*;
import java.util.Properties;

public class ProjectUtils {

    private static final String PREFIX_PROP = "local.";

    private static final String PROP_ADMIN_USERNAME = PREFIX_PROP + "admin.username";

    private static final String PROP_ADMIN_PAS = PREFIX_PROP + "admin.password";

    public static String getUserName() throws IOException {
        FileReader reader = new FileReader("src/test/resources/local.properties");
        Properties properties = new Properties();
        properties.load(reader);
        return properties.getProperty(PROP_ADMIN_USERNAME);
    }

    public static String getPassword() throws IOException {
        FileReader reader = new FileReader("src/test/resources/local.properties");
        Properties properties = new Properties();
        properties.load(reader);
        return properties.getProperty(PROP_ADMIN_PAS);
    }

    public static void deleteAccount(BaseTest baseTest) throws IOException {
        baseTest.getDriver().get("https://www.dentazone.com/admin/index.php?route=sale/customer&token=15ff1e775467e6db693f949f5eb9bc98&filter_email=autotest-new%40orthazone.com");
        baseTest.getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='username']")))
                .sendKeys(ProjectUtils.getUserName());
        baseTest.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(ProjectUtils.getPassword());

        WebElement loginButton = baseTest.getDriver().findElement(By.xpath("//a[@class='button']"));
        loginButton.click();

        baseTest.getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Delete']")));

        int numbersOfCustomers = baseTest.getDriver().findElements(By.xpath("//tbody//tr")).size();
        Assert.assertEquals(numbersOfCustomers, 2);

        if (!baseTest.getDriver().findElements(By.xpath("//input[@name='selected[]']")).isEmpty()) {
            WebElement checkBoxCustomer = baseTest.getDriver().findElement(By.xpath("//input[@name='selected[]']"));
            checkBoxCustomer.click();

            WebElement deleteButton = baseTest.getDriver().findElement(By.xpath("//a[text()='Delete']"));
            deleteButton.click();

            Alert confirmDeleting = baseTest.getDriver().switchTo().alert();
            confirmDeleting.accept();

            WebElement checkDeleting = baseTest.getDriver().findElement(By.xpath("//div[@class='success']"));
            Assert.assertEquals(checkDeleting.getText(), "Success: You have modified customers!");
        }
    }
}