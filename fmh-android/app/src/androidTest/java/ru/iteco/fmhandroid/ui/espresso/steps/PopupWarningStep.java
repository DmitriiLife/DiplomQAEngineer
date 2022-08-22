package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.PopupWarningScreen;

public class PopupWarningStep {
    PopupWarningScreen PopupWarningScreen = new PopupWarningScreen();

    public void checkEmptyToast(int id, boolean visible) {
        Allure.step("Проверка текста тоста");
        if (visible) {
            PopupWarningScreen.emptyToast(id).check(matches(isDisplayed()));
        } else {
            PopupWarningScreen.emptyToast(id).check(matches(not(isDisplayed())));
        }
    }

    public void checkEmptyMessage(int id, boolean visible) {
        Allure.step("Проверка текста сообщения");
        if (visible) {
            PopupWarningScreen.emptyMessage(id).check(matches(isDisplayed()));
        } else {
            PopupWarningScreen.emptyMessage(id).check(matches(not(isDisplayed())));
        }
    }
}