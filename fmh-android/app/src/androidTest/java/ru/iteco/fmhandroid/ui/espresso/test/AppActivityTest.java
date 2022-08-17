package ru.iteco.fmhandroid.ui.espresso.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.checkClaimStatus;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.getCurrentDate;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.getCurrentTime;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.isDisplayedWithSwipe;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.pressBack;

import android.content.Intent;
import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.espresso.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.AddNewCommentStep;
import ru.iteco.fmhandroid.ui.espresso.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.CreateClaimSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.CreateNewsSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.EditClaimSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.MainSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.NewsFilterSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.NewsSteps;
import ru.iteco.fmhandroid.ui.espresso.steps.ThematicQuotesSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AppActivityTest {
    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    MainSteps MainSteps = new MainSteps();
    NewsSteps NewsSteps = new NewsSteps();
    ClaimsSteps ClaimsSteps = new ClaimsSteps();
    EditClaimSteps EditClaimSteps = new EditClaimSteps();
    CreateClaimSteps CreateClaimSteps = new CreateClaimSteps();
    CommonSteps CommonSteps = new CommonSteps();
    ControlPanelSteps ControlPanelSteps = new ControlPanelSteps();
    CreateNewsSteps CreateNewsSteps = new CreateNewsSteps();
    NewsFilterSteps NewsFilterSteps = new NewsFilterSteps();
    AboutSteps AboutSteps = new AboutSteps();
    ThematicQuotesSteps ThematicQuotesSteps = new ThematicQuotesSteps();
    AddNewCommentStep AddNewCommentStep = new AddNewCommentStep();

    public static String newsTitleString = "Некий заголовок должен быть тут " + getCurrentDate() + "You" + getCurrentTime();
    public static String newsDescriptionString = "Проба перуэта " + getCurrentTime();
    public static String newNewsTitle = "Чудо чудесное из чудес " + getCurrentDate() + "You" + getCurrentTime();
    public static String newCommentClaim = "Вот время " + getCurrentTime();
    public static String comment = "Вот и пришла зима " + getCurrentDate();
    String newsPublicationDate = getCurrentDate();
    String newsTime = getCurrentTime();

    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    @Before
    public void loginCheck() {
        SystemClock.sleep(7000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.enterLogin("login2");
        AuthorizationSteps.enterPassword("password2");
        AuthorizationSteps.signIn();
        SystemClock.sleep(2000);
    }

    @Test
    @DisplayName("Создание претензии с пустой датой")
    public void createClaimSpaceDate() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(" ");
        CreateClaimSteps.enterClaimTime(newsTime);
        CreateClaimSteps.enterClaimDescription(newsDescriptionString);
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создание претензии с пустыми данными")
    public void createClaimEmptyData() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(" ");
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(" ");
        CreateClaimSteps.enterClaimTime(" ");
        CreateClaimSteps.enterClaimDescription(" ");
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создание претензии с пустым временем")
    public void createClaimSpaceTime() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime(" ");
        CreateClaimSteps.enterClaimDescription(newsDescriptionString);
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Разворачивание и сворачивание блока новостей и претензий")
    public void expandAll() {
        MainSteps.expandAllNews();
        MainSteps.allNewsNotDisplayed();
        MainSteps.expandAllClaims();
        MainSteps.allClaimsNotDisplayed();
        MainSteps.expandAllNews();
        MainSteps.allNewsDisplayed();
        MainSteps.expandAllClaims();
        MainSteps.allClaimsDisplayed();
    }

    @Test
    @DisplayName("Открытие экрана новостей")
    public void openAllNews() {
        MainSteps.openAllNews();
        NewsSteps.isNewsScreen();
    }

    @Test
    @DisplayName("Проверка, главного экрана")
    public void checkIsMain() {
        MainSteps.isMainScreen();
    }

    @Test
    @DisplayName("Открытие экрана претензий")
    public void openAllClaims() {
        MainSteps.openAllClaims();
        ClaimsSteps.isClaimsScreen();
    }

    @Test
    @DisplayName("Разворачивание и сворачивание одной новости")
    public void expandSingleNews() {
        MainSteps.expandSingleNews();
        MainSteps.collapseSingleNews();
    }

    @Test
    @DisplayName("Сворачивание блока News")
    public void closeBlockNews() {
        MainSteps.isMainScreen();
        MainSteps.allNewsDisplayed();
        MainSteps.expandAllNews();
        MainSteps.allNewsNotDisplayed();
        SystemClock.sleep(2000);
    }

    @Test
    @DisplayName("Сворачивание блока Claims")
    public void closeBlockClaims() {
        MainSteps.isMainScreen();
        MainSteps.allClaimsDisplayed();
        MainSteps.expandAllClaims();
        MainSteps.allClaimsNotDisplayed();
        SystemClock.sleep(2000);
    }

    @Test
    @DisplayName("Открытие претензии")
    public void openSingleClaim() {
        closeBlockNews();
        MainSteps.openSingleClaim();
        EditClaimSteps.isClaimsEditScreen();
    }

    @Test
    @DisplayName("Открытие претензии и возврат из нее")
    public void openClaimAndClose() {
        closeBlockNews();
        MainSteps.openSingleClaim();
        EditClaimSteps.isClaimsEditScreen();
        EditClaimSteps.backFromClaim();
        MainSteps.isMainScreen();
    }

    @Test
    @DisplayName("Фильтр претензий")
    public void filteringClaims() {
        MainSteps.openAllClaims();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.clickCancel();
        ClaimsSteps.openFiltering();
        ClaimsSteps.checkCheckboxInProgress(true);
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.clickOK();
        checkClaimStatus("Open");
        ClaimsSteps.isClaimsScreen();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxOpen();
        ClaimsSteps.checkCheckboxOpen(false);
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxInProgress(true);
        ClaimsSteps.clickOK();
        checkClaimStatus("In progress");
        ClaimsSteps.isClaimsScreen();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxExecuted();
        ClaimsSteps.checkCheckboxExecuted(true);
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxInProgress(false);
        ClaimsSteps.clickOK();
        checkClaimStatus("Executed");
        ClaimsSteps.isClaimsScreen();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxCancelled();
        ClaimsSteps.checkCheckboxCancelled(true);
        ClaimsSteps.clickCheckboxExecuted();
        ClaimsSteps.checkCheckboxExecuted(false);
        ClaimsSteps.clickOK();
        checkClaimStatus("Canceled");
        ClaimsSteps.isClaimsScreen();
    }

    @Test
    @DisplayName("Открытие экрана претензий из меню и переход к созданию претензии")
    public void claimScreen() {
        CommonSteps.goToScreen("Claims");
        ClaimsSteps.isClaimsScreen();
        ClaimsSteps.createClaim();
        CreateClaimSteps.isCreateClaimsScreen();
    }

    @Test
    @DisplayName("Сортировка новостей на экране управления")
    public void controlPanelSorting() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        String firstNews = NewsSteps.getFirstNewsTitle();
        String firstPublicationDate = ControlPanelSteps.getFirstNewsPublicationDate();
        String firstCreationDate = ControlPanelSteps.getFirstNewsCreationDate();
        NewsSteps.clickSortButton();
        String lastPublicationDate = ControlPanelSteps.getLastNewsPublicationDate();
        NewsSteps.clickSortButton();
        String firstNewsAgain = NewsSteps.getFirstNewsAgainTitle();
        String firstPublicationDateAgain = ControlPanelSteps.getFirstNewsPublicationDateAgain();
        String firstCreationDateAgain = ControlPanelSteps.getFirstNewsCreationDateAgain();
        assertEquals(firstNews, firstNewsAgain);
        assertEquals(firstPublicationDate, firstPublicationDateAgain);
        assertEquals(firstCreationDate, firstCreationDateAgain);
        assertNotEquals(firstPublicationDate, lastPublicationDate);
    }

    @Test
    @DisplayName("Создание валидной новости, поиск и удаление")
    public void controlPanelCreateNews() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        ControlPanelSteps.isControlPanel();
        SystemClock.sleep(5000);
        if (isDisplayedWithSwipe(onView(withText(newsTitleString)), 3, true)) {
            onView(withText(newsTitleString)).check(matches(isDisplayed()));
        }

        onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newsTitleString)))))))).perform(click());
        CommonSteps.clickOK();
    }

    @Test
    @DisplayName("Сортировка новостей, удаление созданной")
    public void newsScreenFiltering() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        ControlPanelSteps.isControlPanel();
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(newsPublicationDate);
        NewsFilterSteps.clickFilter();
        NewsSteps.checkFirstNewsDate(newsPublicationDate);
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(newsPublicationDate);
        NewsFilterSteps.clickFilter();
        ControlPanelSteps.checkFirstPublicationDate(newsPublicationDate);
        ControlPanelSteps.clickEditNews();
        CreateNewsSteps.clickNewsSwitcher();
        CommonSteps.clickSave();
        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(newsPublicationDate);
        NewsFilterSteps.clickCheckboxActive();
        NewsFilterSteps.checkCheckboxActive(false);
        NewsFilterSteps.checkCheckboxNotActive(true);
        NewsFilterSteps.clickFilter();
        ControlPanelSteps.checkFirstPublicationDateNotActive(newsPublicationDate);
        ControlPanelSteps.checkNewsStatus();
        ControlPanelSteps.checkNewsStatusNotActive();
        CreateNewsSteps.clickNewsSwitcher();
        CommonSteps.clickSave();
        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(newsPublicationDate);
        NewsFilterSteps.checkCheckboxActive(true);
        NewsFilterSteps.clickCheckboxNotActive();
        NewsFilterSteps.checkCheckboxNotActive(false);
        NewsFilterSteps.clickFilter();
        ControlPanelSteps.checkFirstPublicationDateActive(newsPublicationDate);
        ControlPanelSteps.checkNewsStatusActive();
        ControlPanelSteps.clickDeleteNews();
        CommonSteps.clickOK();
    }

    @Test
    @DisplayName("Открытие экрана тематических цитат и взаимодействие с цитатами")
    public void thematicQuotes() {
        CommonSteps.goToThematicQuotes();
        ThematicQuotesSteps.checkAll();
        //0
        ThematicQuotesSteps.checkTextQuote("«Хоспис для меня - это то, каким должен быть мир.\"");
        ThematicQuotesSteps.checkNotTextQuote("\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер");
        ThematicQuotesSteps.openCloseQuote(0);
        ThematicQuotesSteps.checkTextQuote("\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер");
        //1
        ThematicQuotesSteps.checkTextQuote("Хоспис в своем истинном понимании - это творчество");
        ThematicQuotesSteps.checkNotTextQuote("Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий.");
        ThematicQuotesSteps.openCloseQuote(1);
        ThematicQuotesSteps.checkTextQuote("Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий.");
        //2
        ThematicQuotesSteps.checkTextQuote("“В хосписе не работают плохие люди” В.В. Миллионщикова\"");
        ThematicQuotesSteps.checkNotTextQuote("Все сотрудники хосписа - это адвокаты пациента, его прав и потребностей. Поиск путей решения различных задач - это и есть хосписный индивидуальный подход к паллиативной помощи.");
        ThematicQuotesSteps.openCloseQuote(2);
        ThematicQuotesSteps.checkTextQuote("Все сотрудники хосписа - это адвокаты пациента, его прав и потребностей. Поиск путей решения различных задач - это и есть хосписный индивидуальный подход к паллиативной помощи.");
        //3
        ThematicQuotesSteps.checkTextQuote("«Хоспис – это философия, из которой следует сложнейшая наука медицинской помощи умирающим и искусство ухода, в котором сочетается компетентность и любовь» С. Сандерс");
        ThematicQuotesSteps.checkNotTextQuote("“Творчески и осознанно подойти к проектированию опыта умирания. Создать пространство физическое и психологическое, чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с дороги. Тогда старение и умирание могут стать процессом восхождения до самого конца” \n" +
                "Би Джей Миллер, врач, руководитель проекта \"Дзен-хоспис\"");
        ThematicQuotesSteps.openCloseQuote(3);
        ThematicQuotesSteps.checkTextQuote("“Творчески и осознанно подойти к проектированию опыта умирания. Создать пространство физическое и психологическое, чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с дороги. Тогда старение и умирание могут стать процессом восхождения до самого конца” \\n\" +\n" +
                "                \"Би Джей Миллер, врач, руководитель проекта \\\"Дзен-хоспис\\\"");
        //4
        ThematicQuotesSteps.checkTextQuote("Служение человеку с теплом, любовью и заботой");
        ThematicQuotesSteps.checkNotTextQuote("\"Если пациента нельзя вылечить, это не значит, что для него ничего нельзя сделать. То, что кажется мелочью, пустяком в жизни здорового человека - для пациента имеет огромный смысл.\"");
        ThematicQuotesSteps.openCloseQuote(4);
        ThematicQuotesSteps.checkTextQuote("\"Если пациента нельзя вылечить, это не значит, что для него ничего нельзя сделать. То, что кажется мелочью, пустяком в жизни здорового человека - для пациента имеет огромный смысл.\"");
        //5
        ThematicQuotesSteps.checkTextQuote("\"Хоспис продлевает жизнь, дает надежду, утешение и поддержку.\"");
        ThematicQuotesSteps.checkNotTextQuote("\" Хоспис - это мои новые друзья. Полная перезагрузка жизненных ценностей. В хосписе нет страха и одиночества.\"\n" +
                "Евгения Белоусова, дочь пациентки Ольги Васильевны");
        ThematicQuotesSteps.openCloseQuote(5);
        ThematicQuotesSteps.checkTextQuote("\" Хоспис - это мои новые друзья. Полная перезагрузка жизненных ценностей. В хосписе нет страха и одиночества.\"\n" +
                "Евгения Белоусова, дочь пациентки Ольги Васильевны");
        //6
        ThematicQuotesSteps.checkTextQuote("\"Двигатель хосписа - милосердие плюс профессионализм\"\n" +
                "А.В. Гнездилов, д.м.н., один из пионеров хосписного движения.");
        ThematicQuotesSteps.checkNotTextQuote("\"Делай добро... А добро заразительно. По-моему, все люди милосердны. Нужно просто говорить с ними об этом, суметь разбудить в них чувство сострадания, заложенное от рождения\" - В.В. Миллионщикова");
        ThematicQuotesSteps.openCloseQuote(6);
        ThematicQuotesSteps.checkTextQuote("\"Делай добро... А добро заразительно. По-моему, все люди милосердны. Нужно просто говорить с ними об этом, суметь разбудить в них чувство сострадания, заложенное от рождения\" - В.В. Миллионщикова");
        //7
        ThematicQuotesSteps.checkTextQuote("Важен каждый!");
        ThematicQuotesSteps.checkNotTextQuote("\"Каждый, кто оказывается в стенах хосписа, имеет огромное значение в жизни хосписа и его подопечных\"");
        ThematicQuotesSteps.openCloseQuote(7);
        ThematicQuotesSteps.checkTextQuote("\"Каждый, кто оказывается в стенах хосписа, имеет огромное значение в жизни хосписа и его подопечных\"");
    }

    @Test
    @DisplayName("Добавить комментарий к претензии")
    public void addCommentTheClaim() {
        MainSteps.openAllClaims();
        ClaimsSteps.searchClaim(0);
        SystemClock.sleep(200);
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(newCommentClaim);
        AddNewCommentStep.clickCancel();
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(newCommentClaim);
        AddNewCommentStep.clickSave();
        ClaimsSteps.checkButtonClose();
        onView(withText(newCommentClaim)).check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Редактирование претензии")
    public void editingClaimFromTheMainScreen() {
        MainSteps.isMainScreen();
        MainSteps.openAllClaims();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxCancelled(false);
        ClaimsSteps.checkCheckboxExecuted(false);
        ClaimsSteps.checkCheckboxInProgress(false);
        ClaimsSteps.checkCheckboxOpen(true);
        ClaimsSteps.clickOK();
        SystemClock.sleep(2000);
        ClaimsSteps.openClaim(0);
        SystemClock.sleep(500);
        String textOldClaim = ClaimsSteps.getTextClaim();
        String textNewClaim = "Arrr " + getCurrentTime();
        ClaimsSteps.checkStatusClaim("Open");
        ClaimsSteps.clickEditClaim();
        CreateClaimSteps.enterClaimTitle(textNewClaim);
        CommonSteps.clickSave();
        textOldClaim.matches(String.valueOf(not(isDisplayed())));
        textNewClaim.matches(String.valueOf(isDisplayed()));
    }

    @Test
    @DisplayName("Измененить статус у претензии c 'Open' на 'In progress'")
    public void editStatusTheClaimOpenOnTheInProgress() {
        MainSteps.isMainScreen();
        MainSteps.openAllClaims();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxCancelled(false);
        ClaimsSteps.checkCheckboxExecuted(false);
        ClaimsSteps.checkCheckboxInProgress(false);
        ClaimsSteps.checkCheckboxOpen(true);
        ClaimsSteps.clickOK();
        SystemClock.sleep(2000);
        ClaimsSteps.openClaim(0);
        ClaimsSteps.checkStatusClaim("Open");
        ClaimsSteps.clickButtonEditStatusClaim();
        ClaimsSteps.checkStatus("take to work");
        ClaimsSteps.checkStatus("Cancel");
        ClaimsSteps.clickStatus("take to work");
        SystemClock.sleep(1000);
        ClaimsSteps.checkStatusClaim("In progress");
    }

    @Test
    @DisplayName("Измененить статус у претензии c 'Open' на 'Executed'")
    public void editStatusTheClaimOpenOnTheExecuted() {
        MainSteps.isMainScreen();
        MainSteps.openAllClaims();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxCancelled(false);
        ClaimsSteps.checkCheckboxExecuted(false);
        ClaimsSteps.checkCheckboxInProgress(false);
        ClaimsSteps.checkCheckboxOpen(true);
        ClaimsSteps.clickOK();
        SystemClock.sleep(2000);
        ClaimsSteps.openClaim(0);
        SystemClock.sleep(500);
        ClaimsSteps.checkStatusClaim("Open");
        ClaimsSteps.clickButtonEditStatusClaim();
        ClaimsSteps.checkStatus("take to work");
        ClaimsSteps.checkStatus("Cancel");
        ClaimsSteps.clickStatus("take to work");
        SystemClock.sleep(1000);
        ClaimsSteps.checkStatusClaim("In progress");
        ClaimsSteps.clickButtonEditStatusClaim();
        ClaimsSteps.checkStatus("Throw off");
        ClaimsSteps.checkStatus("To execute");
        ClaimsSteps.clickStatus("To execute");
        ClaimsSteps.addCommentStatusChangedClaim("так надо мне");
        ClaimsSteps.clickButton("OK");
        SystemClock.sleep(1000);
        ClaimsSteps.checkStatusClaim("Executed");
    }

    @Test
    @DisplayName("Измененить статус у претензии c 'Open' на 'In progress' и вернуть 'Open'")
    public void editStatusTheClaimOpenOnTheInProgressAndBackOpen() {
        MainSteps.isMainScreen();
        MainSteps.openAllClaims();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxCancelled(false);
        ClaimsSteps.checkCheckboxExecuted(false);
        ClaimsSteps.checkCheckboxInProgress(false);
        ClaimsSteps.checkCheckboxOpen(true);
        ClaimsSteps.clickOK();
        SystemClock.sleep(1000);
        ClaimsSteps.openClaim(0);
        SystemClock.sleep(500);
        ClaimsSteps.checkStatusClaim("Open");
        ClaimsSteps.clickButtonEditStatusClaim();
        ClaimsSteps.checkStatus("take to work");
        ClaimsSteps.checkStatus("Cancel");
        ClaimsSteps.clickStatus("take to work");
        SystemClock.sleep(1000);
        ClaimsSteps.checkStatusClaim("In progress");
        SystemClock.sleep(1000);
        ClaimsSteps.clickButtonEditStatusClaim();
        ClaimsSteps.checkStatus("Throw off");
        ClaimsSteps.checkStatus("To execute");
        ClaimsSteps.clickStatus("Throw off");
        ClaimsSteps.addCommentStatusChangedClaim("так надо");
        ClaimsSteps.clickButton("OK");
        SystemClock.sleep(1000);
        ClaimsSteps.checkStatusClaim("Open");
    }

    @Test
    @DisplayName("Открытие экрана About и возврат на Main")
    public void aboutScreenAndBackToMain() {
        CommonSteps.goToScreen("About");
        AboutSteps.checkCopyright();
        AboutSteps.checkPrivacyPolicy();
        AboutSteps.checkTermsTitle();
        AboutSteps.checkTermsUrl();
        AboutSteps.checkVersionTitle();
        AboutSteps.goBack();
        MainSteps.isMainScreen();
    }

    @Test
    @DisplayName("Добавить пустой комментарий к претензии")
    public void addSpaceCommentTheClaim() {
        ViewInteraction emptyToast = onView(withText(R.string.toast_empty_field)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.openAllClaims();
        ClaimsSteps.searchClaim(0);
        SystemClock.sleep(200);
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(" ");
        AddNewCommentStep.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создание претензии с пустым описанием")
    public void createClaimSpaceDescription() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime(getCurrentTime());
        CreateClaimSteps.enterClaimDescription(" ");
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создание претензии, нажать кнопку отмена")
    public void createClaimClickButtonCancel() {
        ViewInteraction emptyToast = onView(withText(R.string.cancellation)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime(getCurrentTime());
        CreateClaimSteps.enterClaimDescription(newsDescriptionString);
        CommonSteps.clickCancel();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Открыть News, открыть экран управления новостями и назад к экрану новостей")
    public void openNewsOpenControlPanelAndBackNewsScreen() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        pressBack();
        SystemClock.sleep(2000);

    }

    @Test
    @DisplayName("Создать/Открыть подробное описание новости и удалить")
    public void openDescriptionNews() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        ControlPanelSteps.isControlPanel();
        SystemClock.sleep(2000);
        if (isDisplayedWithSwipe(onView(withText(newsTitleString)), 3, true)) {
            onView(withText(newsTitleString)).check(matches(isDisplayed()));
        }
        onView(allOf(withId(R.id.view_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newsTitleString)))))))).perform(click());
        onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(newsTitleString)))))))).perform(click());
        CommonSteps.clickOK();
    }

    @Test
    @DisplayName("Создать пустую форму новости")
    public void createNewsEmptyData() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        SystemClock.sleep(1000);
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость, поле 'Category' оставить пустыми")
    public void createNewsCategoryEmpty() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость, поле 'Category' заполнить произвольно")
    public void createNewsCategoryArbitrarily() {
        ViewInteraction emptyToast = onView(withText(R.string.error_saving)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.enterNewsCategory("Привет");
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость, поле 'Category' заполнить символами")
    public void createNewsCategorySymbols() {
        ViewInteraction emptyToast = onView(withText(R.string.error_saving)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.enterNewsCategory("$#%#%##");
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость, в поле 'Title' ввести пробел")
    public void createNewsTitleSpace() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(" ");
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость, в поле 'Title' ввести символы")
    public void createNewsTitleSymbols() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle("№%№%,.№%№%");
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();

        onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText("№%№%,.№%№%")))))))).perform(click());
        CommonSteps.clickOK();
    }

    @Test
    @DisplayName("Создать новость, в поле 'Publication date' ввести пробел")
    public void createNewsPublicationDateSpace() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(" ");
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость, в поле 'Time' ввести пробел")
    public void createNewsTimeSpace() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(" ");
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Проверка ссылки 'TermsUrl'")
    public void checkIntentTermsUrl() {
        CommonSteps.goToScreen("About");
        Intents.init();//start recording
        AboutSteps.clickTermsUrl();
        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData("https://vhospice.org/#/terms-of-use")
        ));
        Intents.release(); //Clean recorded
    }

    @Test
    @DisplayName("Проверка ссылки 'privacy_policy'")
    public void checkIntentPrivacyPolicy() {
        CommonSteps.goToScreen("About");
        Intents.init();//start recording
        AboutSteps.clickPrivacyPolicy();
        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData("https://vhospice.org/#/privacy-policy/")
        ));
        Intents.release(); //Clean recorded
    }

    @Test
    @DisplayName("Создать новость, поле 'Description' оставить пустым")
    public void createNewsDescriptionEmpty() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создать новость, в поле 'Description' ввести пробел")
    public void createNewsDescriptionSpace() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(" ");
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создание и поиск валидной претензии")
    public void createClaim() {
        String titleString = "Некий заголовок " + getCurrentDate() + "We" + getCurrentTime();
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(titleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(newsPublicationDate);
        CreateClaimSteps.enterClaimTime(newsTime);
        CreateClaimSteps.enterClaimDescription(newsDescriptionString);
        CommonSteps.clickSave();
        SystemClock.sleep(1000);
        MainSteps.openAllClaims();
        ClaimsSteps.checkClaim(titleString);
    }

    @Test
    @DisplayName("Измененить статус у претензии c 'Open' на 'Canceled'")
    public void editStatusTheClaimOpenOnTheCanceled() {
        MainSteps.isMainScreen();
        MainSteps.openAllClaims();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxCancelled(false);
        ClaimsSteps.checkCheckboxExecuted(false);
        ClaimsSteps.checkCheckboxInProgress(false);
        ClaimsSteps.checkCheckboxOpen(true);
        ClaimsSteps.clickOK();
        SystemClock.sleep(2000);
        ClaimsSteps.openClaim(0);
        ClaimsSteps.checkStatusClaim("Open");
        ClaimsSteps.clickButtonEditStatusClaim();
        ClaimsSteps.checkStatus("take to work");
        ClaimsSteps.checkStatus("Cancel");
        ClaimsSteps.clickStatus("Cancel");
        SystemClock.sleep(2000);
        ClaimsSteps.checkStatusClaim("Canceled");
    }

    @Test//bug 1 +
    @DisplayName("Создать новость, в поле 'Publication date' ввести символы")
    public void createNewsPublicationDateSymbols() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate("%%$%#%$%");
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 2 +
    @DisplayName("Создать новость, в поле 'Publication date' ввести несуществующую дату")
    public void createNewsPublicationDateNonExistent() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate("00.00.0000");
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 3 +
    @DisplayName("Создать новость, в поле 'Publication date' ввести 'далеко-будущую' дату")
    public void createNewsPublicationDateFuture() {
        ViewInteraction emptyToast = onView(withText(R.string.wrong_news_date_period)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate("01.01.3000");
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 4 +
    @DisplayName("Добавить комментарий с использованием символов к претензии")
    public void addSymbolsCommentTheClaim() {
        String text = "!??№!%№%!%!%:,(;.,:%(;.,:%:,№№%:,. " + getCurrentTime();
        ViewInteraction emptyToast = onView(withText(R.string.toast_empty_field)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.openAllClaims();
        ClaimsSteps.searchClaim(0);
        SystemClock.sleep(200);
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(text);
        AddNewCommentStep.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 5 +
    @DisplayName("Создание претензии с прошедшей датой")
    public void createClaimPastDate() {
        ViewInteraction emptyToast = onView(withText(R.string.wrong_news_date_period)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate("01.01.2000");
        CreateClaimSteps.enterClaimTime(newsTime);
        CreateClaimSteps.enterClaimDescription(newsDescriptionString);
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 6 +
    @DisplayName("Создание претензии с 'далеко-будущей датой'")
    public void createClaimFutureDate() {
        ViewInteraction emptyToast = onView(withText(R.string.wrong_news_date_period)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate("01.01.3000");
        CreateClaimSteps.enterClaimTime(newsTime);
        CreateClaimSteps.enterClaimDescription(newsDescriptionString);
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 7 +
    @DisplayName("Создать новость, в поле 'Publication date' ввести 'прошлую' дату")
    public void createNewsPublicationDatePast() {
        ViewInteraction emptyToast = onView(withText(R.string.wrong_news_date_period)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate("01.01.2000");
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 8 +
    @DisplayName("Создать новость, в поле 'Time' ввести символы")
    public void createNewsTimeSymbols() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime("%&$%&*%$");
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 9 +
    @DisplayName("Создать новость, в поле 'Description' ввести символы")
    public void createNewsDescriptionSymbols() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription("$%#%%##%&&%#&");
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 10 +
    @DisplayName("Создание претензии, в описание ввести символы")
    public void createClaimSymbolsDescription() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime(getCurrentTime());
        CreateClaimSteps.enterClaimDescription("№%:№%:№%:№%");
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 11 +
    @DisplayName("Создание претензии с несуществующей датой")
    public void createClaimNonExistentDate() {
        ViewInteraction emptyToast = onView(withText(R.string.wrong_news_date_period)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate("00.00.0000");
        CreateClaimSteps.enterClaimTime(newsTime);
        CreateClaimSteps.enterClaimDescription(newsDescriptionString);
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 12 +
    @DisplayName("Создание претензии с несуществующим временем")
    public void createClaimNonExistentTime() {
        ViewInteraction emptyToast = onView(withText(R.string.empty_fields)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime("1111111111");
        CreateClaimSteps.enterClaimDescription(newsDescriptionString);
        CommonSteps.clickSave();
        emptyToast.check(matches(isDisplayed()));
    }

    @Test//bug 13 +
    @DisplayName("Редактирование комментария к претензии")
    public void editCommentTheClaim() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(newCommentClaim);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate("01.01.2013");
        CreateClaimSteps.enterClaimTime("01:00");
        CreateClaimSteps.enterClaimDescription(newsDescriptionString);
        CommonSteps.clickSave();
        SystemClock.sleep(500);
        MainSteps.openAllClaims();
        ClaimsSteps.checkClaim(newCommentClaim);
        ClaimsSteps.openClaim(1);
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(comment);
        AddNewCommentStep.clickSave();
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(newCommentClaim);
        AddNewCommentStep.clickSave();
        String textOldComment = EditClaimSteps.getTextComment();
        EditClaimSteps.checkTextDescription(textOldComment, true);
        ClaimsSteps.checkButtonClose();
        EditClaimSteps.buttonEditComment(0);
        AddNewCommentStep.enterComment("Mea1000t");
        AddNewCommentStep.clickSave();
        ClaimsSteps.checkButtonClose();
        EditClaimSteps.checkTextDescription(textOldComment, false);
    }

    @Test
    @DisplayName("Сортировка новостей на экране новостей")
    public void newsScreenSorting() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        String firstNews = NewsSteps.getFirstNewsTitle();
        NewsSteps.clickSortButton();
        String lastNews = NewsSteps.getLastNewsTitle();
        NewsSteps.clickSortButton();
        String firstNewsAgain = NewsSteps.getFirstNewsAgainTitle();
        assertEquals(firstNews, firstNewsAgain);
        assertNotEquals(firstNews, lastNews);
    }

    @Test
    @DisplayName("Редактирование и удаление новости")
    public void newsEditingDeleting() {
        String titleString = "Некий заголовок должен " + getCurrentDate() + "They" + getCurrentTime();
        String descriptionString = "Проба тут " + getCurrentTime();
        String newTitle = "Чудо чудесное из чудес " + getCurrentDate() + "We" + getCurrentTime();
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(titleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(descriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        ControlPanelSteps.isControlPanel();
        if (isDisplayedWithSwipe(onView(withText(titleString)), 1, true)) {
            onView(withText(titleString)).check(matches(isDisplayed())).perform(click());
        }
        ControlPanelSteps.checkNewsDescription(true, descriptionString);
        ControlPanelSteps.clickNewsTitle(titleString);
        SystemClock.sleep(1500);
        ControlPanelSteps.checkNewsDescription(false, descriptionString);
        ControlPanelSteps.clickEditThisNews(titleString);
        CreateNewsSteps.isEditNewsScreen();
        CreateNewsSteps.checkNewsTitle(titleString);
        CreateNewsSteps.enterNewsTitle(newTitle);
        CommonSteps.clickSave();
        ControlPanelSteps.isControlPanel();
        if (isDisplayedWithSwipe(onView(withText(newTitle)), 1, true)) {
            onView(withText(newTitle)).check(matches(isDisplayed()));
        }
        ControlPanelSteps.clickDeleteThisNews(newTitle);
        CommonSteps.clickOK();
    }
}


