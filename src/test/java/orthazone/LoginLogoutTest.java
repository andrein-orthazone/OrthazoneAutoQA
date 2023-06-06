package orthazone;

import org.testng.Assert;
import org.testng.annotations.Test;
import orthazone.model.HomePage;
import orthazone.runner.BaseTest;

public class LoginLogoutTest extends BaseTest {

    @Test
    public void testLoginLogout() {

        String customersNameAfterLogin = new HomePage(getDriver())
                .getHeader()
                .clickAccountButton()
                .clickLoginButton()
                .fillFieldByName("email", "autotest-old@orthazone.com")
                .fillFieldByName("password", "123456789")
                .clickLoginButton()
                .getHeader()
                .getCustomersName();

        Assert.assertEquals(customersNameAfterLogin, "Auto");

        String textOnLogoutPage = new HomePage(getDriver())
                .getHeader()
                .clickAccountButton()
                .clickLogoutButton()
                .getTextOnLogoutPage();

        Assert.assertEquals(textOnLogoutPage, "ACCOUNT LOGOUT");
    }
}
