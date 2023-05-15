package orthazone.runner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {

    private WebDriverWait wait2;
    private WebDriverWait wait5;
    private WebDriverWait wait10;
    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        startDriver();
        getWeb();
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
        BaseUtils.log("Browser open");
        driver = BaseUtils.createDriver();
    }

    protected void getWeb() {
        BaseUtils.log("Get web page");
        getDriver().get("https://www.orthazone.com/");
    }

    protected void stopDriver() {
        driver.quit();
        wait2 = null;
        wait5 = null;
        wait10 = null;
        BaseUtils.log("Browser closed");
    }

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait5;
    }

    protected WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }
        return wait2;
    }

    protected WebDriverWait getWait10() {
        if (wait10 == null) {
            wait10 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait10;
    }
}