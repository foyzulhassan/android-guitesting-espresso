package org.wikipedia;


import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddReadingListTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addReadingListTest2() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.view_list_card_list), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        SleepUtil.sleep(5);
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.view_news_fullscreen_link_card_list), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));
        SleepUtil.sleep(5);

        ViewInteraction articleTabLayout = onView(
                allOf(withText("b#1"), isDisplayed()));
        articleTabLayout.perform(click());

        SleepUtil.sleep(5);


        try {
            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.onboarding_button), withText("Got it"),
                            withParent(withId(R.id.onboarding_container)),
                            isDisplayed()));
            appCompatButton.perform(click());
            SleepUtil.sleep(5);
        }catch(NoMatchingViewException e) {
            ViewInteraction linearLayout = onView(
                    allOf(withId(R.id.create_button),
                            withParent(withId(R.id.lists_container)),
                            isDisplayed()));
            linearLayout.perform(click());
            SleepUtil.sleep(5);
        }

        ViewInteraction plainPasteEditText = onView(
                allOf(withId(R.id.reading_list_title)));
        plainPasteEditText.perform(scrollTo(), replaceText("People"), closeSoftKeyboard());
        SleepUtil.sleep(5);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton2.perform(click());
        SleepUtil.sleep(5);

        pressBack();
        SleepUtil.sleep(5);

        pressBack();
        SleepUtil.sleep(5);

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("My lists"), isDisplayed()));
        appCompatTextView2.perform(click());
        SleepUtil.sleep(5);

        ViewInteraction textView = onView(
                allOf(withId(R.id.item_title), withText("People"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.item_container),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("People")));
        SleepUtil.sleep(5);
        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.item_container), isDisplayed()));
        linearLayout2.perform(click());
        SleepUtil.sleep(5);
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.button_edit), withContentDescription("Edit reading list details"), isDisplayed()));
        floatingActionButton.perform(click());
        SleepUtil.sleep(5);
        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.reading_list_delete_link), withText("Delete reading list")));
        appCompatTextView3.perform(scrollTo(), click());
        SleepUtil.sleep(5);
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton3.perform(click());
        SleepUtil.sleep(5);
        ViewInteraction appCompatTextView4 = onView(
                allOf(withText("Explore"), isDisplayed()));
        appCompatTextView4.perform(click());

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
