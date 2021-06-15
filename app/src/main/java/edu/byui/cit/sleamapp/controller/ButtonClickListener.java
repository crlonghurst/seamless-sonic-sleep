package edu.byui.cit.sleamapp.controller;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import edu.byui.cit.sleamapp.R;

/**
 * Author: Joel Jossie
 * Created: 10 November 2020
 *
 * A helper class that implements OnClickListener to make switching between fragments
 * at the click of a button much easier.
 *<br>
 * Example usage:<br>
 * <code>Fragment frag = new FragToSwitchTo();<br>
 * Button btn = view.findViewById("btn_example");<br>
 * btn.setOnClickListener( new ButtonClickListener( getActivity(), frag );</code>
 *
 */
public class ButtonClickListener implements View.OnClickListener {

    FragmentActivity activity;
    Fragment fragment;

    public ButtonClickListener(FragmentActivity act, Fragment frag) {
        activity = act;
        fragment = frag;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragContainer, fragment);
        transaction.commit();
    }
}
