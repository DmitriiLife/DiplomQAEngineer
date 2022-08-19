package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.isDisplayedWithSwipe;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.nestedScrollTo;

import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.EditClaimScreen;
import ru.iteco.fmhandroid.ui.espresso.utils.Utils;

public class EditClaimSteps {
    EditClaimScreen EditClaimScreen = new EditClaimScreen();

    public void isClaimsEditScreen() {
        Allure.step("Проверка, что это экран редактирования претензии");
        EditClaimScreen.claimStatus.check(matches(isDisplayed()));
    }

    public void backFromClaim() {
        Allure.step("Нажать кнопку вернуться назад");
        EditClaimScreen.backButton.perform(nestedScrollTo());
        EditClaimScreen.backButton.perform(click());
    }

    public void buttonEditComment(int index) {
        Allure.step("Нажать кнопку редактировать комментарий");
        EditClaimScreen.backButton.perform(nestedScrollTo());
        EditClaimScreen.appButton(index).check(matches(isDisplayed()));
        EditClaimScreen.appButton(index).perform(click());
    }

    public String getTextComment() {
        Allure.step("Получить название комментария");
        return Utils.TextHelpers.getText(EditClaimScreen.textDescription);
    }

    public void checkTextDescription(String text, boolean checked) {
        Allure.step("Открытие нужной претензии");
        if (checked) {
            onView(withText(text)).check(matches(isDisplayed()));
        } else {
            onView(withText(text)).check(matches(isNotChecked()));
        }
        if (isDisplayedWithSwipe(onView(withText(text)), 0, true)) {
            onView(withText(text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        }
    }
}
