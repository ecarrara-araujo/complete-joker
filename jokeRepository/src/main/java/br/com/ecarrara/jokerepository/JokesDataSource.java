package br.com.ecarrara.jokerepository;

import java.util.Random;

/**
 * Jokes from: http://www.short-funny.com/
 * Sorry I am not that funny... :P
 */
public final class JokesDataSource {

    private static final String[] jokes = {
        "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.",
        "Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                "Doctor: \"Nine.\"",
        "Anton, do you think I’m a bad mother?\n" +
                "My name is Paul.\n",
        "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.",
        "What is the difference between a snowman and a snowwoman?\n" +
                "Snowballs.",
        "Knock knock.\n" +
                "Who’s there?\n" +
                "The door.",
        "Why did the physics teacher break up with the biology teacher? There was no chemistry."
    };

    public static String getRandomJoke() {
        final int nextJokeIndex = (new Random()).nextInt(jokes.length);
        return jokes[nextJokeIndex];
    }

}
