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
        if(getSupportLoaderManager().getLoader(JOKES_LOADER_ID) == null) {
            getSupportLoaderManager().initLoader(JOKES_LOADER_ID, null, this).forceLoad();
        } else {
            getSupportLoaderManager().restartLoader(JOKES_LOADER_ID, null, this).forceLoad();
        }
    }

    @Override
    public Loader<Joke> onCreateLoader(int id, Bundle args) {
        return new GetJokeTask(this, jokeRepository);
    }

    @Override
    public void onLoadFinished(Loader<Joke> loader, Joke data) {
        getLoaderManager().destroyLoader(JOKES_LOADER_ID);
        handleJoke(data);
    }

    private void handleJoke(Joke data) {
        String jokeToTell = getString(R.string.not_jokes_message);
        if(data != null) {
            jokeToTell = data.getJokeDescription();
        }
        JokePresenter.presentJoke(MainActivity.this, jokeToTell);
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
        public Joke loadInBackground() {
            return jokeRepository.getJoke();
        }

    }


}
