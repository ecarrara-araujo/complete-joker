package br.com.ecarrara.completejoker.repository;

import javax.inject.Inject;

import br.com.ecarrara.completejoker.repository.restapi.JokesRestApiDataSource;

public class JokeRepository {

    private JokesRestApiDataSource jokesRestApiDataSource;

    @Inject
    public JokeRepository(JokesRestApiDataSource jokesRestApiDataSource) {
        this.jokesRestApiDataSource = jokesRestApiDataSource;
    }

    public Joke getJoke() {
        return this.jokesRestApiDataSource.tellJoke();
    }

}
