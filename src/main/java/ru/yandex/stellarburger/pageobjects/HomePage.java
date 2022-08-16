package ru.yandex.stellarburger.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

public class HomePage {

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginBtn;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement createOrderBtn;

    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructorBtn;

    @FindBy(how = How.CSS, using = "a[href='/account']")
    private SelenideElement privateOfficeBtn;

    @FindBy(how = How.CSS, using = "div.AppHeader_header__logo__2D0X2")
    private SelenideElement logoElement;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sauceTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsTab;

    @FindBy(how = How.CSS, using = "div.tab_tab_type_current__2BEPc")
    private SelenideElement chooseTab;

    @FindBy(how = How.XPATH, using = ".//section[1]/div[2]/ul[1]/a[1]")
    private SelenideElement bunTitle;
//
//    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
//    private SelenideElement sauceTitle;
//
//    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
//    private SelenideElement fillingsTitle;

    @Step("Клик по вкладке 'Войти в аккаунт'")
    public void clickLoginBtn() {
        loginBtn.click();
    }

    @Step("Получение имени кнопки")
    public String getNameCreateOrderBtn() {
        return createOrderBtn.getText();
    }

    @Step("Клик по вкладке 'Личный кабинет'")
    public void clickPrivateOfficeBtn() {
        privateOfficeBtn.click();
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorBtn() {
        constructorBtn.click();
    }

    @Step("Клик по логотипу Бургер")
    public void clickLogoElement() {
        logoElement.click();
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickBunTab() {
        bunTab.shouldBe(text("Булки")).click();
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickSauceTab() {
        sauceTab.shouldBe(text("Соусы")).click();
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingsTab() {
        fillingsTab.shouldBe(text("Начинки")).click();
    }

    @Step("Получение названия выбранного раздела")
    public String textActiveTab() {
        return chooseTab.getText();
    }

    @Step("Проверка умная вкладка")
    public void ensureTab(){
        if (textActiveTab().equals("Булки")){
            clickFillingsTab();
            }
    }
}
