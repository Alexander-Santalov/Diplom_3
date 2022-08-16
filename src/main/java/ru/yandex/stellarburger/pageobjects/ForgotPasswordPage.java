package ru.yandex.stellarburger.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {

    @FindBy(how = How.CSS, using = "a[href='/login']")
    private SelenideElement enterUrl;

    @Step("Клик по кнопке 'Войти' в форме 'Восстановления пароля'")
    public void clickEnterUrl() {
        enterUrl.click();
    }
}
