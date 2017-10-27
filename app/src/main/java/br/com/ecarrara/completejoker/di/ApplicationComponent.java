package br.com.ecarrara.completejoker.di;

import javax.inject.Singleton;

import br.com.ecarrara.completejoker.MainActivity;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkingModule.class,
        JokesModule.class
})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

}
