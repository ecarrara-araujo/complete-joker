package br.com.ecarrara.completejoker.di;

import br.com.ecarrara.completejoker.JokeViewerRouterWithNoAds;
import br.com.ecarrara.completejoker.repository.JokeViewerRouter;
import dagger.Module;
import dagger.Provides;

@Module
public class JokesModule {

    @Provides
    public JokeViewerRouter providesJokesViewerRouter() {
        return new JokeViewerRouterWithNoAds();
    }

}
