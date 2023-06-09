package orthazone;
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
                .getHeader()
                .clickAccountButton()
                .clickRegisterButton()
                .fillFieldByNameStepOne("telephone", wrongCharacters)
                .scrollToNextButton()
                .clickNextButton()
                .getErrorMainPhone();

        Assert.assertEquals(errorMainPhone, "Main Phone Number must contain at least 10 digits, and can have plus, numbers, dashes and brackets!");
    }

    @Test
    public void testRegistrationPagePersonalAccount() throws InterruptedException {

        String customerEmail = "autotest-new@orthazone.com";

        String confirmRegistration = new HomePage(getDriver())
                .getHeader()
                .clickAccountButton()
                .clickRegisterButton()
                .fillFieldByNameStepOne("email", customerEmail)
                .fillFieldByNameStepOne("telephone", "1234567890")
                .fillFieldByNameStepOne("password", "123456789")
                .fillFieldByNameStepOne("confirm", "123456789")
                .chooseRadioButtonByName("personal")
                .scrollToNextButton()
                .clickNextButton()
                .fillFieldByNameStepTwo("personal", "firstname", "test")
                .fillFieldByNameStepTwo("personal", "lastname", "test")
                .clickNextButton()
                .scrollAndClickCheckboxPrivacyPolicy()
                .clickRegisterButton()
                .getTextOnAccountCreationSuccessPage();

        Assert.assertEquals(confirmRegistration, "YOUR ACCOUNT HAS BEEN CREATED!");
    }

    @Test
    public void testEmailValidationOnRegPage (){

        String errorMessageEmailAlreadyRegistered = new HomePage(getDriver())
                .getHeader()
                .clickAccountButton()
                .clickRegisterButton()
                .fillFieldByNameStepOne("email", "autotest-old@orthazone.com")
                .fillFieldByNameStepOne("telephone", "1234567890")
                .fillFieldByNameStepOne("password", "123456")
                .fillFieldByNameStepOne("confirm", "123456")
                .chooseRadioButtonByName("personal")
                .scrollToNextButton()
                .clickNextButton()
                .getErrorMessageByFieldName("email");

        Assert.assertEquals(errorMessageEmailAlreadyRegistered, "Warning: E-Mail Address is already registered!");
    }
    @Test
    public void testRegistrationMedicalAccountNotOrthodontist () throws InterruptedException {

        String customerEmail = "autotest-new@orthazone.com";

        String confirmRegistration = new HomePage(getDriver())
                .getHeader()
                .clickAccountButton()
                .clickRegisterButton()
                .fillFieldByNameStepOne("email", customerEmail)
                .fillFieldByNameStepOne("telephone", "1234567890")
                .fillFieldByNameStepOne("password", "123456789")
                .fillFieldByNameStepOne("confirm", "123456789")
                .chooseRadioButtonByName("medical")
                .scrollToNextButton()
                .clickNextButton()
                .fillFieldByNameStepTwo("medical", "firstname", "test")
                .fillFieldByNameStepTwo("medical", "lastname", "test")
                .registrationTypeSelect()
                .fillFieldByNameStepTwo("medical", "license_holder", "aaa")
                .fillFieldByNameStepTwo("medical", "license_number", "123")
                .fillFieldByNameStepTwo("medical", "state_license", "ll")
                .scrollToNextButton()
                .clickNextButton()
                .scrollAndClickCheckboxPrivacyPolicy()
                .clickRegisterButton()
                .getTextOnAccountCreationSuccessPage();

        Assert.assertEquals(confirmRegistration, "YOUR ACCOUNT HAS BEEN CREATED!");
    }
    @Test
    public void testRegistrationPageBusinessAccount() throws InterruptedException {

        String customerEmail = "autotest-new@orthazone.com";

        String confirmRegistration = new HomePage(getDriver())
                .getHeader()
                .clickAccountButton()
                .clickRegisterButton()
                .fillFieldByNameStepOne("email", customerEmail)
                .fillFieldByNameStepOne("telephone", "1234567890")
                .fillFieldByNameStepOne("password", "123456789")
                .fillFieldByNameStepOne("confirm", "123456789")
                .chooseRadioButtonByName("business")
                .scrollToNextButton()
                .clickNextButton()
                .fillFieldByNameStepTwo("business", "firstname", "test")
                .fillFieldByNameStepTwo("business", "lastname", "test")
                .clickNextButton()
                .scrollAndClickCheckboxPrivacyPolicy()
                .clickRegisterButton()
                .getTextOnAccountCreationSuccessPage();

        Assert.assertEquals(confirmRegistration, "YOUR ACCOUNT HAS BEEN CREATED!");
    }
    @Test
    public void testRegistrationPageUniversitiesAccount() throws InterruptedException {

        String customerEmail = "autotest-new@orthazone.com";

        String confirmRegistration = new HomePage(getDriver())
                .getHeader()
                .clickAccountButton()
                .clickRegisterButton()
                .fillFieldByNameStepOne("email", customerEmail)
                .fillFieldByNameStepOne("telephone", "1234567890")
                .fillFieldByNameStepOne("password", "123456789")
                .fillFieldByNameStepOne("confirm", "123456789")
                .chooseRadioButtonByName("universities")
                .scrollToNextButton()
                .clickNextButton()
                .fillFieldByNameStepTwo("universities", "firstname", "test")
                .fillFieldByNameStepTwo("universities", "lastname", "test")
                .clickNextButton()
                .scrollAndClickCheckboxPrivacyPolicy()
                .clickRegisterButton()
                .getTextOnAccountCreationSuccessPage();

        Assert.assertEquals(confirmRegistration, "YOUR ACCOUNT HAS BEEN CREATED!");
    }
}