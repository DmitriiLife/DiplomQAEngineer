package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.SplashScreen;

public class SplashSteps {
    SplashScreen SplashScreen = new SplashScreen();

    public void isSplashScreen() {
        Allure.step("Проверка, что это экран загрузки");
        SplashScreen.imageSplashPage.check(matches(isDisplayed()));
        SplashScreen.textSplashPage.check(matches(isDisplayed()));
        SplashScreen.progressBarSplashPage.check(matches(isDisplayed()));
    }
}
