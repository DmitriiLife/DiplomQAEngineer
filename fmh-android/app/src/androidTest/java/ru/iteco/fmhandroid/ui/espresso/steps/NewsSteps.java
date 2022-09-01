package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.NewsScreen;
import ru.iteco.fmhandroid.ui.espresso.utils.Utils;

public class NewsSteps {
    NewsScreen NewsScreen = new NewsScreen();
    ControlPanelSteps ControlPanelSteps = new ControlPanelSteps();

    public void checkSorting() {
        Allure.step("Проверка сортировки новостей");
        String firstNews = getFirstNewsTitle();
        String firstPublicationDate = ControlPanelSteps.getFirstNewsPublicationDate();
        String firstCreationDate = ControlPanelSteps.getFirstNewsCreationDate();
        clickSortButton();
        String lastPublicationDate = ControlPanelSteps.getLastNewsPublicationDate();
        clickSortButton();
        String firstNewsAgain = getFirstNewsAgainTitle();
        String firstPublicationDateAgain = ControlPanelSteps.getFirstNewsPublicationDateAgain();
        String firstCreationDateAgain = ControlPanelSteps.getFirstNewsCreationDateAgain();
        assertEquals(firstNews, firstNewsAgain);
        assertEquals(firstPublicationDate, firstPublicationDateAgain);
        assertEquals(firstCreationDate, firstCreationDateAgain);
        assertNotEquals(firstPublicationDate, lastPublicationDate);
    }

    public void isNewsScreen() {
        Allure.step("Проверить, что это экран новостей");
        NewsScreen.news.check(matches(isDisplayed()));
        NewsScreen.buttonSort.check(matches(isDisplayed()));
        NewsScreen.buttonFilter.check(matches(isDisplayed()));
        NewsScreen.buttonControlPanel.check(matches(isDisplayed()));
    }

    public String getFirstNewsTitle() {
        Allure.step("Получить название первой новости");
        return Utils.TextHelpers.getText(NewsScreen.firstNews);
    }

    public String getLastNewsTitle() {
        Allure.step("Получить название последней новости");
        return Utils.TextHelpers.getText(NewsScreen.lastNews);
    }

    public String getFirstNewsAgainTitle() {
        Allure.step("Получить название новой первой новости");
        return Utils.TextHelpers.getText(NewsScreen.firstNewsAgain);
    }

    public void clickSortButton() {
        Allure.step("Сортировать");
        NewsScreen.buttonSort.perform(click());
    }

    public void goToControlPanel() {
        Allure.step("Перейти в панель управления");
        NewsScreen.buttonControlPanel.perform(click());
        NewsScreen.controlPanel.check(matches(isDisplayed()));
    }

    public void openFilter() {
        Allure.step("Открыть фильтр");
        NewsScreen.buttonFilter.perform(click());
    }

    public void checkFirstNewsDate(String text) {
        Allure.step("Проверить дату первой новости");
        NewsScreen.firstNewsDate.check(matches(withText(text)));
    }

    public void checkNewsScreenSorting() {
        Allure.step("Проверка сортировки новостей на экране новости");
        String firstNews = getFirstNewsTitle();
        clickSortButton();
        String lastNews = getLastNewsTitle();
        clickSortButton();
        String firstNewsAgain = getFirstNewsAgainTitle();
        assertEquals(firstNews, firstNewsAgain);
       // assertNotEquals(firstNews, lastNews);
    }
}
