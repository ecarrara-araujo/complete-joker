package br.com.ecarrara.completejoker.functional;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import br.com.ecarrara.completejoker.MainActivity;
import br.com.ecarrara.completejoker.core.InstrumentationTestIdlingResourceProvider;
import br.com.ecarrara.completejoker.robot.JokeRequestRobot;
import br.com.ecarrara.completejoker.rule.IdlingResourceRule;

@RunWith(AndroidJUnit4.class)
public class RequestJokeNoAdsUseCase {

    @Rule
    public RuleChain chain = RuleChain
            .outerRule(new IdlingResourceRule(new InstrumentationTestIdlingResourceProvider()))
            .around(new ActivityTestRule<>(MainActivity.class));

    @Test
    public void requestJoke() {
        JokeRequestRobot.getRobot()
                .requestJoke()
                .checkJoke();

    }

}
