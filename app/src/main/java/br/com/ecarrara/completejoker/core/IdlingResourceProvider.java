package br.com.ecarrara.completejoker.core;

import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;

public abstract class IdlingResourceProvider {

    private static IdlingResourceProvider idlingResourceProvider = null;

    @VisibleForTesting
    public static void setIdlingResourceProvider(IdlingResourceProvider newIdlingResourceProvider) {
        idlingResourceProvider = newIdlingResourceProvider;
    }

    @VisibleForTesting
    public static void clearIdlingResourceProvider() {
        idlingResourceProvider = null;
    }

    public static IdlingResourceProvider getInstance() {
        return idlingResourceProvider;
    }

    public abstract CountingIdlingResource getIdlingResource();

}
