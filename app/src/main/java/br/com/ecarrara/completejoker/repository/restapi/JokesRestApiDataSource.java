package br.com.ecarrara.completejoker.repository.restapi;

import java.io.IOException;

import javax.inject.Inject;

import br.com.ecarrara.completejoker.repository.Joke;
import retrofit2.Response;
import timber.log.Timber;

public class JokesRestApiDataSource {

    private JokerRestApi jokerRestApi;

    @Inject
    public JokesRestApiDataSource(JokerRestApi jokerRestApi) {
        this.jokerRestApi = jokerRestApi;
    }

    public Joke tellJoke() {
        Joke joke = null;

        try {
            Response<Joke> response = jokerRestApi.tellJoke().execute();
            joke = response.body();
        } catch (IOException e) {
            Timber.e(e);
        }

        return joke;
    }

}
