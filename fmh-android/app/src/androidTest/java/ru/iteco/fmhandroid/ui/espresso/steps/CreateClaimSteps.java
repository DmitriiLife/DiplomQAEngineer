package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.CreateClaimScreen;

public class CreateClaimSteps {
    CreateClaimScreen CreateClaimScreen = new CreateClaimScreen();

    public void isCreateClaimsScreen() {
        Allure.step("Проверить, что это экран создания претензии");
        CreateClaimScreen.title.check(matches(withText("Creating")));
        CreateClaimScreen.subTitle.check(matches(withText("Claims")));
    }

    public void checkClaimTitleLength() {
        Allure.step("Проверить длину заголовка");
        CreateClaimScreen.claimTitle.perform(replaceText("Здравствуйте, я тут и полезу в это поле ввода"));
        CreateClaimScreen.claimTitle.check(matches(withText("Здравствуйте, я тут и полезу в это поле ввода")));
    }

    public void enterClaimTitle(String text) {
        Allure.step("Ввести заголовок");
        CreateClaimScreen.claimTitle.check(matches(isDisplayed()));
        CreateClaimScreen.claimTitle.perform(replaceText(text));
    }

    public void selectExecutor() {
        Allure.step("Выбрать исполнителя");
        CreateClaimScreen.executorList.perform(replaceText("Иванов Даниил Данилович"));
        SystemClock.sleep(2000);
        CreateClaimScreen.claimTitle.perform(click(), closeSoftKeyboard());
    }

    public void enterClaimDate(String text) {
        Allure.step("Ввести дату");
        CreateClaimScreen.claimDate.check(matches(isDisplayed()));
        CreateClaimScreen.claimDate.perform(replaceText(text));
    }

    public void enterClaimTime(String text) {
        Allure.step("Ввести время");
        CreateClaimScreen.claimTime.check(matches(isDisplayed()));
        CreateClaimScreen.claimTime.perform(replaceText(text));
    }

    public void enterClaimDescription(String text) {
        Allure.step("Ввести описание");
        CreateClaimScreen.claimDescription.check(matches(isDisplayed()));
        CreateClaimScreen.claimDescription.perform(replaceText(text), closeSoftKeyboard());
    }
}
