package br.com.ecarrara.completejoker.functional;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import br.com.ecarrara.completejoker.MainActivity;
import br.com.ecarrara.completejoker.core.InstrumentationTestIdlingResourceProvider;
import br.com.ecarrara.completejoker.robot.JokeRequestWithAdsRobot;
import br.com.ecarrara.completejoker.rule.IdlingResourceRule;

@RunWith(AndroidJUnit4.class)
public class RequestJokesWithAdsUseCase {

    @Rule
    public RuleChain chain = RuleChain
            .outerRule(new IdlingResourceRule(new InstrumentationTestIdlingResourceProvider()))
            .around(new ActivityTestRule<>(MainActivity.class));

    @Test
    public void requestJokeWithAds() throws UiObjectNotFoundException {
        JokeRequestWithAdsRobot.getRobot()
                .checkBannerAd()
                .requestJoke()
                .checkInterstitialAd()
                .closeInterstitialAd()
                .checkJoke();
    }

}
