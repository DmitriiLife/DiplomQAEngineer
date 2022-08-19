package ru.iteco.fmhandroid.ui.espresso.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.espresso.elements.CreateNewsScreen;

public class CreateNewsSteps {

    CreateNewsScreen CreateNewsScreen = new CreateNewsScreen();

    public void isCreateNewsScreen() {
        Allure.step("Проверка, что это экран создания новости");
        CreateNewsScreen.title.check(matches(withText("Creating")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
    }

    public void isEditNewsScreen() {
        Allure.step("Проверка, что это экран редактирования новости");
        CreateNewsScreen.title.check(matches(withText("Editing")));
        CreateNewsScreen.subTitle.check(matches(withText("News")));
    }

    public void selectNewsCategory() {
        Allure.step("Выбрать категорию новости");
        CreateNewsScreen.categoryList.perform(click());
        CreateNewsScreen.newsTitle.perform(click());
    }

    public void enterNewsCategory(String text) {
        Allure.step("Ввести категорию новости");
        CreateNewsScreen.categoryList.perform(replaceText(text), closeSoftKeyboard());
    }

    public void enterNewsTitle(String text) {
        Allure.step("Ввести заголовок");
        CreateNewsScreen.newsTitle.perform(replaceText(text), closeSoftKeyboard());
    }

    public void enterNewsPublicationDate(String text) {
        Allure.step("Ввести дату публикации");
        CreateNewsScreen.newsDate.perform(replaceText(text));
    }

    public void enterNewsTime(String text) {
        Allure.step("Ввести время");
        CreateNewsScreen.newsTime.perform(replaceText(text));
    }

    public void enterNewsDescription(String text) {
        Allure.step("Ввести описание");
        CreateNewsScreen.newsDescription.perform(replaceText(text), closeSoftKeyboard());
    }

    public void checkNewsTitle(String text) {
        Allure.step("Проверить заголовок");
        CreateNewsScreen.newsTitle.check(matches(withText(text)));
    }

    public void checkNewsSwitcher() {
        Allure.step("Проверить переключатель");
        CreateNewsScreen.newsSwitcher.check(matches(allOf(withText("Active"), isDisplayed())));
    }

    public void clickNewsSwitcher() {
        Allure.step("Щелкнуть переключатель");
        CreateNewsScreen.newsSwitcher.perform(click());
    }
}
