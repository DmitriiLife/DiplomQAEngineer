package ru.iteco.fmhandroid.ui.espresso.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AddNewCommentScreen {
    public ViewInteraction comment = onView(allOf(withHint("Comment"), withParent(withParent(withId(R.id.comment_text_input_layout)))));
    public ViewInteraction buttonSave = onView(withId(R.id.save_button));
    public ViewInteraction buttonCancel = onView(withId(R.id.cancel_button));
}
