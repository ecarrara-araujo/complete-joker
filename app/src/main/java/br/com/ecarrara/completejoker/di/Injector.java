package br.com.ecarrara.completejoker.di;

import br.com.ecarrara.completejoker.core.CompleteJokerApplication;
import br.com.ecarrara.completejoker.repository.restapi.ApiConstants;

public final class Injector {

    private Injector() { /* must not be constructed */ }

    private static ApplicationComponent applicationComponent;

    public static void initialize(CompleteJokerApplication application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .networkingModule(new NetworkingModule(ApiConstants.SERVICE_BASE_URL))
                .build();
    }

    public static ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

}
