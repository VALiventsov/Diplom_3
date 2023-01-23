package pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {

    @FindBy(how = How.XPATH,
            using = ("//button[text()='Войти в аккаунт']"))
    private static SelenideElement loginButton;
    @FindBy(how = How.XPATH,
            using = ("//button[text()='Оформить заказ']"))
    private static SelenideElement setOrderButton;
    @FindBy(how = How.XPATH,
            using = ("//p[text()='Личный Кабинет']"))
    private SelenideElement personalAccountButton;
    @FindBy(how = How.XPATH,
            using = ("//span[text()='Булки']//parent::div"))
    private SelenideElement bunsTab;
    @FindBy(how = How.XPATH,
            using = ("//p[@class = 'BurgerIngredient_ingredient__text__yp3dH']"))
    private SelenideElement fluorescentBunTab;
    @FindBy(how = How.XPATH,
            using = ("//span[text()='Соусы']//parent::div"))
    private SelenideElement sauceTab;
    @FindBy(how = How.XPATH,
            using = ("//p[@class = 'BurgerIngredient_ingredient__text__yp3dH']"))
    private SelenideElement spicySauceTab;
    @FindBy(how = How.XPATH,
            using = ("//span[text()='Начинки']//parent::div"))
    private SelenideElement fillingsTab;
    @FindBy(how = How.XPATH,
            using = ("//p[@class = 'BurgerIngredient_ingredient__text__yp3dH']"))
    private SelenideElement protostomiaFillingsTab;

    @Step("Кллик по кнопке Войти")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    @Step("Кнопка Оформить заказ должна быть видима")
    public void orderButtonShouldBeVisible() {
        setOrderButton.shouldBe(Condition.visible);
    }

    @Step("Клик по кнопке Соусы")
    public void clickSauceTab() {
        sauceTab.click();
    }

    @Step("Соус Spicy-X должен быть видим")
    public void spicySauceTabShouldBeVisible() {
        spicySauceTab.shouldBe(Condition.visible);
    }

    @Step("Клик по кнопке Начинки")
    public void clickFillingsTab() {
        fillingsTab.click();
    }

    @Step("Мясо бессмертных моллюсков должно быть видимо")
    public void protostomiaFillingsTabShouldBeVisible() {
        protostomiaFillingsTab.shouldBe(Condition.visible);
    }

    @Step("клик по кнопке Булки")
    public void clickBunsTab() {
        bunsTab.click();
    }

    @Step("Флюорисцентная булка должна быть видима")
    public void fluorescentBunTabShouldBeVisible() {
        fluorescentBunTab.shouldBe(Condition.visible);
    }
}