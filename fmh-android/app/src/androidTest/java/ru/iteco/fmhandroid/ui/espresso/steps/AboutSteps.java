package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.AboutScreen;

public class AboutSteps {
    AboutScreen AboutScreen = new AboutScreen();

    public void checkVersionTitle() {
        Allure.step("Проверка version");
        AboutScreen.versionTitle.check(matches(allOf(withText("Version:"), isDisplayed())));
        AboutScreen.version.check(matches(allOf(withText("1.0.0"), isDisplayed())));
    }

    public void checkPrivacyPolicy() {
        Allure.step("Проверка privacyPolicy");
        AboutScreen.privacyPolicy.check(matches(allOf(withText("https://vhospice.org/#/privacy-policy/"),
                isDisplayed(), isClickable())));
    }

    public void clickPrivacyPolicy() {
        Allure.step("Кликнуть privacyPolicy");
        AboutScreen.privacyPolicy.check(matches(allOf(withText("https://vhospice.org/#/privacy-policy/")))).perform(click());
    }

    public void clickTermsUrl() {
        Allure.step("Кликнуть termsUrl");
        AboutScreen.termsUrl.perform(click());
    }

    public void checkTermsTitle() {
        Allure.step("Проверка termsTitle");
        AboutScreen.termsTitle.check(matches(allOf(withText("Terms of use:"), isDisplayed())));
    }

    public void checkTermsUrl() {
        Allure.step("Проверка termsUrl");
        AboutScreen.termsUrl.check(matches(allOf(withText("https://vhospice.org/#/terms-of-use"), isDisplayed(), isClickable())));
    }

    public void checkCopyright() {
        Allure.step("Проверка copyright");
        AboutScreen.copyright.check(matches(allOf(withText("© I-Teco, 2022"), isDisplayed())));
    }

    public void goBack() {
        Allure.step("Возврат к предыдущему экрану");
        AboutScreen.buttonBack.perform(click());
    }
}
