package orthazone.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import orthazone.model.base.BaseModel;

public class AccountCreationSuccessPage extends BaseModel {

    public AccountCreationSuccessPage(WebDriver driver) {
        super(driver);
    }

    public String getTextOnAccountCreationSuccessPage() {
        return getDriver().findElement(By.xpath("//div[@class='asteps__head']")).getText();
    }
}