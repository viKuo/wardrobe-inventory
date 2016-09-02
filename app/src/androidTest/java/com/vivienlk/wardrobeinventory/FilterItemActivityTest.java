package com.vivienlk.wardrobeinventory;

import android.support.test.rule.ActivityTestRule;

import com.vivienlk.wardrobeinventory.UI.FilterItemsActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Vivien on 9/1/2016.
 */
public class FilterItemActivityTest {
    @Rule
    public ActivityTestRule<FilterItemsActivity> activityTestRule =
            new ActivityTestRule<>(FilterItemsActivity.class);

    @Test
    public void validateAddItemButton() {
        onView(withText("AddItemActivity")).check(matches(isDisplayed()));
    }
}
