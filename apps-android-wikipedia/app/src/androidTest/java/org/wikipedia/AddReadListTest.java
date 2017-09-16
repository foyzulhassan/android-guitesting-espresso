package org.wikipedia;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddReadListTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addReadListTest() {

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.view_list_card_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        SleepUtil.sleep(3);
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.view_news_fullscreen_link_card_list), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        SleepUtil.sleep(12);
        ViewInteraction appCompatTextView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatTextView")), isDisplayed()));
        appCompatTextView.perform(click());

/*        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.onboarding_button), withText("Got it"),
                        withParent(withId(R.id.onboarding_container)),
                        isDisplayed()));
        appCompatButton.perform(click());*/

        SleepUtil.sleep(3);
        ViewInteraction plainPasteEditText = onView(
                allOf(withId(R.id.reading_list_title), withText("My reading list")));
        plainPasteEditText.perform(scrollTo(), replaceText("People"), closeSoftKeyboard());

        SleepUtil.sleep(3);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        SleepUtil.sleep(3);
        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.page_toolbar)),
                        isDisplayed()));
        imageButton.perform(click());

        SleepUtil.sleep(3);
        pressBack();

        SleepUtil.sleep(3);
        ViewInteraction appCompatTextView2 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatTextView")), isDisplayed()));
        appCompatTextView2.perform(click());

        SleepUtil.sleep(3);
        ViewInteraction textView = onView(
                allOf(withId(R.id.item_title), withText("People"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_container),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("People")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.item_count), withText("1 article"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_container),
                                        1),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("1 article")));

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.item_container),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        SleepUtil.sleep(3);
        ViewInteraction appCompatTextView3 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatTextView")), isDisplayed()));
        appCompatTextView3.perform(click());

        SleepUtil.sleep(3);
        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.item_container), isDisplayed()));
        linearLayout2.perform(click());

        SleepUtil.sleep(3);
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.button_edit), withContentDescription("Edit reading list details"), isDisplayed()));
        floatingActionButton.perform(click());

        SleepUtil.sleep(3);
        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.reading_list_delete_link), withText("Delete reading list")));
        appCompatTextView4.perform(scrollTo(), click());

        SleepUtil.sleep(3);
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        SleepUtil.sleep(3);
        pressBack();

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
