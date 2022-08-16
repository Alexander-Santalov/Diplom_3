package ru.yandex.stellarburger.pageobjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {
    @FindBy(how = How.XPATH, using = ".//label[text()='Имя']/../input")
    private SelenideElement nameField;

    @FindBy(how = How.XPATH, using = ".//label[text()='Email']/../input")
    private SelenideElement emailField;

    @FindBy(how = How.CSS, using = "input[type='password']")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registrationBtn;

    @FindBy(how = How.CSS, using = "p.input__error")
    private SelenideElement errorMessage;

    @FindBy(how = How.CSS, using = "a[href='/login']")
    private SelenideElement enterUrl;


    public void setNameField(String name) {
        nameField.setValue(name);
    }

    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Клик по кнопке 'Зарегестрироваться' в форме регистрации")
    public void clickRegistrationBtn() {
        registrationBtn.click();
    }

    @Step("Заполнение формы регистрации")
    public void fillFormRegistration(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    @Step("Получение текста ошибки в форме регистрации")
    public String getErrorMessage() {
        clickRegistrationBtn();
        return errorMessage.getText();
    }

    @Step("Клик по кнопке 'Войти' в форме регистрации")
    public void clickEnterUrl() {
        enterUrl.click();
    }
}
