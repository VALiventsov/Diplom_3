package pom;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {

    // локатор поля ввода имени
    @FindBy(how = How.XPATH,
            using = ("//label[text()='Имя']//following-sibling::input"))
    private SelenideElement nameFieldButton;

    // локатор поля ввода email
    @FindBy(how = How.XPATH,
            using = ("//label[text()='Email']//following-sibling::input"))
    private SelenideElement emailFieldButton;

    // локатор поля ввода пароля
    @FindBy(how = How.XPATH,
            using = ("//input[@type='password']"))
    private SelenideElement passwordFieldButton;

    // локатор кнопки зарегистрироваться
    @FindBy(how = How.XPATH,
            using = (".//button[contains(text(),'Зарегистрироваться')]"))
    private SelenideElement registerButton;

    // локатор кнопки Войти
    @FindBy(how = How.XPATH,
            using = ("//a[text()='Войти']"))
    private SelenideElement singInButton;

    //локатор некорректного пароля
    @FindBy(how = How.CSS,
            using = (".input__error.text_type_main-default"))
    private SelenideElement textError;

    @Step("Вводим имя")
    public void setInputName(String name) {
        nameFieldButton.setValue(name);
    }

    @Step("Вводим почту")
    public void setInputEmail(String email) {
        emailFieldButton.setValue(email);
    }

    @Step("Вводим пароль")
    public void setInputPassword(String password) {
        passwordFieldButton.setValue(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Регистрация")
    public void registration(String name, String email, String password) {
        setInputName(name);
        setInputEmail(email);
        setInputPassword(password);
        clickRegisterButton();
    }

    @Step("Получить ошибку некорректного пароля")
    public SelenideElement getTextError() {
        return textError;
    }

    @Step("Клик по кнопке Войти")
    public void clickSingInButton() {
        singInButton.click();
    }
}
