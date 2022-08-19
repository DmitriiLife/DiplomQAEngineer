package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.AddNewCommentScreen;

public class AddNewCommentStep {
    AddNewCommentScreen AddNewCommentScreen = new AddNewCommentScreen();

    public void enterComment(String text) {
        Allure.step("Ввод комментария к претензии");
        AddNewCommentScreen.comment.check(matches(isDisplayed()));
        AddNewCommentScreen.comment.perform(replaceText(text));
    }

    public void clickSave() {
        Allure.step("Кликнуть сохранить");
        AddNewCommentScreen.buttonSave.check(matches(isDisplayed()));
        AddNewCommentScreen.buttonSave.perform(click());
        SystemClock.sleep(1500);
    }

    public void clickCancel() {
        Allure.step("Кликнуть отменить");
        AddNewCommentScreen.buttonCancel.check(matches(isDisplayed()));
        AddNewCommentScreen.buttonCancel.perform(click());
    }
}
