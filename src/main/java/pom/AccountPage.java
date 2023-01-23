package pom;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPage {

    // локатор кнопки Конструктор
    @FindBy(how = How.XPATH,
            using = ("//p[text()='Конструктор']"))
    private SelenideElement createBurgerButton;

    // локатор логотипа Stellar Burgers
    @FindBy(how = How.XPATH,
            using = ("//div[@class='AppHeader_header__logo__2D0X2']/a"))
    private SelenideElement stellarBurgerLogoButton;

    // локатор кнопки Выход
    @FindBy(how = How.XPATH,
            using = ("//button[text()='Выход']"))
    private SelenideElement logOutButton;

    @Step("Клик кнопки Конструктор")
    public void clickCreateBurgerButton() {
        createBurgerButton.click();
    }

    @Step("Клик логотипа Stellar Burgers")
    public void clickStellarBurgerLogoButton() {
        stellarBurgerLogoButton.click();
    }

    @Step("Клик по кнопке Выйти")
    public void clickLogOutButton() {
        logOutButton.click();
    }
}
