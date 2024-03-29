package ru.iteco.fmhandroid.ui.espresso.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ControlPanelScreen {
    public ViewInteraction firstPublicationDate = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction firstCreationDate = onView(withIndex(withId(R.id.news_item_create_date_text_view), 0));
    public ViewInteraction firstPublicationDateAgain = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction firstCreationDateAgain = onView(withIndex(withId(R.id.news_item_create_date_text_view), 0));
    public ViewInteraction lastPublicationDate = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction buttonCreateNews = onView(withId(R.id.add_news_image_view));
    public ViewInteraction buttonEditNews = onView(withIndex(withId(R.id.edit_news_item_image_view), 0));
    public ViewInteraction newsStatus = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public ViewInteraction newsStatusActive = onView(withIndex(withId(R.id.news_item_published_text_view), 0));
    public ViewInteraction buttonDeleteNews = onView(withIndex(withId(R.id.delete_news_item_image_view), 0));
    public ViewInteraction firstPublicationDateNotActive = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction firstPublicationDateActive = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));
    public ViewInteraction buttonEditNewsNotActive = onView(withIndex(withId(R.id.edit_news_item_image_view), 0));

    public ViewInteraction newsDescription(String text) {
        return onView(withText(text));
    }

    public ViewInteraction newsDelete(String text) {
        return onView(allOf(withId(R.id.delete_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(text))))))));
    }

    public ViewInteraction newsEdit(String text) {
        return onView(allOf(withId(R.id.edit_news_item_image_view), withParent(withParent(allOf(withId(R.id.news_item_material_card_view), withChild(withChild(withText(text))))))));
    }

    public ViewInteraction newsTitle(String text) {
        return onView(allOf(withId(R.id.news_item_title_text_view), withText(text)));
    }

    public ViewInteraction newsPublicationDate(String date) {
        return onView(allOf(withId(R.id.news_item_publication_date_text_view), withText(date)));
    }

}
