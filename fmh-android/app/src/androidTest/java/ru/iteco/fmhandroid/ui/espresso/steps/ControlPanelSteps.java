package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.isDisplayedWithSwipe;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.ControlPanelScreen;
import ru.iteco.fmhandroid.ui.espresso.elements.NewsScreen;
import ru.iteco.fmhandroid.ui.espresso.utils.Utils.TextHelpers;

public class ControlPanelSteps {

    ControlPanelScreen ControlPanelScreen = new ControlPanelScreen();
    NewsScreen NewsScreen = new NewsScreen();
    CommonSteps CommonSteps = new CommonSteps();

    public String getFirstNewsPublicationDate() {
        Allure.step("Получить дату публикации первой новости");
        return TextHelpers.getText(ControlPanelScreen.firstPublicationDate);
    }

    public String getFirstNewsCreationDate() {
        Allure.step("Получить дату создания первой новости");
        return TextHelpers.getText(ControlPanelScreen.firstCreationDate);
    }

    public String getFirstNewsPublicationDateAgain() {
        Allure.step("Получить дату публикации новой первой новости");
        return TextHelpers.getText(ControlPanelScreen.firstPublicationDateAgain);
    }

    public String getFirstNewsCreationDateAgain() {
        Allure.step("Получить дату создания новой первой новости");
        return TextHelpers.getText(ControlPanelScreen.firstCreationDateAgain);
    }

    public String getLastNewsPublicationDate() {
        Allure.step("Получить дату публикации последней новости");
        return TextHelpers.getText(ControlPanelScreen.lastPublicationDate);
    }

    public void createNews() {
        Allure.step("Перейти к экрану создания новости");
        ControlPanelScreen.buttonCreateNews.perform(click());
    }

    public void isControlPanel() {
        Allure.step("Проверка, что это экран управления");
        NewsScreen.controlPanel.check(matches(isDisplayed()));
    }

    public void checkFirstPublicationDate(String text) {
        Allure.step("Проверить дату публикации первой новости");
        ControlPanelScreen.firstPublicationDate.check(matches(withText(text)));
    }

    public void checkFirstPublicationDateNotActive(String text) {
        Allure.step("Проверить дату публикации первой не активной новости");
        ControlPanelScreen.firstPublicationDateNotActive.check(matches(withText(text)));
    }

    public void checkFirstPublicationDateActive(String text) {
        Allure.step("Проверить дату публикации первой активной новости");
        ControlPanelScreen.firstPublicationDateActive.check(matches(withText(text)));
    }

    public void checkNewsStatus() {
        Allure.step("Проверить статус новости");
        ControlPanelScreen.newsStatus.check(matches(withText("Not active")));
    }

    public void checkNewsStatusActive() {
        Allure.step("Проверить статус активной новости");
        ControlPanelScreen.newsStatusActive.check(matches(withText("Active")));
    }

    public void clickEditNews() {
        Allure.step("Открыть окно изменения новости");
        ControlPanelScreen.buttonEditNews.perform(click());
    }

    public void clickEditThisNews(String text) {
        Allure.step("Открыть окно изменения этой новости");
        ControlPanelScreen.newsEdit(text).perform(click());
    }

    public void checkNewsStatusNotActive() {
        Allure.step("Проверить статус не активной новости");
        ControlPanelScreen.buttonEditNewsNotActive.perform(click());
    }

    public void clickDeleteNews() {
        Allure.step("Удалить новость");
        ControlPanelScreen.buttonDeleteNews.perform(click());
    }

    public void clickDeleteThisNews(String text) {
        Allure.step("Удалить эту новость");
        ControlPanelScreen.newsDelete(text).perform(click());
    }

    public void clickNewsTitle(String text) {
        Allure.step("Нажать на заголовок");
        ViewInteraction newsTitle = onView(withText(text));
        newsTitle.perform(click());
    }

    public void checkNewsDescription(boolean visible, String text) {
        Allure.step("Проверить описание новости");
        if (visible) {
            ControlPanelScreen.newsDescription(text).check(matches(isDisplayed()));
        } else {
            ControlPanelScreen.newsDescription(text).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        }
    }

    public void deleteNews(String text) {
        Allure.step("Поиск и удаление новости");
        isControlPanel();
        if (isDisplayedWithSwipe(onView(withText(text)), 3, true)) {
            onView(withText(text)).check(matches(isDisplayed()));
        }
        ControlPanelScreen.newsDelete(text).perform(click());
        CommonSteps.clickOK();
    }

    public void openDescriptionNewsDelete(String text) {
        Allure.step("Поиск/открытие описания и удаление новости");
        isControlPanel();
        if (isDisplayedWithSwipe(onView(withText(text)), 3, true)) {
            onView(withText(text)).check(matches(isDisplayed()));
        }
        ControlPanelScreen.newsTitle(text).perform(doubleClick());
        ControlPanelScreen.newsDelete(text).perform(click());
        CommonSteps.clickOK();
    }

    public void searchOpenDescriptionNews(String text) {
        Allure.step("Поиск/открытие описания новости");
        isControlPanel();
        if (isDisplayedWithSwipe(onView(withText(text)), 3, true)) {
            onView(withText(text)).check(matches(isDisplayed()));
        }
        ControlPanelScreen.newsTitle(text).perform(doubleClick());
    }
}
