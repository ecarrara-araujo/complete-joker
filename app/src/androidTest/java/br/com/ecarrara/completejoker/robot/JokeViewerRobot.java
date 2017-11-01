package br.com.ecarrara.completejoker.robot;

import br.com.ecarrara.completejoker.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class JokeViewerRobot {

    public static JokeViewerRobot getRobot() {
        return new JokeViewerRobot();
    }

    public static final String EMPTY_JOKE = "";

    public JokeViewerRobot checkJoke() {
        onView(withId(R.id.joke_presenter_joke_textView))
                .check(
                        matches(
                                allOf(
                                        not(withText(EMPTY_JOKE)),
                                        not(withText(R.string.not_jokes_message))
                                )
                        )
                );
        return this;
    }

    public JokeRequestRobot returnToRequestANewJoke() {
        pressBack();
        return JokeRequestRobot.getRobot();
    }

}
