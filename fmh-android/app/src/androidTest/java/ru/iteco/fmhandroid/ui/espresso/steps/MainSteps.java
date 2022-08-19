package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static org.hamcrest.CoreMatchers.not;

import android.os.SystemClock;

import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.MainScreen;

public class MainSteps {
    MainScreen MainScreen = new MainScreen();

    public void isMainScreen() {
        Allure.step("Проверка, что это главный экран");
        MainScreen.tradeMarkImage.check(matches(isDisplayed()));
        MainScreen.news.check(matches(isDisplayed()));
        MainScreen.claims.check(matches(isDisplayed()));
        MainScreen.allNews.check(matches(isDisplayed()));
        MainScreen.allClaims.check(matches(isDisplayed()));
    }

    public void expandAllNews() {
        Allure.step("Свернуть/Развернуть блок новостей");
        MainScreen.expandAllNews.check(matches(isDisplayed()));
        MainScreen.expandAllNews.perform(click());
    }

    public void expandAllClaims() {
        Allure.step("Свернуть/Развернуть блок претензий");
        MainScreen.expandClaims.check(matches(isDisplayed()));
        MainScreen.expandClaims.perform(click());
    }

    public void allNewsNotDisplayed() {
        Allure.step("Не видно ссылки Все новости");
        MainScreen.allNews.check(matches(not(isDisplayed())));
    }

    public void allNewsDisplayed() {
        Allure.step("Видна ссылка Все новости");
        MainScreen.allNews.check(matches(isDisplayed()));
    }

    public void allClaimsNotDisplayed() {
        Allure.step("Не видно ссылки 'Все претензии'");
        MainScreen.allClaims.check(matches(not(isDisplayed())));
    }

    public void allClaimsDisplayed() {
        Allure.step("Видна ссылка 'Все претензии'");
        MainScreen.allClaims.check(matches(isDisplayed()));
    }

    public void openAllNews() {
        Allure.step("Кликнуть Все новости");
        MainScreen.allNews.check(matches(isDisplayed()));
        MainScreen.allNews.perform(click());
    }

    public void openAllClaims() {
        Allure.step("Кликнуть Все претензии");
        MainScreen.allClaims.check(matches(isDisplayed()));
        MainScreen.allClaims.perform(click());
    }

    public void expandSingleNews() {
        Allure.step("Развернуть новость");
        MainScreen.expandSingleNews.perform(actionOnItemAtPosition(0, click()));
        MainScreen.newsDescription.check(matches(isDisplayed()));
    }

    public void collapseSingleNews() {
        Allure.step("Свернуть новость");
        MainScreen.categoryIcon.perform(click());
        MainScreen.newsDescriptionAfterCollapse.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    public void openSingleClaim() {
        Allure.step("Открыть претензию");
        MainScreen.firstClaimExecutorName.check(matches(isDisplayed()));
        MainScreen.firstClaimExecutorName.perform(actionOnItemAtPosition(0, click()));
        SystemClock.sleep(2000);
    }

    public void createClaim() {
        Allure.step("Создать претензию");
        MainScreen.addNewClaimButton.perform(click());
        SystemClock.sleep(1000);
    }
}
