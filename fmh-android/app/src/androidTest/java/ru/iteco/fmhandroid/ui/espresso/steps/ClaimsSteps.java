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
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.isDisplayedWithSwipe;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.nestedScrollTo;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.ClaimScreen;
import ru.iteco.fmhandroid.ui.espresso.elements.MainScreen;
import ru.iteco.fmhandroid.ui.espresso.resources.Resources;
import ru.iteco.fmhandroid.ui.espresso.utils.Utils;

public class ClaimsSteps {
    MainScreen MainScreen = new MainScreen();
    ClaimScreen ClaimScreen = new ClaimScreen();
    Resources Resources = new Resources();

    public void isClaimsScreen() {
        Allure.step("Проверка, что это экране претензий");
        ClaimScreen.claims.check(matches(isDisplayed()));
        ClaimScreen.addNewClaimButton.check(matches(isDisplayed()));
        ClaimScreen.buttonFiltering.check(matches(isDisplayed()));
        MainScreen.firstClaimExecutorName.check(matches(isDisplayed()));
    }

    public void openFiltering() {
        Allure.step("Открытие фильтра");
        ClaimScreen.buttonFiltering.perform(click());
        ClaimScreen.titleFiltering.check(matches(isDisplayed()));
        ClaimScreen.open.check(matches(isDisplayed()));
        ClaimScreen.inProgress.check(matches(isDisplayed()));
        ClaimScreen.executed.check(matches(isDisplayed()));
        ClaimScreen.cancelled.check(matches(isDisplayed()));
    }

    public void clickCheckboxInProgress() {
        Allure.step("Отметить чекбокс в процессе");
        ClaimScreen.inProgress.perform(click());
    }

    public void clickCheckboxOpen() {
        Allure.step("Отметить чекбокс открыт");
        ClaimScreen.open.perform(click());
    }

    public void clickCheckboxExecuted() {
        Allure.step("Отметить чекбокс выполнен");
        ClaimScreen.executed.perform(click());
    }

    public void clickCheckboxCancelled() {
        Allure.step("Отметить чекбокс отменен");
        ClaimScreen.cancelled.perform(click());
    }

    public void clickEditClaim() {
        Allure.step("Кликнуть редактировать претензию");
        ClaimScreen.buttonEditClaim.perform(nestedScrollTo());
        ClaimScreen.buttonEditClaim.perform(click());
    }

    public void clickCancel() {
        Allure.step("Кликнуть отмена");
        ClaimScreen.buttonCancel.perform(click());
    }

    public void clickOK() {
        Allure.step("Кликнуть ОК");
        ClaimScreen.buttonOk.perform(click());
    }

    public void clickButton(String text) {
        Allure.step("Кликнуть по кнопке с определенным названием");
        ClaimScreen.buttonText(text).perform(click());
    }

    public void clickButtonEditStatusClaim() {
        Allure.step("Кликнуть редактировать статус претензии");
        ClaimScreen.buttonStatusClaim.perform(nestedScrollTo());
        ClaimScreen.buttonStatusClaim.check(matches(isDisplayed()));
        ClaimScreen.buttonStatusClaim.perform(click());
    }

    public void checkCheckboxInProgress(boolean checked) {
        Allure.step("Проверить состояние чекбокса в процессе");
        if (checked) {
            ClaimScreen.inProgress.check(matches(isChecked()));
        } else {
            ClaimScreen.inProgress.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxOpen(boolean checked) {
        Allure.step("Проверить состояние чекбокса открыт");
        if (checked) {
            ClaimScreen.open.check(matches(isChecked()));
        } else {
            ClaimScreen.open.check(matches(isNotChecked()));
        }
    }

    public void clickAddComment() {
        Allure.step("Нажать добавить комментарий к претензии");
        SystemClock.sleep(200);
        ClaimScreen.buttonAddCommentClaim.perform(nestedScrollTo());
        ClaimScreen.buttonAddCommentClaim.perform(click());
    }

    public void checkCheckboxExecuted(boolean checked) {
        Allure.step("Проверить состояние чекбокса выполнен");
        if (checked) {
            ClaimScreen.executed.check(matches(isChecked()));
        } else {
            ClaimScreen.executed.check(matches(isNotChecked()));
        }
    }

    public void checkCheckboxCancelled(boolean checked) {
        Allure.step("Проверить состояние чекбокса отменен");
        if (checked) {
            ClaimScreen.cancelled.check(matches(isChecked()));
        } else {
            ClaimScreen.cancelled.check(matches(isNotChecked()));
        }
    }

    public void createClaim() {
        Allure.step("Кликнуть создать претензию");
        ClaimScreen.addNewClaimButton.perform(click());
        SystemClock.sleep(1500);
    }

    public void searchClaim(int position) {
        Allure.step("Найти претензию");
        ClaimScreen.description.perform(actionOnItemAtPosition(position, click()));
    }

    public void openClaimIndex(int index) {
        Allure.step("Кликнуть по первой претензии");
        ClaimScreen.claimList(index).perform(click());
    }

    public void checkButtonClose() {
        Allure.step("Проверка отображение кнопки закрыть");
        ClaimScreen.buttonClose.perform(nestedScrollTo());
        ClaimScreen.buttonClose.check(matches(isDisplayed()));
    }

    public void checkClaim(String text) {
        Allure.step("Открытие нужной претензии");
        if (isDisplayedWithSwipe(onView(withText(text)), 0, true)) {
            onView(withText(text)).check(matches(isDisplayed()));
        }
    }

    public void checkStatusClaim(String status) {
        Allure.step("Проверить какой статус у претензии");
        ClaimScreen.statusClaim(status).check(matches(isDisplayed()));
    }

    public void addCommentStatusChangedClaim(String text) {
        Allure.step("Добавить комментарий при изменении статуса претензии");
        ClaimScreen.addTextCommentStatus.check(matches(isDisplayed()));
        ClaimScreen.addTextCommentStatus.perform(replaceText(text));
    }

    public String getTextClaim() {
        Allure.step("Получить название претензии");
        return Utils.TextHelpers.getText(ClaimScreen.textTitleClaim);
    }

    public void checkStatus(String status) {
        Allure.step("Проверить отображение статусов на экране при редактировании");
        ClaimScreen.checkTextStatusClaim(status).check(matches(isDisplayed()));
    }

    public void clickStatus(String status) {
        Allure.step("Кликнуть на нужный статус");
        ClaimScreen.checkTextStatusClaim(status).perform(click());
    }

    public void checkCommentOnClaim(String text) {
        Allure.step("Проверка комментария к претензии");
        ClaimScreen.comment(text).check(matches(isDisplayed()));
    }

    public void checkModifiedClaim() {
        Allure.step("Проверка измененной претензии");
        getTextClaim().matches(String.valueOf(not(isDisplayed())));
        Resources.textNewClaim.matches(String.valueOf(isDisplayed()));
    }
}

