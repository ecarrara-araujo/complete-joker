package br.com.ecarrara.completejoker.repository;

public class Joke {

    private String jokeDescription;

    public Joke(String jokeDescription) {
        this.jokeDescription = jokeDescription;
    }

    public String getJokeDescription() {
        return jokeDescription;
    }

    public void setJokeDescription(String jokeDescription) {
        this.jokeDescription = jokeDescription;
    }

}
