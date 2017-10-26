package br.com.ecarrara.completejoker.repository.restapi;

import br.com.ecarrara.completejoker.repository.Joke;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JokerRestApi {

    @GET("tellJoke")
    Call<Joke> tellJoke();

}
