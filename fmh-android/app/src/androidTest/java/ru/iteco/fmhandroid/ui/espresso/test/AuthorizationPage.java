package ru.iteco.fmhandroid.ui.espresso.test;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.espresso.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.MainSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.PopupWarningStep;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationPage {
    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    CommonSteps CommonSteps = new CommonSteps();
    MainSteps MainSteps = new MainSteps();
    PopupWarningStep PopupWarningStep = new PopupWarningStep();

    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        SystemClock.sleep(3000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            CommonSteps.logout();
        }
    }

    @Test
    @DisplayName("Проверка входа с пустой формой и под несуществующим пользователем")
    public void signInWrong() {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.signIn();
        SystemClock.sleep(500);
        PopupWarningStep.checkEmptyToast(R.string.empty_login_or_password, true);
        AuthorizationSteps.enterLogin(" ");
        AuthorizationSteps.enterPassword(" ");
        AuthorizationSteps.signIn();
        SystemClock.sleep(500);
        PopupWarningStep.checkEmptyToast(R.string.empty_login_or_password, true);
        AuthorizationSteps.enterLogin("123");
        AuthorizationSteps.enterPassword("123");
        AuthorizationSteps.signIn();
        SystemClock.sleep(500);
        PopupWarningStep.checkEmptyToast(R.string.wrong_login_or_password, true);
    }

    @Test
    @DisplayName("Успешный вход за пользователя и выход")
    public void signInOK() {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.enterLogin("login2");
        AuthorizationSteps.enterPassword("password2");
        AuthorizationSteps.signIn();
        SystemClock.sleep(2500);
        MainSteps.isMainScreen();
        CommonSteps.logout();
    }
}
