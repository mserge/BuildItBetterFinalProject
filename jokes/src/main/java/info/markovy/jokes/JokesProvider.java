package info.markovy.jokes;

import java.util.Random;

public class JokesProvider {

    private static final String[] jokes = {"Joke #1", "Joke #2", "Joke #3"};
    private final Random random;

    public JokesProvider(long seed) {
        random = new Random(seed);
    }

    public String getRandomJoke(){
        return jokes[random.nextInt(jokes.length)];
    }
}
