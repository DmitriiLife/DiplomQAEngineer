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

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.espresso.elements.ThematicQuotesScreen;

public class ThematicQuotesSteps {
    ThematicQuotesScreen ThematicQuotesScreen = new ThematicQuotesScreen();

    @Step("Проверить всё")
    public void checkAll() {
        ThematicQuotesScreen.title.check(matches(allOf(withText("Love is all"), isDisplayed())));
        ThematicQuotesScreen.icon.check(matches(isDisplayed()));
        ThematicQuotesScreen.thematicTitle.check(matches(isDisplayed()));
    }

    @Step("Развернуть/Свернуть цитату")
    public void openCloseQuote(int number) {
        ThematicQuotesScreen.missionList.check(matches(isDisplayed()));
        ThematicQuotesScreen.missionList.perform(actionOnItemAtPosition(number, click()));
    }

    @Step("Проверить наличие текста цитаты")
    public void checkTextQuote(String text) {
        onView(Matchers.allOf(withText(text), isDisplayed()));
    }

    @Step("Проверить, что текст цитаты скрыт")
    public void checkNotTextQuote(String text) {
        onView(Matchers.allOf(withText(text), not(isDisplayed())));
    }
}
