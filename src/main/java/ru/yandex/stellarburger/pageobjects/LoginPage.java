package ru.yandex.stellarburger.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    @FindBy(how = How.CSS, using = "a[href='/register']")
    private SelenideElement registrationUrl;

    @FindBy(how = How.XPATH, using = ".//label[text()='Email']/../input")
    private SelenideElement emailField;

    @FindBy(how = How.CSS, using = "input[type='password']")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement enterBtn;

    @FindBy(how = How.CSS, using = "a[href='/register']")
    private SelenideElement forgotPasswordUrl;

    @Step("Клик по кнопке 'Восстановить пароль' в форме логина")
    public void clickForgotPasswordUrl() {
        forgotPasswordUrl.click();
    }

    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Клик по кнопке 'Зарегестрироваться' в форме логина")
    public void clickRegistrationUrl() {
        registrationUrl.click();
    }

    @Step("Заполнение формы логина")
    public void fillFormLogin(String email, String password) {
        registrationUrl.shouldBe(Condition.visible);
        setEmailField(email);
        setPasswordField(password);
    }

    @Step("Клик по кнопке 'Войти' в форме логина")
    public void clickEnterBtn() {
        enterBtn.click();
    }

    @Step("Получение имени кнопки")
    public String getNameEnterBtn() {
        return enterBtn.getText();
    }

}
