package ru.iteco.fmhandroid.ui.espresso.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class SplashScreen {
    public ViewInteraction progressBarSplashPage = onView(withId(R.id.splash_screen_circular_progress_indicator));
    public ViewInteraction textSplashPage = onView(withId(R.id.splashscreen_text_view));
    public ViewInteraction imageSplashPage = onView(withId(R.id.splashscreen_image_view));
}
