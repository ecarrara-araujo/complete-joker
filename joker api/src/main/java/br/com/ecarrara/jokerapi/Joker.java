package br.com.ecarrara.jokerapi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import br.com.ecarrara.jokerepository.JokeRepository;

@Api(
        name = "joker",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "completejoker.ecarrara.com.br",
                ownerName = "completejoker.ecarrara.com.br",
                packagePath=""
        )
)
public class Joker {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tellJoke"
    )
    public Joke tellJoke() {
        JokeRepository repository = new JokeRepository();
        Joke responseJoke = new Joke();
        responseJoke.setJokeDescription(repository.getRandomJoke());
        return responseJoke;
    }
}
