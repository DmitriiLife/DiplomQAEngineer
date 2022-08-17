package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.espresso.elements.AddNewCommentScreen;

public class AddNewCommentStep {
    AddNewCommentScreen AddNewCommentScreen = new AddNewCommentScreen();

    @Step("Ввод комментария к претензии")
    public void enterComment(String text) {
        AddNewCommentScreen.comment.check(matches(isDisplayed()));
        AddNewCommentScreen.comment.perform(replaceText(text));
    }

    @Step("Кликнуть сохранить")
    public void clickSave() {
        AddNewCommentScreen.buttonSave.check(matches(isDisplayed()));
        AddNewCommentScreen.buttonSave.perform(click());
        SystemClock.sleep(1500);
    }

    @Step("Кликнуть отменить")
    public void clickCancel() {
        AddNewCommentScreen.buttonCancel.check(matches(isDisplayed()));
        AddNewCommentScreen.buttonCancel.perform(click());
    }
}
