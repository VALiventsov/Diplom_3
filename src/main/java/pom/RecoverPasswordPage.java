package pom;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RecoverPasswordPage {
    @FindBy(how = How.XPATH,
            using = (".//a[text()='Войти']"))
    private SelenideElement loginButton;

    @Step("Клик по кнопке войти")
    public void clickLoginButton() {
        loginButton.click();
    }
}

