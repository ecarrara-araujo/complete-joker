package br.com.ecarrara.completejoker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import javax.inject.Inject;

import br.com.ecarrara.completejoker.di.Injector;
import br.com.ecarrara.completejoker.repository.Joke;
import br.com.ecarrara.completejoker.repository.JokeRepository;
import br.com.ecarrara.jokepresenter.JokePresenter;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Joke> {

    @Inject
    JokeRepository jokeRepository;

    private static final int JOKES_LOADER_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Injector.applicationComponent().inject(this);
        prepareAds();
    }

    private void prepareAds() {
        AdView mAdView = findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        getSupportLoaderManager().initLoader(JOKES_LOADER_ID, null, this);
    }

    @Override
    public Loader<Joke> onCreateLoader(int id, Bundle args) {
        return new GetJokeTask(this, jokeRepository);
    }

    @Override
    public void onLoadFinished(Loader<Joke> loader, Joke data) {
        String jokeToTell = getString(R.string.not_jokes_message);
        if(data != null) {
            jokeToTell = data.getJokeDescription();
        }
        JokePresenter.presentJoke(MainActivity.this, jokeToTell);
    }

    private void displayNoJokes() {

    }

    @Override
    public void onLoaderReset(Loader<Joke> loader) {
    }


    private static class GetJokeTask extends AsyncTaskLoader<Joke> {

        private JokeRepository jokeRepository;

        public GetJokeTask(Context context, JokeRepository jokeRepository) {
            super(context);
            this.jokeRepository = jokeRepository;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }

        @Override
        public Joke loadInBackground() {
            return jokeRepository.getJoke();
        }

    }


}
