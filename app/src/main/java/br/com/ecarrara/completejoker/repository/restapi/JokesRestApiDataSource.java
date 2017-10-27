package br.com.ecarrara.completejoker.repository.restapi;

import android.support.test.espresso.idling.CountingIdlingResource;

import java.io.IOException;

import javax.inject.Inject;

import br.com.ecarrara.completejoker.core.IdlingResourceProvider;
import br.com.ecarrara.completejoker.repository.Joke;
import retrofit2.Response;
import timber.log.Timber;

public class JokesRestApiDataSource {

    private JokerRestApi jokerRestApi;
    private CountingIdlingResource idlingResource;

    @Inject
    public JokesRestApiDataSource(JokerRestApi jokerRestApi) {
        this.jokerRestApi = jokerRestApi;
        prepareIdlingResource();
    }

    private void prepareIdlingResource() {
        IdlingResourceProvider idlingResourceProvider = IdlingResourceProvider.getInstance();
        if (idlingResourceProvider != null) {
            idlingResource = idlingResourceProvider.getIdlingResource();
        }
    }

    public Joke tellJoke() {
        Joke joke = null;

        try {
            if (idlingResource != null) {
                idlingResource.increment();
            }
            Response<Joke> response = jokerRestApi.tellJoke().execute();
            joke = response.body();
        } catch (IOException e) {
            Timber.e(e);
        } finally {
            if (idlingResource != null) {
                idlingResource.decrement();
            }
        }

        return joke;
    }

}
