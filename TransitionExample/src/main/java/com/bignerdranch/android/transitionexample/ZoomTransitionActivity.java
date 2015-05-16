package com.bignerdranch.android.transitionexample;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class ZoomTransitionActivity extends AppCompatActivity
        implements DetailFragment.OnFragmentDetailListener,
        GridFragment.OnGridFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_transition);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new GridFragment());
        ft.commit();
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onFragmentDetailInteraction() {

    }

    @Override
    public void onFragmentDetailAttached(View view) {

    }

    @Override
    public void onGridFragmentInteraction(View view) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment fragment = new DetailFragment();
        ft.add(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
