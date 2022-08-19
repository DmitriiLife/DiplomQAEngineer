package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.NewsFilterScreen;

public class NewsFilterSteps {
    NewsFilterScreen NewsFilterScreen = new NewsFilterScreen();

    public void enterPublishDateStart(String text) {
        Allure.step("Ввести начальную дату публикации");
        NewsFilterScreen.publishDateStart.perform(replaceText(text));
    }

    public void enterPublishDateEnd(String text) {
        Allure.step("Ввести конечную дату публикации");
        NewsFilterScreen.publishDateEnd.perform(replaceText(text));
    }

    public void clickFilter() {
        Allure.step("Отфильтровать");
        NewsFilterScreen.buttonFilter.perform(click());
    }

    public void clickCheckboxActive() {
        Allure.step("Кликнуть чекбокс активные");
        NewsFilterScreen.checkboxActive.perform(click());
    }

    public void clickCheckboxNotActive() {
        Allure.step("Кликнуть чекбокс не активные");
        NewsFilterScreen.checkboxNotActive.perform(click());
    }

    public void checkCheckboxActive(boolean checked) {
        Allure.step("Проверить чекбокс активные");
        if (checked) {
            NewsFilterScreen.checkboxActive.check(matches(isChecked()));
        } else {
            NewsFilterScreen.checkboxActive.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxNotActive(boolean checked) {
        Allure.step("Проверить чекбокс неактивные");
        if (checked) {
            NewsFilterScreen.checkboxNotActive.check(matches(isChecked()));
        } else {
            NewsFilterScreen.checkboxNotActive.check(matches(isNotChecked()));
        }
    }
}
