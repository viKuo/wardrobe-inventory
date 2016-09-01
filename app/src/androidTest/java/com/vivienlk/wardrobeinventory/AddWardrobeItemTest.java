package com.vivienlk.wardrobeinventory;

import android.support.test.rule.ActivityTestRule;

import com.vivienlk.wardrobeinventory.UI.AddItemActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Vivien on 8/31/2016.
 */
public class AddWardrobeItemTest {
    @Rule
    public ActivityTestRule<AddItemActivity> activityTestRule =
            new ActivityTestRule<>(AddItemActivity.class);

    @Test
    public void fillItemDataAndSave() {
        onView(withText("AddItemActivity")).check(matches(isDisplayed()));
//        onView(withId(R.id.dateInputTextView)).perform(typeText("8/21/2016"));
//        onView(withId(R.id.colorsInput)).perform(typeText("Blue, White"));
//        onView(withId(R.id.texturesInput)).perform(typeText("Suede"));
//        onView(withId(R.id.priceInput)).perform(typeText("26"));
//        onView(withId(R.id.brandInput)).perform(typeText("Zara"));
//        onView(withId(R.id.saveButton)).perform(click()).check(matches(isEnabled()));
//        onView(withText("Blue, White!")).check(matches(isDisplayed()));
    }
}
