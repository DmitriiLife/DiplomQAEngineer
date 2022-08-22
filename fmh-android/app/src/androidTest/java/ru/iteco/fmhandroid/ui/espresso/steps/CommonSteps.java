package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;

import android.os.SystemClock;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.CommonElements;

public class CommonSteps {
    CommonElements CommonElements = new CommonElements();

    public void logout() {
        Allure.step("Кликнуть выйти из приложения");
        CommonElements.manImage.perform(click());
        CommonElements.exitButton.perform(click());
    }

    public void clickSave() {
        Allure.step("Кликнуть сохранить");
        CommonElements.buttonSave.perform(click());
        SystemClock.sleep(1500);
    }

    public void clickOK() {
        Allure.step("Кликнуть ОК");
        CommonElements.buttonOkText.perform(click());
    }

    public void clickCancel() {
        Allure.step("Кликнуть отмена");
        CommonElements.buttonCancel.perform(click());
    }

    public void goToThematicQuotes() {
        Allure.step("Перейти в тематические цитаты");
        CommonElements.thematicQuotes.perform(click());
    }

    public void goToScreen(String screen) {
        Allure.step("Перейти к нужному экрану через меню");
        CommonElements.mainMenu.perform(click());
        switch (screen) {
            case ("Main"):
                CommonElements.menuMain.perform(click());
                break;
            case ("News"):
                CommonElements.menuNews.perform(click());
                break;
            case ("About"):
                CommonElements.menuAbout.perform(click());
                break;
            case ("Claims"):
                CommonElements.menuClaims.perform(click());
                break;
        }
    }
}
