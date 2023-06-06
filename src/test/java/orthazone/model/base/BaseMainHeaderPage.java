package orthazone.model.base;

import org.openqa.selenium.WebDriver;
import orthazone.model.component.MainHeaderComponent;

public abstract class BaseMainHeaderPage extends BasePage<MainHeaderComponent> {

    public BaseMainHeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainHeaderComponent getHeader() {
        return new MainHeaderComponent(getDriver());
    }
}
