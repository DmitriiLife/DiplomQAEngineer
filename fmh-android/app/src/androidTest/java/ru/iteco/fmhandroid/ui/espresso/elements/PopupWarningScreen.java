package ru.iteco.fmhandroid.ui.espresso.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.ui.espresso.utils.Utils;

public class PopupWarningScreen {

    public ViewInteraction emptyToast(int id) {
        return onView(withText(id)).inRoot(new Utils.ToastMatcher());
    }

    public ViewInteraction emptyMessage(int id) {
        return onView(allOf(withId(android.R.id.message), withText(id)));
    }
}
