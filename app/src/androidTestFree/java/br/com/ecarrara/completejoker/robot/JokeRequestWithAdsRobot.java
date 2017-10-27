package br.com.ecarrara.completejoker.robot;

import br.com.ecarrara.completejoker.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class JokeRequestWithAdsRobot extends JokeRequestRobot {

    public static JokeRequestWithAdsRobot getRobot() {
        return new JokeRequestWithAdsRobot();
    }

    public JokeRequestWithAdsRobot checkBannerAd() {
        onView(withId(R.id.adView)).check(matches(isDisplayed()));
        return this;
    }

    public JokeViewerWithAdsRobot requestJoke() {
        super.requestJoke();
        return JokeViewerWithAdsRobot.getRobot();
    }

}
