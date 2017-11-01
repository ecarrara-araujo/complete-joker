package br.com.ecarrara.completejoker.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.ecarrara.completejoker.BuildConfig;
import br.com.ecarrara.completejoker.repository.restapi.JokerRestApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
class NetworkingModule {

    private final String restApiBaseUrl;

    public NetworkingModule(String restApiBaseUrl) {
        this.restApiBaseUrl = restApiBaseUrl;
    }

    @Provides
    @Singleton
    public JokerRestApi providesJokerRestApi(Retrofit retrofitClient) {
        return retrofitClient.create(JokerRestApi.class);
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofitClient(OkHttpClient okHttpClient,
                                           Converter.Factory gsonConverterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .baseUrl(restApiBaseUrl)
                .build();
    }

    @Provides
    @Singleton
    public Converter.Factory providesGsonConverterFactory() {
        Gson gson = new GsonBuilder().create();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(
            @Named("loggingInterceptor") Interceptor loggingInterceptor
    ) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    @Named("loggingInterceptor")
    public Interceptor providesLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return logging;
    }

}
