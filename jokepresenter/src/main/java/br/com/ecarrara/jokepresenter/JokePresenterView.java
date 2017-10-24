package br.com.ecarrara.jokepresenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

class JokePresenterView extends AppCompatActivity {

    public static final String ARGUMENT_JOKE = "joke";

    public static void navigate(AppCompatActivity parentActivity, String joke) {
        Intent intent = new Intent(parentActivity, JokePresenterView.class);
        intent.putExtra(ARGUMENT_JOKE, joke);

        ActivityCompat.startActivity(parentActivity, intent, null);
    }

    private static final String LAST_KNOWN_JOKE_KEY = "last_known_joke";

    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_presenter_activity);
        processExtras(getIntent().getExtras());
        processSavedInstanceState(savedInstanceState);
        setUpActionBar();
        prepareViews();
    }

    private void processExtras(Bundle extras) {
        if (extras != null) {
            joke = extras.getString(ARGUMENT_JOKE);
        }
    }

    private void processSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            joke = savedInstanceState.getString(LAST_KNOWN_JOKE_KEY);
        }
    }

    private void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.joke_presenter_view);
    }

    private void prepareViews() {
        TextView jokeTextView = findViewById(R.id.joke_presenter_joke_textView);
        jokeTextView.setText(joke);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(LAST_KNOWN_JOKE_KEY, joke);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
