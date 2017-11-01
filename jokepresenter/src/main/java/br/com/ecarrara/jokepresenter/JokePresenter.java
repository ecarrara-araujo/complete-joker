package br.com.ecarrara.jokepresenter;

import android.support.v7.app.AppCompatActivity;

public class JokePresenter {

    public static void presentJoke(AppCompatActivity parentActivity, String joke) {
        JokePresenterView.navigate(parentActivity, joke);
    }

}
