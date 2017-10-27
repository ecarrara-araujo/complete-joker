package br.com.ecarrara.completejoker;

import android.os.Bundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.ecarrara.completejoker.core.IdlingResourceProvider;

public class AdFragment extends android.support.v4.app.Fragment {

    private CountingIdlingResource idlingResource;

    public AdFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ad, container, false);
        prepareIdlingResource();
        prepareAds(root);
        return root;

    }

    private void prepareIdlingResource() {
        IdlingResourceProvider idlingResourceProvider = IdlingResourceProvider.getInstance();
        if (idlingResourceProvider != null) {
            idlingResource = idlingResourceProvider.getIdlingResource();
        }
    }

    private void prepareAds(View root) {
        final AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        if (idlingResource != null) {
            idlingResource.increment();
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    idlingResource.decrement();
                    mAdView.setAdListener(null);
                }
            });
        }

        mAdView.loadAd(adRequest);
    }

}
