package br.com.ecarrara.completejoker.repository;

import android.support.v7.app.AppCompatActivity;

public interface JokeViewerRouter {

    void init(AppCompatActivity parent);

    void displayJoke(String joke);

}
