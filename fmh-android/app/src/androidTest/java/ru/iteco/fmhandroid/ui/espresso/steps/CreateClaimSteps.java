package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.espresso.elements.CreateClaimScreen;

public class CreateClaimSteps {

    CreateClaimScreen CreateClaimScreen = new CreateClaimScreen();

    @Step("Проверить, что это экран создания претензии")
    public void isCreateClaimsScreen() {
        CreateClaimScreen.title.check(matches(withText("Creating")));
        CreateClaimScreen.subTitle.check(matches(withText("Claims")));
    }

    @Step("Проверить длину заголовка")
    public void checkClaimTitleLength() {
        CreateClaimScreen.claimTitle.perform(replaceText("Здравствуйте, я тут и полезу в это поле ввода"));
        CreateClaimScreen.claimTitle.check(matches(withText("Здравствуйте, я тут и полезу в это поле ввода")));
    }

    @Step("Ввести заголовок")
    public void enterClaimTitle(String text) {
        CreateClaimScreen.claimTitle.check(matches(isDisplayed()));
        CreateClaimScreen.claimTitle.perform(replaceText(text));
    }

    @Step("Выбрать исполнителя")
    public void selectExecutor() {
        CreateClaimScreen.executorList.perform(click());
        SystemClock.sleep(2000);
        CreateClaimScreen.claimTitle.perform(click(), closeSoftKeyboard());
    }

    @Step("Ввести дату")
    public void enterClaimDate(String text) {
        CreateClaimScreen.claimDate.check(matches(isDisplayed()));
        CreateClaimScreen.claimDate.perform(replaceText(text));
    }

    @Step("Ввести время")
    public void enterClaimTime(String text) {
        CreateClaimScreen.claimTime.check(matches(isDisplayed()));
        CreateClaimScreen.claimTime.perform(replaceText(text));
    }

    @Step("Ввести описание")
    public void enterClaimDescription(String text) {
        CreateClaimScreen.claimDescription.check(matches(isDisplayed()));
        CreateClaimScreen.claimDescription.perform(replaceText(text), closeSoftKeyboard());
    }
}
