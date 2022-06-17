package com.lermala.lookconstructor.mainapp.presentation.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.lermala.lookconstructor.R;

public class LoginActivityTest {
    // https://habr.com/ru/company/otus/blog/472372/
    @Rule
    public ActivityTestRule<LoginActivity> activityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void onCreate() {
        System.out.println("created");
    }

    @Test
    @Ignore
    public void clickButtonBack(){
        onView(withId(R.id.btn_back)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void clickButtonPortfolio(){
        onView(withId(R.id.portfolioFragment)).perform(click()).check(matches(isDisplayed()));
    }
}