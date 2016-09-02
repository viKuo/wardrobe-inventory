package com.vivienlk.wardrobeinventory;

import android.support.test.rule.ActivityTestRule;

import com.vivienlk.wardrobeinventory.UI.ListItemsActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Vivien on 9/2/2016.
 */
public class ListItemsActivityTest {
    @Rule
    public ActivityTestRule<ListItemsActivity> activityTestRule =
            new ActivityTestRule<>(ListItemsActivity.class);

    @Test
    public void validateListView() {
        onData(withText("AddItemActivity")).check(matches(isDisplayed()));
    }
}
