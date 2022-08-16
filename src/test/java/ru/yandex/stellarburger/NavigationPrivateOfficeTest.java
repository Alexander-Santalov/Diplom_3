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

@Epic("Проверки навигации страницы 'Личный кабинет'")
public class NavigationPrivateOfficeTest extends TestBase {
    @Before
    public void precondition_test() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        if (!currentUrl.equals("https://stellarburgers.nomoreparties.site/")) {
            open("https://stellarburgers.nomoreparties.site/");
        }
        user = new User(RandomStringUtils.randomAlphabetic(10) + "@ya.ru",
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        accessToken = UserHelper.createUser(user);
        homePage.clickLoginBtn();
        loginPage.fillFormLogin(user.getEmail(), user.getPassword());
        loginPage.clickEnterBtn();
    }

    @After
    public void tearDown() {
        homePage.clickPrivateOfficeBtn();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        if (currentUrl.equals("https://stellarburgers.nomoreparties.site/profile")) {
            profilePage.clickExitBtn();
        }
        UserHelper.delete(accessToken.split(" ")[1]);
    }

    @Test
    @Story("Переход пользователя в 'Личный кабинет'")
    public void testGoToPrivateOffice() {
        homePage.clickPrivateOfficeBtn();
        assertThat("Неверное имя пользователя", profilePage.getUserName(), equalTo(user.getName()));
    }

    @Test
    @Story("Логаут пользователя")
    public void testExitFromPrivateOffice() {
        homePage.clickPrivateOfficeBtn();
        profilePage.clickExitBtn();
        assertThat("Неверное имя кнопки формы логина", loginPage.getNameEnterBtn(), equalTo("Войти"));
    }

    @Test
    @Story("Переход пользователя из личного кабинета на главную страницу по кнопке 'Конструктор'")
    public void testGoToConstructorFromPrivateOffice() {
        homePage.clickPrivateOfficeBtn();
        homePage.clickConstructorBtn();
        assertThat("Неверный текст кнопки", homePage.getNameCreateOrderBtn(),
                equalTo("Оформить заказ"));
    }

    @Test
    @Story("Переход пользователя из личного кабинета на главную страницу по логотипу")
    public void testGoToLogoFromPrivateOffice() {
        homePage.clickPrivateOfficeBtn();
        homePage.clickLogoElement();
        assertThat("Неверный текст кнопки", homePage.getNameCreateOrderBtn(),
                equalTo("Оформить заказ"));
    }

}
