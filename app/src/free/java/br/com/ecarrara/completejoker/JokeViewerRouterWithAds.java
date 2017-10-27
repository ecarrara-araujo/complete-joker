package br.com.ecarrara.completejoker;

import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import br.com.ecarrara.completejoker.repository.JokeViewerRouter;
import br.com.ecarrara.jokepresenter.JokePresenter;
import timber.log.Timber;

public class JokeViewerRouterWithAds extends AdListener implements JokeViewerRouter {

    private AppCompatActivity parentActivity;
    private InterstitialAd interstitialAd;
    private String jokeToTell;

    @Override
    public void init(AppCompatActivity parent) {
        this.parentActivity = parent;
        this.interstitialAd = new InterstitialAd(parent);
        prepareInterstitialAd();
    }

    private void prepareInterstitialAd() {
        this.interstitialAd.setAdUnitId(parentActivity.getString(R.string.interstitial_ad_unit_id));
        this.interstitialAd.setAdListener(this);

        prepareNextAd();
    }

    @Override
    public void displayJoke(String joke) {
        jokeToTell = joke;
        this.interstitialAd.show();
    }


    @Override
    public void onAdFailedToLoad(int i) {
        Timber.e("Ad failed to load with error code: " + i);
        JokePresenter.presentJoke(parentActivity, jokeToTell);
    }

    @Override
    public void onAdClosed() {
        prepareNextAd();
        JokePresenter.presentJoke(parentActivity, jokeToTell);
    }

    private void prepareNextAd() {
        this.interstitialAd.loadAd(buildAdRequest());
    }

    private AdRequest buildAdRequest() {
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    }

}
