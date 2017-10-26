package br.com.ecarrara.completejoker.di;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import br.com.ecarrara.completejoker.core.CompleteJokerApplication;
import dagger.Module;
import dagger.Provides;

@Singleton
@Module
class ApplicationModule {

    private CompleteJokerApplication applicationContext;

    public ApplicationModule(@NonNull CompleteJokerApplication completeJokerApplication) {
        this.applicationContext = completeJokerApplication;
    }

    @Provides
    @Singleton
    public Context providesApplicationContext() {
        return this.applicationContext;
    }

}
