package edu.byui.cit.sleamapp.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Objects;

import edu.byui.cit.sleamapp.R;
import edu.byui.cit.sleamapp.model.SleepSchedule;
import edu.byui.cit.sleamapp.model.database.AppDatabase;
import edu.byui.cit.sleamapp.model.database.SleepScheduleDAO;

/**
 * Author: Joel Jossie
 * Created: 9 November 2020
 *
 * Controller class for the schedules fragment. This will be the main fragment
 * to load when the user opens the app and has one or more existing schedules already.
 */
public class SchedulesFrag extends Fragment {

    private final static String TAG = "SchedulesFrag";

    LinearLayout scheduleContainer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstState){
        super.onCreateView(inflater, container, savedInstState);
        View view = inflater.inflate(R.layout.schedules, container, false);

        // CONNECT TO DATABASE
        AppDatabase db = AppDatabase.getInstance(getContext());
        SleepScheduleDAO dao = db.getSleepScheduleDAO();

        // CHECK FOR DATA
        List<SleepSchedule> schedules = dao.getAll();

        // GET COMPONENTS
        scheduleContainer = view.findViewById(R.id.schedules_card_container);

        // Add FAB and set OnClick listener.
        MaterialButton fabCreate = view.findViewById(R.id.mb_create);
        fabCreate.setOnClickListener(new ButtonClickListener(getActivity(), new EditScheduleFrag(new SleepSchedule())));

        // CREATE COMPONENTS
        for (SleepSchedule s : schedules){
            CardView card = new ScheduleCard(requireContext(), s);
            inflater.inflate(R.layout.schedule_card, card);
            scheduleContainer.addView(card);
            EditScheduleFrag frag = new EditScheduleFrag(s);
            ButtonClickListener buttonClickListener = new ButtonClickListener(getActivity(), frag);
            card.setOnClickListener(buttonClickListener);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // POPULATE DATA
        for (int i = 0; i < scheduleContainer.getChildCount(); i++){ // For every card in the list
            ScheduleCard card = (ScheduleCard)scheduleContainer.getChildAt(i);
            card.populateData();
        }

    }
}
