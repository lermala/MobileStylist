package com.lermala.lookconstructor.mainapp.presentation.ui;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;

import androidx.test.rule.ActivityTestRule;

import com.lermala.lookconstructor.R;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickPortfolio(){
        onView(withId(R.id.portfolioFragment)).perform(click());
        // onView(withId(R.id.lv_portfolio))
        onData(anything()).inAdapterView(withId(R.id.lv_portfolio)).atPosition(0).perform(click());
    }

    @Test
    public void clickProfile(){
        onView(withId(R.id.profileFragment)).perform(click());
    }
}