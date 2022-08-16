package ru.yandex.stellarburger.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement exitBtn;

    @FindBy(how = How.XPATH, using = ".//label[text()='Имя']/../input")
    private SelenideElement nameField;

    @Step("Клик по кнопке 'Выйти'")
    public void clickExitBtn() {
        exitBtn.click();
    }

    @Step("Получение значения поля Имя")
    public String getUserName() {
        return nameField.getValue();
    }
}
