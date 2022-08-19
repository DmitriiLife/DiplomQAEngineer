package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.ThematicQuotesScreen;

public class ThematicQuotesSteps {
    ThematicQuotesScreen ThematicQuotesScreen = new ThematicQuotesScreen();

    public void checkAll() {
        Allure.step("Проверить всё");
        ThematicQuotesScreen.title.check(matches(allOf(withText("Love is all"), isDisplayed())));
        ThematicQuotesScreen.icon.check(matches(isDisplayed()));
        ThematicQuotesScreen.thematicTitle.check(matches(isDisplayed()));
    }

    public void openCloseQuote(int number) {
        Allure.step("Развернуть/Свернуть цитату");
        ThematicQuotesScreen.missionList.check(matches(isDisplayed()));
        ThematicQuotesScreen.missionList.perform(actionOnItemAtPosition(number, click()));
    }

    public void checkTextQuote(String text) {
        Allure.step("Проверить наличие текста цитаты");
        onView(Matchers.allOf(withText(text), isDisplayed()));
    }

    public void checkNotTextQuote(String text) {
        Allure.step("Проверить, что текст цитаты скрыт");
        onView(Matchers.allOf(withText(text), not(isDisplayed())));
    }
}
