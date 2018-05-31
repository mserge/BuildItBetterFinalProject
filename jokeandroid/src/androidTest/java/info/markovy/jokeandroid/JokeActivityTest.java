package info.markovy.jokeandroid;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
@SmallTest
@RunWith(AndroidJUnit4.class)
public class JokeActivityTest {

    @Rule
    public ActivityTestRule<JokeActivity> mActivityRule =
            new ActivityTestRule<>(JokeActivity.class, true, false);

    @Test
    public void activityShowsTextFromIntent() {
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, JokeActivity.class);
        String value = "Value";
        intent.putExtra(JokeActivity.PARAM_JOKE, value);

        mActivityRule.launchActivity(intent);

        /* Your activity is initialized and ready to go. */
        onView(withId(R.id.textViewJoke))
                .check(matches(withText(value)))
                .check(matches(isDisplayed()));

    }

}