package br.com.ecarrara.completejoker;

import android.support.v7.app.AppCompatActivity;

import br.com.ecarrara.completejoker.repository.JokeViewerRouter;
import br.com.ecarrara.jokepresenter.JokePresenter;

public class JokeViewerRouterWithNoAds implements JokeViewerRouter {

    private AppCompatActivity parentActivity;

    @Override
    public void init(AppCompatActivity parent) {
        this.parentActivity = parent;
    }

    @Override
    public void displayJoke(String joke) {
        JokePresenter.presentJoke(parentActivity, joke);
    }

}
