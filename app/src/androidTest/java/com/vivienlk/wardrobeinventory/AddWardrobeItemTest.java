package com.vivienlk.wardrobeinventory;

import android.support.test.rule.ActivityTestRule;

import com.vivienlk.wardrobeinventory.UI.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/**
 * Created by Vivien on 8/31/2016.
 */
public class AddWardrobeItemTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateAddItemButton() {
        onView(withId(R.id.add_new_item_button)).check(matches(withText("Add New Item")));
    }
}
