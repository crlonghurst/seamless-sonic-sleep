package edu.byui.cit.sleamapp.controller;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.time.LocalTime;

import edu.byui.cit.sleamapp.model.SonicEvent;

public class FallAsleepTimePickerFragment extends DialogFragment {
    TimePickerDialog.OnTimeSetListener listener;
    SonicEvent sonicEvent;
    LocalTime initTime;

    public FallAsleepTimePickerFragment(TimePickerDialog.OnTimeSetListener listener, SonicEvent event) {
        Log.d("aaaahh", "FallAsleepTimePickerFragment Created");
        this.listener = listener;
        sonicEvent = event;
        initTime = event.getStartTime();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("aaaahh", "FallAsleepTimePickerFragment onCreateDialog() Called");
        int hour = initTime.getHour();
        int minute = initTime.getMinute();
        return new TimePickerDialog(getContext(), listener, hour, minute, DateFormat.is24HourFormat(getContext()));
    }
}