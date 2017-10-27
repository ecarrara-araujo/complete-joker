package br.com.ecarrara.completejoker.robot;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import br.com.ecarrara.completejoker.R;

import static android.support.test.espresso.Espresso.onView;

public class JokeRequestRobot {

    public static JokeRequestRobot getRobot() {
        return new JokeRequestRobot();
    }

    public JokeViewerRobot requestJoke() {
        onView(ViewMatchers.withId(R.id.joke_request_button))
                .perform(ViewActions.click());
        return JokeViewerRobot.getRobot();
    }

}
