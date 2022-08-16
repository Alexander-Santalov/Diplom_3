package ru.yandex.stellarburger;

import com.codeborne.selenide.WebDriverRunner;

import org.junit.BeforeClass;
import ru.yandex.stellarburger.model.User;
import ru.yandex.stellarburger.pageobjects.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class TestBase {
    protected User user;
    protected String accessToken;
    static HomePage homePage = page(HomePage.class);
    static LoginPage loginPage = page(LoginPage.class);
    static RegistrationPage registrationPage = page(RegistrationPage.class);
    static ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);
    static ProfilePage profilePage = page(ProfilePage.class);

    @BeforeClass
    public static void precondition() {
        // Для запуска тестов в  Яндекс Браузере необходимо расскоментировать 24 строку, по умолчанию запуск в Chrome
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        open("https://stellarburgers.nomoreparties.site/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}