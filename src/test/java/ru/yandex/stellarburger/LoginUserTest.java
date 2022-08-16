package ru.yandex.stellarburger;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;

import ru.yandex.stellarburger.api.UserHelper;
import ru.yandex.stellarburger.model.User;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Epic("Проверка авторизации пользователя через разные кнопки")
public class LoginUserTest extends TestBase {

    @Before
    public void precondition_test() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        if (!currentUrl.equals("https://stellarburgers.nomoreparties.site/")) {
            open("https://stellarburgers.nomoreparties.site/");
        }
        user = new User(RandomStringUtils.randomAlphabetic(10) + "@ya.ru",
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        accessToken = UserHelper.createUser(user);
    }

    @After
    public void tearDown() {
        homePage.clickPrivateOfficeBtn();
        profilePage.clickExitBtn();
        UserHelper.delete(accessToken.split(" ")[1]);
    }

    @Test
    @Story("Логин пользователя через кнопку 'Войти в аккаунт'")
    public void testUserLoginWithLoginBtn() {
        homePage.clickLoginBtn();
        loginPage.fillFormLogin(user.getEmail(), user.getPassword());
        loginPage.clickEnterBtn();
        assertThat("Неверный текст кнопки", homePage.getNameCreateOrderBtn(),
                equalTo("Оформить заказ"));
    }

    @Test
    @Story("Логин пользователя через 'Личный кабинет'")
    public void testUserLoginWithPrivateOffice() {
        homePage.clickPrivateOfficeBtn();
        loginPage.fillFormLogin(user.getEmail(), user.getPassword());
        loginPage.clickEnterBtn();
        assertThat("Неверный текст кнопки", homePage.getNameCreateOrderBtn(),
                equalTo("Оформить заказ"));
    }

    @Test
    @Story("Логин пользователя через кнопку 'Войти' в форме регистраиции")
    public void testUserLoginWithFormRegistration() {
        homePage.clickLoginBtn();
        loginPage.clickRegistrationUrl();
        registrationPage.clickEnterUrl();
        loginPage.fillFormLogin(user.getEmail(), user.getPassword());
        loginPage.clickEnterBtn();
        assertThat("Неверный текст кнопки", homePage.getNameCreateOrderBtn(),
                equalTo("Оформить заказ"));
    }

    @Test
    @Story("Логин пользователя через кнопку 'Войти' в форме восстановления пароля")
    public void testUserLoginWithForgotPassword() {
        homePage.clickLoginBtn();
        loginPage.clickForgotPasswordUrl();
        forgotPasswordPage.clickEnterUrl();
        loginPage.fillFormLogin(user.getEmail(), user.getPassword());
        loginPage.clickEnterBtn();
        assertThat("Неверный текст кнопки", homePage.getNameCreateOrderBtn(),
                equalTo("Оформить заказ"));
    }
}
