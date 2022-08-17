package ru.iteco.fmhandroid.ui.espresso.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.withIndex;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class EditClaimScreen {
    public ViewInteraction claimStatus = onView(allOf(withId(R.id.status_label_text_view), withText("In progress"), withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.cardview.widget.CardView.class)))));
    public ViewInteraction backButton = onView(withId(R.id.close_image_button));
    public ViewInteraction textDescription = onView(withIndex(withId(R.id.comment_description_text_view), 0));

    public ViewInteraction appButton(int index) {
        return onView(allOf(withIndex(withId(R.id.edit_comment_image_button), index),
                withContentDescription("button edit comment")));
    }
}
