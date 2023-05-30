package orthazone.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import orthazone.model.base.BaseModel;

public class AccountLogoutPage extends BaseModel {

    public AccountLogoutPage(WebDriver driver) {
        super(driver);
    }

    public String getTextOnLogoutPage() {
        return getDriver().findElement(By.xpath("//div[@class='asteps__head']")).getText();
    }
}
