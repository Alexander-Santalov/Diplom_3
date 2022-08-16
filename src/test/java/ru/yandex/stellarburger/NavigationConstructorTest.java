package ru.yandex.stellarburger;

import io.qameta.allure.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Epic("Проверки на переход между вкладками в «Конструкторе»")
public class NavigationConstructorTest extends TestBase {
    @Test
    @Story("Переход к вкладке 'Соусы'")
    public void testGoToSauceTab() {
        homePage.clickSauceTab();
        assertThat("Неверное имя активного раздела", homePage.textActiveTab(), equalTo("Соусы"));
    }

    @Test
    @Story("Переход к вкладке 'Начинки'")
    public void testGoToFillingsTab() {
        homePage.clickFillingsTab();
        assertThat("Неверное имя активного раздела", homePage.textActiveTab(), equalTo("Начинки"));
    }

    @Test
    @Story("Переход к вкладке 'Булки'")
    public void testGoToBunTab() {
        homePage.ensureTab();
        homePage.clickBunTab();
        assertThat("Неверное имя активного раздела", homePage.textActiveTab(), equalTo("Булки"));
    }
}
