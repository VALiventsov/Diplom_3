package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pom.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {

    @Test
    @DisplayName("проверка перехода к разделу соусы")
    public void checkGoToSauces() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickSauceTab();
        mainPage.spicySauceTabShouldBeVisible();
    }

    @Test
    @DisplayName("проверка перехода к разделу начинки")
    public void checkGoToFillings() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickFillingsTab();
        mainPage.protostomiaFillingsTabShouldBeVisible();
    }

    @Test
    @DisplayName("проверка перехода к разделу булки")
    public void checkGoToBuns() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        mainPage.fluorescentBunTabShouldBeVisible();
    }
}
