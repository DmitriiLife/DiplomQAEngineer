package ru.iteco.fmhandroid.ui.espresso.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;

import ru.iteco.fmhandroid.ui.AppActivity;

public class PopupWarningScreen {

    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    public ViewInteraction emptyToast(String text) {
        return onView(withText(text)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));
    }
}

//
//    onView(withText(R.string.toast_text)).
//        inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
//        check(matches(isDisplayed()));
