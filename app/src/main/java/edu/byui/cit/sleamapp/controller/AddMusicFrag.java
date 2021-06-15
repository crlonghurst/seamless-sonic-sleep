package edu.byui.cit.sleamapp.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.byui.cit.sleamapp.R;

/**
 * Author: Joel Jossie
 * Created: 10 November 2020
 *
 * Controller class for the add_music fragment.
 */
public class AddMusicFrag extends Fragment {

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_music, container, false);


        return view;
    }
}
