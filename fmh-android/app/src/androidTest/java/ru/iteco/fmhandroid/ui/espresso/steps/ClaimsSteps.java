package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.isDisplayedWithSwipe;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.nestedScrollTo;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.espresso.elements.ClaimScreen;
import ru.iteco.fmhandroid.ui.espresso.elements.MainScreen;
import ru.iteco.fmhandroid.ui.espresso.utils.Utils;

public class ClaimsSteps {

    MainScreen MainScreen = new MainScreen();
    ClaimScreen ClaimScreen = new ClaimScreen();

    @Step("Проверка, что это экране претензий")
    public void isClaimsScreen() {
        ClaimScreen.claims.check(matches(isDisplayed()));
        ClaimScreen.addNewClaimButton.check(matches(isDisplayed()));
        ClaimScreen.buttonFiltering.check(matches(isDisplayed()));
        MainScreen.firstClaimExecutorName.check(matches(isDisplayed()));
    }

    @Step("Открытие фильтра")
    public void openFiltering() {
        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.titleFiltering.check(matches(isDisplayed()));
        ClaimScreen.open.check(matches(isDisplayed()));
        ClaimScreen.inProgress.check(matches(isDisplayed()));
        ClaimScreen.executed.check(matches(isDisplayed()));
        ClaimScreen.cancelled.check(matches(isDisplayed()));
    }

    @Step("Отметить чекбокс в процессе")
    public void clickCheckboxInProgress() {
        ClaimScreen.inProgress.perform(click());
    }

    @Step("Отметить чекбокс открыт")
    public void clickCheckboxOpen() {
        ClaimScreen.open.perform(click());
    }

    @Step("Отметить чекбокс выполнен")
    public void clickCheckboxExecuted() {
        ClaimScreen.executed.perform(click());
    }

    @Step("Отметить чекбокс отменен")
    public void clickCheckboxCancelled() {
        ClaimScreen.cancelled.perform(click());
    }

    @Step("Кликнуть редактировать претензию")
    public void clickEditClaim() {
        ClaimScreen.buttonEditClaim.perform(click());
    }

    @Step("Кликнуть отмена")
    public void clickCancel() {
        ClaimScreen.buttonCancel.perform(click());
    }

    @Step("Кликнуть ОК")
    public void clickOK() {
        ClaimScreen.buttonOk.perform(click());
    }

    @Step("Кликнуть по кнопке с определенным названием")
    public void clickButton(String text) {
        ClaimScreen.buttonText(text).perform(click());
    }

    @Step("Кликнуть редактировать статус претензии")
    public void clickButtonEditStatusClaim() {
        ClaimScreen.buttonStatusClaim.perform(nestedScrollTo());
        ClaimScreen.buttonStatusClaim.check(matches(isDisplayed()));
        ClaimScreen.buttonStatusClaim.perform(click());
    }

    @Step("Проверить состояние чекбокса в процессе")
    public void checkCheckboxInProgress(boolean checked) {
        if (checked) {
            ClaimScreen.inProgress.check(matches(isChecked()));
        } else {
            ClaimScreen.inProgress.check(matches(isNotChecked()));
        }
    }

    @Step("Проверить состояние чекбокса открыт")
    public void checkCheckboxOpen(boolean checked) {
        if (checked) {
            ClaimScreen.open.check(matches(isChecked()));
        } else {
            ClaimScreen.open.check(matches(isNotChecked()));
        }
    }

    @Step("Нажать добавить комментарий к претензии")
    public void clickAddComment() {
        SystemClock.sleep(200);
        ClaimScreen.buttonAddCommentClaim.perform(nestedScrollTo());
        ClaimScreen.buttonAddCommentClaim.perform(click());
    }

    @Step("Проверить состояние чекбокса выполнен")
    public void checkCheckboxExecuted(boolean checked) {
        if (checked) {
            ClaimScreen.executed.check(matches(isChecked()));
        } else {
            ClaimScreen.executed.check(matches(isNotChecked()));
        }
    }

    @Step("Проверить состояние чекбокса отменен")
    public void checkCheckboxCancelled(boolean checked) {
        if (checked) {
            ClaimScreen.cancelled.check(matches(isChecked()));
        } else {
            ClaimScreen.cancelled.check(matches(isNotChecked()));
        }
    }

    @Step("Кликнуть создать претензию")
    public void createClaim() {
        ClaimScreen.addNewClaimButton.perform(click());
        SystemClock.sleep(1500);
    }

    @Step("Найти претензию")
    public void searchClaim(int position) {
        ClaimScreen.description.perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Кликнуть по определенной претензии")
    public void openClaim(int index) {
        ClaimScreen.claimList(index).perform(click());
    }

    @Step("Проверка отображение кнопки закрыть")
    public void checkButtonClose() {
        ClaimScreen.buttonClose.perform(nestedScrollTo());
        ClaimScreen.buttonClose.check(matches(isDisplayed()));
    }

    @Step("Открытие нужной претензии")
    public void checkClaim(String text) {
        if (isDisplayedWithSwipe(onView(withText(text)), 0, true)) {
            onView(withText(text)).check(matches(isDisplayed()));
        }
    }

    @Step("Проверить какой статус у претензии")
    public void checkStatusClaim(String status) {
        ClaimScreen.statusClaim(status).check(matches(isDisplayed()));
    }

    @Step("Добавить комментарий при изменении статуса претензии")
    public void addCommentStatusChangedClaim(String text) {
        ClaimScreen.addTextCommentStatus.check(matches(isDisplayed()));
        ClaimScreen.addTextCommentStatus.perform(replaceText(text));
    }

    @Step("Получить название претензии")
    public String getTextClaim() {
        return Utils.TextHelpers.getText(ClaimScreen.textTitleClaim);
    }

    @Step("Проверить отображение статусов на экране при редактировании")
    public void checkStatus(String status) {
        ClaimScreen.checkTextStatusClaim(status).check(matches(isDisplayed()));
    }

    @Step("Кликнуть на нужный статус")
    public void clickStatus(String status) {
        ClaimScreen.checkTextStatusClaim(status).perform(click());
    }
}

