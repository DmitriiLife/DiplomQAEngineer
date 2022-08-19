package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.PopupWarningScreen;

public class PopupWarningStep {
    static PopupWarningScreen PopupWarningScreen = new PopupWarningScreen();

    public static void checkEmptyToast(String text, boolean visible) {
        Allure.step("Проверка текста уведомления");
        if (visible) {
            PopupWarningScreen.emptyToast(text).check(matches(isDisplayed()));
        } else {
            PopupWarningScreen.emptyToast(text).check(matches(not(isDisplayed())));
        }
    }
}