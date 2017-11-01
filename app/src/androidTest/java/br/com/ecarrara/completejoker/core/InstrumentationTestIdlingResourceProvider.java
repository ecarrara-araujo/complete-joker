package br.com.ecarrara.completejoker.core;

import android.support.test.espresso.idling.CountingIdlingResource;

public class InstrumentationTestIdlingResourceProvider extends IdlingResourceProvider {

    private static CountingIdlingResource countingIdlingResource;

    @Override
    public CountingIdlingResource getIdlingResource() {
        if (countingIdlingResource == null) {
            countingIdlingResource = new CountingIdlingResource("JokeCountingIdlingResource", true);
        }
        return countingIdlingResource;
    }

}
