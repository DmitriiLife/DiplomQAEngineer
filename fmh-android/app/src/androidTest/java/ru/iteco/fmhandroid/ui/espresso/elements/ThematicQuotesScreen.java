package ru.iteco.fmhandroid.ui.espresso.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.childAtPosition;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ThematicQuotesScreen {
    public ViewInteraction missionList = onView(allOf(withId(R.id.our_mission_item_list_recycler_view), childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")), 0)));
    public ViewInteraction title = onView(withId(R.id.our_mission_title_text_view));
    public ViewInteraction icon = onView(withIndex(withId(R.id.our_mission_item_image_view), 0));
    public ViewInteraction thematicTitle = onView(withIndex(withId(R.id.our_mission_item_title_text_view), 0));
}
