package orthazone.runner;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        startDriver();
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        stopDriver();
        BaseUtils.logf("Execution time is %o sec\n\n", (testResult.getEndMillis() - testResult.getStartMillis()) / 1000);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected void startDriver() {
        BaseUtils.log("Browser opened");
        driver = BaseUtils.createDriver();
    }

    protected void stopDriver() {
        driver.quit();
        BaseUtils.log("Browser closed");
    }

}