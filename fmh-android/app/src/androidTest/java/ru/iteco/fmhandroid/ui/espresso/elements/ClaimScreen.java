package ru.iteco.fmhandroid.ui.espresso.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.childAtPosition;
import static ru.iteco.fmhandroid.ui.espresso.utils.Utils.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimScreen {
    public ViewInteraction buttonClose = onView(withId(R.id.close_image_button));
    public ViewInteraction buttonFiltering = onView((withId(R.id.filters_material_button)));
    public ViewInteraction titleFiltering = onView((withId(R.id.claim_filter_dialog_title)));
    public ViewInteraction inProgress = onView(withId(R.id.item_filter_in_progress));
    public ViewInteraction buttonCancel = onView((withId(R.id.claim_filter_cancel_material_button)));
    public ViewInteraction buttonOk = onView(withId(R.id.claim_list_filter_ok_material_button));
    public ViewInteraction claims = onView(withText("Claims"));
    public ViewInteraction open = onView(withId(R.id.item_filter_open));
    public ViewInteraction executed = onView(withId(R.id.item_filter_executed));
    public ViewInteraction cancelled = onView(withId(R.id.item_filter_cancelled));
    public ViewInteraction addNewClaimButton = onView(withId(R.id.add_new_claim_material_button));
    public ViewInteraction description = onView(allOf(withId(R.id.claim_list_recycler_view), childAtPosition(withId(R.id.all_claims_cards_block_constraint_layout), 4)));
    public ViewInteraction buttonAddCommentClaim = onView(withId(R.id.add_comment_image_button));
    public ViewInteraction buttonEditClaim = onView(withId(R.id.edit_processing_image_button));
    public ViewInteraction textTitleClaim = onView(withId(R.id.title_text_view));
    public ViewInteraction buttonStatusClaim = onView(withId(R.id.status_processing_image_button));

    public ViewInteraction buttonText(String text) {
        return onView(withText(text));
    }

    public ViewInteraction claimList(int index) {
        return onView(withIndex(withId(R.id.claim_list_card),
                index));
    }

    public ViewInteraction statusClaim(String status) {
        return onView(allOf(withId(R.id.status_label_text_view), withText(status)));
    }

    public ViewInteraction checkTextStatusClaim(String status) {
        return onView(allOf(withId(android.R.id.title), withText(status)));
    }

    public ViewInteraction addTextCommentStatus = onView(withId(R.id.editText));

}
