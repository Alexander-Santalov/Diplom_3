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

@Epic("Регистрация пользователя")
public class RegistrationUserTest extends TestBase {
    String message = "";


    @Before
    public void precondition_test() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        if (!currentUrl.equals("https://stellarburgers.nomoreparties.site/")) {
            open("https://stellarburgers.nomoreparties.site/");
        }
        user = new User(RandomStringUtils.randomAlphabetic(10) + "@ya.ru",
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    }

    @After
    public void tearDown() {
        if (message.isEmpty()) {
            accessToken = UserHelper.login(user);
            UserHelper.delete(accessToken.split(" ")[1]);
            message = "";
        }
    }

    @Test
    @Story("Регистрация с невалидным паролем")
    public void testRegistrationUserWithInvalidPass() throws InterruptedException {
        homePage.clickLoginBtn();
        loginPage.clickRegistrationUrl();
        user.setPassword("123");
        registrationPage.fillFormRegistration(user.getName(), user.getEmail(), user.getPassword());
        message = registrationPage.getErrorMessage();
        assertThat("Неверное сообщение об ошибке", message, equalTo("Некорректный пароль"));
    }

    @Test
    @Story("Регистрация пользователя с валидными данными")
    public void testRegistrationUser() throws InterruptedException {
        homePage.clickLoginBtn();
        loginPage.clickRegistrationUrl();
        registrationPage.fillFormRegistration(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegistrationBtn();
        assertThat("Неверное имя кнопки формы логина", loginPage.getNameEnterBtn(), equalTo("Войти"));
    }
}
