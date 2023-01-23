package pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    //локаторр кнопки восстановить пароль
    @FindBy(how = How.XPATH,
            using = (".//a[text()='Восстановить пароль']"))
    private SelenideElement recoverPasswordButton;

    //локатор кнопки зарегистрироваться
    @FindBy(how = How.XPATH,
            using = (".//a[text()='Зарегистрироваться']"))
    private SelenideElement registerButton;

    //локатор кнопки Войти
    @FindBy(how = How.XPATH,
            using = ".//button[contains(text(),'Войти')]")
    private SelenideElement singInButton;

    //локатор поля email
    @FindBy(how = How.XPATH,
            using = ".//input[@name='name']")
    private SelenideElement emailField;

    //локатор поля пароль
    @FindBy(how = How.XPATH,
            using = ".//input[@name='Пароль']")
    private SelenideElement passwordField;

    @Step("Заполнение формы входа")
    public void fillLoginForm(String email, String password) {
        emailField.setValue(email);
        passwordField.setValue(password);
    }

    @Step("Клик по кнопке зарегистрироваться")
    public void registerButtonClick() {
        registerButton.click();
    }

    @Step("Клик по кнопке Войти")
    public void singInButtonClick() {
        singInButton.click();
    }

    @Step("Клик по кнопке восстановить пароль")
    public void clickRecoverPasswordButton() {
        recoverPasswordButton.click();
    }

    public void setSingInButtonShouldBeVisible() {
        singInButton.shouldBe(Condition.visible);
    }
}
