package ru.iteco.fmhandroid.ui.espresso.test;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.espresso.steps.SplashSteps;

@RunWith(AllureAndroidJUnit4.class)
public class SplashPage {
    SplashSteps SplashSteps = new SplashSteps();

    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Test
    @DisplayName("Проверка отображения элементов на экране")
    public void checkSplashPage() {
        SplashSteps.isSplashScreen();
    }
}
