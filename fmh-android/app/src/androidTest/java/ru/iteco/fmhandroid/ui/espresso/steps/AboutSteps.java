package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.ui.espresso.elements.AboutScreen;

public class AboutSteps {
    AboutScreen AboutScreen = new AboutScreen();

    @Step("Проверка version")
    public void checkVersionTitle() {
        AboutScreen.versionTitle.check(matches(allOf(withText("Version:"), isDisplayed())));
        AboutScreen.version.check(matches(allOf(withText("1.0.0"), isDisplayed())));
    }

    @Step("Проверка privacyPolicy")
    public void checkPrivacyPolicy() {
        AboutScreen.privacyPolicy.check(matches(allOf(withText("https://vhospice.org/#/privacy-policy/"),
                isDisplayed(), isClickable())));
    }

    @Step("Кликнуть privacyPolicy")
    public void clickPrivacyPolicy() {
        AboutScreen.privacyPolicy.check(matches(allOf(withText("https://vhospice.org/#/privacy-policy/")))).perform(click());
    }

    @Step("Кликнуть termsUrl")
    public void clickTermsUrl() {
        AboutScreen.termsUrl.perform(click());
    }

    @Step("Проверка termsTitle")
    public void checkTermsTitle() {
        AboutScreen.termsTitle.check(matches(allOf(withText("Terms of use:"), isDisplayed())));
    }

    @Step("Проверка termsUrl")
    public void checkTermsUrl() {
        AboutScreen.termsUrl.check(matches(allOf(withText("https://vhospice.org/#/terms-of-use"), isDisplayed(), isClickable())));
    }

    @Step("Проверка copyright")
    public void checkCopyright() {
        AboutScreen.copyright.check(matches(allOf(withText("© I-Teco, 2022"), isDisplayed())));
    }

    @Step("Возврат к предыдущему экрану")
    public void goBack() {
        AboutScreen.buttonBack.perform(click());
    }
}
