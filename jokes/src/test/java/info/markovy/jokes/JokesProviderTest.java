package info.markovy.jokes;

import static org.junit.Assert.*;

public class JokesProviderTest {

    @org.junit.Test
    public void getRandomJoke() {
        JokesProvider jokesProvider = new JokesProvider(0); // seed 0 will give the #1 always
        assertEquals(jokesProvider.getRandomJoke(), "Joke #1");
    }
}