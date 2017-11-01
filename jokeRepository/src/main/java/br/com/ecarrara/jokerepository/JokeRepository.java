package br.com.ecarrara.jokerepository;

public class JokeRepository {

    public String getRandomJoke() {
        return JokesDataSource.getRandomJoke();
    }

}
