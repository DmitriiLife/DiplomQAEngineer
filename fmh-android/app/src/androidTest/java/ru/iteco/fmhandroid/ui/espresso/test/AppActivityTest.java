package ru.iteco.fmhandroid.ui.espresso.test;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.checkClaimStatus;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.getCurrentDate;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.getCurrentTime;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.pressBack;

import android.content.Intent;
import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
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
import ru.iteco.fmhandroid.ui.espresso.resources.Resources;
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
import ru.iteco.fmhandroid.ui.espresso.steps.PopupWarningStep;
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
    PopupWarningStep PopupWarningStep = new PopupWarningStep();
    Resources Resources = new Resources();

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
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(" ");
        CreateClaimSteps.enterClaimTime(Resources.newsTime);
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyMessage(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("Создание претензии с пустыми данными")
    public void createClaimEmptyData() {
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
        PopupWarningStep.checkEmptyMessage(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("Создание претензии с пустым временем")
    public void createClaimSpaceTime() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime(" ");
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyMessage(R.string.empty_fields, true);
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
    }

    @Test
    @DisplayName("Сворачивание блока Claims")
    public void closeBlockClaims() {
        MainSteps.isMainScreen();
        MainSteps.allClaimsDisplayed();
        MainSteps.expandAllClaims();
        MainSteps.allClaimsNotDisplayed();
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

    @Test//забрать клаймс
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
        NewsSteps.checkSorting();
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
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        ControlPanelSteps.deleteNews(Resources.newsTitleString);
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
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        ControlPanelSteps.isControlPanel();
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(Resources.newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(Resources.newsPublicationDate);
        NewsFilterSteps.clickFilter();
        NewsSteps.checkFirstNewsDate(Resources.newsPublicationDate);
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(Resources.newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(Resources.newsPublicationDate);
        NewsFilterSteps.clickFilter();
        ControlPanelSteps.checkFirstPublicationDate(Resources.newsPublicationDate);
        ControlPanelSteps.clickEditNews();
        CreateNewsSteps.clickNewsSwitcher();
        CommonSteps.clickSave();
        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(Resources.newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(Resources.newsPublicationDate);
        NewsFilterSteps.clickCheckboxActive();
        NewsFilterSteps.checkCheckboxActive(false);
        NewsFilterSteps.checkCheckboxNotActive(true);
        NewsFilterSteps.clickFilter();
        ControlPanelSteps.checkFirstPublicationDateNotActive(Resources.newsPublicationDate);
        ControlPanelSteps.checkNewsStatus();
        ControlPanelSteps.checkNewsStatusNotActive();
        CreateNewsSteps.clickNewsSwitcher();
        CommonSteps.clickSave();
        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(Resources.newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(Resources.newsPublicationDate);
        NewsFilterSteps.checkCheckboxActive(true);
        NewsFilterSteps.clickCheckboxNotActive();
        NewsFilterSteps.checkCheckboxNotActive(false);
        NewsFilterSteps.clickFilter();
        ControlPanelSteps.checkFirstPublicationDateActive(Resources.newsPublicationDate);
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
        AddNewCommentStep.enterComment(Resources.newCommentClaim);
        AddNewCommentStep.clickCancel();
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(Resources.newCommentClaim);
        AddNewCommentStep.clickSave();
        ClaimsSteps.checkButtonClose();
        ClaimsSteps.checkCommentOnClaim(Resources.newCommentClaim);
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
        ClaimsSteps.openClaimIndex(0);
        SystemClock.sleep(500);
        ClaimsSteps.checkStatusClaim("Open");
        ClaimsSteps.clickEditClaim();
        CreateClaimSteps.enterClaimTitle(Resources.textNewClaim);
        CommonSteps.clickSave();
        ClaimsSteps.checkModifiedClaim();
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
        ClaimsSteps.openClaimIndex(0);
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
        ClaimsSteps.openClaimIndex(0);
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
        ClaimsSteps.openClaimIndex(0);
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
        MainSteps.openAllClaims();
        ClaimsSteps.searchClaim(0);
        SystemClock.sleep(200);
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(" ");
        AddNewCommentStep.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.toast_empty_field, true);
    }

    @Test
    @DisplayName("Создание претензии с пустым описанием")
    public void createClaimSpaceDescription() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime(getCurrentTime());
        CreateClaimSteps.enterClaimDescription(" ");
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyMessage(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("Создание претензии, нажать кнопку отмена")
    public void createClaimClickButtonCancel() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime(getCurrentTime());
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickCancel();
        PopupWarningStep.checkEmptyMessage(R.string.cancellation, true);
    }

    @Test
    @DisplayName("Открыть News, открыть экран управления новостями и назад к экрану новостей")
    public void openNewsOpenControlPanelAndBackNewsScreen() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        pressBack();
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
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        ControlPanelSteps.openDescriptionNewsDelete(Resources.newsTitleString);
    }

    @Test
    @DisplayName("Создать пустую форму новости")
    public void createNewsEmptyData() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        SystemClock.sleep(1000);
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("Создать новость, поле 'Category' оставить пустыми")
    public void createNewsCategoryEmpty() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("Создать новость, поле 'Category' заполнить произвольно")
    public void createNewsCategoryArbitrarily() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.enterNewsCategory("Привет");
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.error_saving, true);
    }

    @Test
    @DisplayName("Создать новость, поле 'Category' заполнить символами")
    public void createNewsCategorySymbols() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.enterNewsCategory("$#%#%##");
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.error_saving, true);
    }

    @Test
    @DisplayName("Создать новость, в поле 'Title' ввести пробел")
    public void createNewsTitleSpace() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(" ");
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
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
        CreateNewsSteps.enterNewsTitle(Resources.symbols);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        ControlPanelSteps.deleteNews(Resources.symbols);
    }

    @Test
    @DisplayName("Создать новость, в поле 'Publication date' ввести пробел")
    public void createNewsPublicationDateSpace() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(" ");
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("Создать новость, в поле 'Time' ввести пробел")
    public void createNewsTimeSpace() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(" ");
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
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
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("Создание и поиск валидной претензии")
    public void createClaim() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.titleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(Resources.newsPublicationDate);
        CreateClaimSteps.enterClaimTime(Resources.newsTime);
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickSave();
        SystemClock.sleep(1000);
        MainSteps.openAllClaims();
        ClaimsSteps.checkClaim(Resources.titleString);
    }

    @Test//bug 1
    @DisplayName("Создать новость, в поле 'Publication date' ввести символы")
    public void createNewsPublicationDateSymbols() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate("%%$%#%$%");
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
    }

    @Test//bug 2
    @DisplayName("Создать новость, в поле 'Publication date' ввести несуществующую дату")
    public void createNewsPublicationDateNonExistent() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate("00.00.0000");
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
    }

    @Test//bug 3
    @DisplayName("Создать новость, в поле 'Publication date' ввести 'далеко-будущую' дату")
    public void createNewsPublicationDateFuture() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate("01.01.3000");
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.wrong_news_date_period, true);
    }

    @Test//bug 4
    @DisplayName("Добавить комментарий с использованием символов к претензии")
    public void addSymbolsCommentTheClaim() {
        MainSteps.openAllClaims();
        ClaimsSteps.searchClaim(0);
        SystemClock.sleep(200);
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(Resources.symbolsComment);
        AddNewCommentStep.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.toast_empty_field, true);
    }

    @Test//bug 5
    @DisplayName("Создание претензии с прошедшей датой")
    public void createClaimPastDate() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate("01.01.2000");
        CreateClaimSteps.enterClaimTime(Resources.newsTime);
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.wrong_news_date_period, true);
    }

    @Test//bug 6
    @DisplayName("Создание претензии с 'далеко-будущей датой'")
    public void createClaimFutureDate() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate("01.01.3000");
        CreateClaimSteps.enterClaimTime(Resources.newsTime);
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.wrong_news_date_period, true);
    }

    @Test//bug 7
    @DisplayName("Создать новость, в поле 'Publication date' ввести 'прошлую' дату")
    public void createNewsPublicationDatePast() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate("01.01.2000");
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.wrong_news_date_period, true);
    }

    @Test//bug 8
    @DisplayName("Создать новость, в поле 'Time' ввести символы")
    public void createNewsTimeSymbols() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime("%&$%&*%$");
        CreateNewsSteps.enterNewsDescription(Resources.newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
    }

    @Test//bug 9
    @DisplayName("Создать новость, в поле 'Description' ввести символы")
    public void createNewsDescriptionSymbols() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription("$%#%%##%&&%#&");
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
    }

    @Test//bug 10
    @DisplayName("Создание претензии, в описание ввести символы")
    public void createClaimSymbolsDescription() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime(getCurrentTime());
        CreateClaimSteps.enterClaimDescription("№%:№%:№%:№%");
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyMessage(R.string.empty_fields, true);
    }

    @Test//bug 11
    @DisplayName("Создание претензии с несуществующей датой")
    public void createClaimNonExistentDate() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate("00.00.0000");
        CreateClaimSteps.enterClaimTime(Resources.newsTime);
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.wrong_news_date_period, true);
    }

    @Test//bug 12
    @DisplayName("Создание претензии с несуществующим временем")
    public void createClaimNonExistentTime() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newsTitleString);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(getCurrentDate());
        CreateClaimSteps.enterClaimTime("1111111111");
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyMessage(R.string.empty_fields, true);
    }

    @Test//bug 13
    @DisplayName("Редактирование комментария к претензии")
    public void editCommentTheClaim() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.newCommentClaim);
        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate("01.01.2013");
        CreateClaimSteps.enterClaimTime("01:00");
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickSave();
        SystemClock.sleep(500);
        MainSteps.openAllClaims();
        ClaimsSteps.checkClaim(Resources.newCommentClaim);
        ClaimsSteps.openClaimIndex(1);
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(Resources.comment);
        AddNewCommentStep.clickSave();
        ClaimsSteps.clickAddComment();
        AddNewCommentStep.enterComment(Resources.newCommentClaim);
        AddNewCommentStep.clickSave();
        EditClaimSteps.checkTextDescription(EditClaimSteps.getTextComment(), true);
        ClaimsSteps.checkButtonClose();
        EditClaimSteps.buttonEditComment(0);
        AddNewCommentStep.enterComment("Mea1000t");
        AddNewCommentStep.clickSave();
        ClaimsSteps.checkButtonClose();
        EditClaimSteps.checkTextDescription(EditClaimSteps.getTextComment(), false);
    }

    @Test//50-50
    @DisplayName("Сортировка новостей на экране новостей")
    public void newsScreenSorting() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.checkNewsScreenSorting();

    }

    @Test
    @DisplayName("Редактирование и удаление новости")
    public void newsEditingDeleting() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.titleStringEdit);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(Resources.descriptionString);
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        ControlPanelSteps.searchOpenDescriptionNews(Resources.titleStringEdit);
        ControlPanelSteps.checkNewsDescription(true, Resources.descriptionString);
        ControlPanelSteps.clickNewsTitle(Resources.titleStringEdit);
        SystemClock.sleep(1500);
        ControlPanelSteps.checkNewsDescription(false, Resources.descriptionString);
        ControlPanelSteps.clickEditThisNews(Resources.titleStringEdit);
        CreateNewsSteps.isEditNewsScreen();
        CreateNewsSteps.checkNewsTitle(Resources.titleStringEdit);
        CreateNewsSteps.enterNewsTitle(Resources.newTitle);
        CommonSteps.clickSave();
        ControlPanelSteps.deleteNews(Resources.newTitle);
    }

    @Test//50-50
    @DisplayName("Создать новость, в поле 'Description' ввести пробел")
    public void createNewsDescriptionSpace() {
        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();
        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();
        CreateNewsSteps.enterNewsTitle(Resources.newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(Resources.newsPublicationDate);
        CreateNewsSteps.enterNewsTime(Resources.newsTime);
        CreateNewsSteps.enterNewsDescription(" ");
        CreateNewsSteps.checkNewsSwitcher();
        CommonSteps.clickSave();
        PopupWarningStep.checkEmptyToast(R.string.empty_fields, true);
    }

    @Test
    @DisplayName("Создание и поиск произвольной претензии")
    public void createArbitraryClaim() {
        MainSteps.isMainScreen();
        MainSteps.createClaim();
        SystemClock.sleep(1000);
        CreateClaimSteps.isCreateClaimsScreen();
        CreateClaimSteps.checkClaimTitleLength();
        CreateClaimSteps.enterClaimTitle(Resources.titleString);
        CreateClaimSteps.enterClaimDate("01.02.1996");
        CreateClaimSteps.enterClaimTime(Resources.newsTime);
        CreateClaimSteps.enterClaimDescription(Resources.newsDescriptionString);
        CommonSteps.clickSave();
        SystemClock.sleep(1000);
        MainSteps.openAllClaims();
        ClaimsSteps.checkClaim(Resources.titleString);
    }

    @Test//50-50
    @DisplayName("Измененить статус у претензии c 'Open' на 'Canceled'")
    public void editStatusTheClaimOpenOnTheCanceled() {
        createArbitraryClaim();
        ClaimsSteps.isClaimsScreen();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxCancelled(false);
        ClaimsSteps.checkCheckboxExecuted(false);
        ClaimsSteps.checkCheckboxInProgress(false);
        ClaimsSteps.checkCheckboxOpen(true);
        ClaimsSteps.clickOK();
        SystemClock.sleep(2000);
        ClaimsSteps.openClaimIndex(0);
        ClaimsSteps.checkStatusClaim("Open");
        ClaimsSteps.clickButtonEditStatusClaim();
        ClaimsSteps.checkStatus("take to work");
        ClaimsSteps.checkStatus("Cancel");
        ClaimsSteps.clickStatus("Cancel");
        SystemClock.sleep(2000);
        ClaimsSteps.checkStatusClaim("Canceled");
    }
}


