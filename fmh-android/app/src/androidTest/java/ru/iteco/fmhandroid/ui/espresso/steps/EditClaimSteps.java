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

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.espresso.elements.EditClaimScreen;
import ru.iteco.fmhandroid.ui.espresso.utils.Utils;

public class EditClaimSteps {
    EditClaimScreen EditClaimScreen = new EditClaimScreen();

    @Step("Проверка, что это экран редактирования претензии")
    public void isClaimsEditScreen() {
        EditClaimScreen.claimStatus.check(matches(isDisplayed()));
    }

    @Step("Нажать кнопку вернуться назад")
    public void backFromClaim() {
        EditClaimScreen.backButton.perform(nestedScrollTo());
        EditClaimScreen.backButton.perform(click());
    }

    @Step("Нажать кнопку редактировать комментарий")
    public void buttonEditComment(int index) {
        EditClaimScreen.backButton.perform(nestedScrollTo());
        EditClaimScreen.appButton(index).check(matches(isDisplayed()));
        EditClaimScreen.appButton(index).perform(click());
    }

    @Step("Получить название комментария")
    public String getTextComment() {
        return Utils.TextHelpers.getText(EditClaimScreen.textDescription);
    }

    @Step("Открытие нужной претензии")
    public void checkTextDescription(String text, boolean checked) {
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
