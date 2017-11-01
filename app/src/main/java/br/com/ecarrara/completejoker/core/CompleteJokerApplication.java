package br.com.ecarrara.completejoker.core;

import android.app.Application;

import br.com.ecarrara.completejoker.BuildConfig;
import br.com.ecarrara.completejoker.di.Injector;
import timber.log.Timber;

public class CompleteJokerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeLogging();
        Injector.initialize(this);
    }

    private void initializeLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
