package com.bignerdranch.android.transitionexample;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ZoomTransitionActivity extends AppCompatActivity
        implements DetailFragment.OnFragmentDetailListener,
        GridFragment.OnGridFragmentInteractionListener {

    private TransitionManager mTransitionManager;
    private Scene mScene1;
    private Scene mScene2;

    private Fragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_transition);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            mDetailFragment = new DetailFragment();
            ft.replace(R.id.fragment_container, new GridFragment());
            ft.add(R.id.fragment_container, mDetailFragment);
            ft.commit();
        }

        ViewGroup container = (ViewGroup)findViewById(R.id.fragment_container);
        TransitionInflater transitionInflater = TransitionInflater.from(this);
        mTransitionManager = transitionInflater.inflateTransitionManager(R.transition.transition_manager, container);
//        mScene1 = Scene.getSceneForLayout(container, R.layout.fragment_grid, this);
//        mScene2 = Scene.getSceneForLayout(container, R.layout.fragment_detail, this);

        mScene1 = new Scene(container,
                (ViewGroup) LayoutInflater.from(this).inflate(R.layout.fragment_grid, container, false));
        mScene2 = new Scene(container,
                (ViewGroup) LayoutInflater.from(this).inflate(R.layout.fragment_detail, container, false));
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            //getFragmentManager().popBackStack();
            mTransitionManager.transitionTo(mScene2);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onFragmentDetailInteraction() {

    }

    @Override
    public void onFragmentDetailAttached(View view) {
        //mTransitionManager.transitionTo(mScene2);
    }

    @Override
    public void onGridFragmentInteraction(View view) {
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        Fragment fragment = new DetailFragment();
//        ft.add(R.id.fragment_container, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
        mDetailFragment.getView().setVisibility(View.VISIBLE);
        mTransitionManager.transitionTo(mScene2);
    }
}
