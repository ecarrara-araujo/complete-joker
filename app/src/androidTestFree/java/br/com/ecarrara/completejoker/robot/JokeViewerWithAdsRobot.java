package br.com.ecarrara.completejoker.robot;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.webkit.WebView;
import android.widget.ImageButton;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class JokeViewerWithAdsRobot extends JokeViewerRobot {

    public static JokeViewerWithAdsRobot getRobot() {
        return new JokeViewerWithAdsRobot();
    }

    public JokeViewerWithAdsRobot checkInterstitialAd() {
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        UiObject interstitialAdWebView = uiDevice.findObject(
                (new UiSelector()).className(WebView.class)
        );
        assertThat(interstitialAdWebView.exists(), is(true));
        return this;
    }

    public JokeViewerWithAdsRobot closeInterstitialAd() throws UiObjectNotFoundException {
        onView(
                allOf(
                        withClassName(equalTo(ImageButton.class.getName())),
                        withContentDescription("Interstitial close button")
                )
        ).perform(click());
        return this;
    }

}
