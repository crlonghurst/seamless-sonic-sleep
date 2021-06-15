package edu.byui.cit.sleamapp.controller;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalTime;

import edu.byui.cit.sleamapp.R;
import edu.byui.cit.sleamapp.model.*;
import edu.byui.cit.sleamapp.system.SystemResources;

/**
 * Author: Joel Jossie
 * Created: 9 November 2020
 * Last edited: 19 November, Joel Jossie
 * <p>
 * Controller class for the edit_schedule fragment.
 * </p>
 */
public class EditScheduleFrag extends Fragment implements CompoundButton.OnCheckedChangeListener{
    public static final String TAG = "EditScheduleFrag";

    // Declare UI Components
    Button fallAsleepTimeButton;
    Button fallAsleepSoundButton;
    Button stayAsleepTimeButton;
    Button stayAsleepSoundButton;
    Button wakeUpTimeButton;
    Button wakeUpSoundButton;
    EditText scheduleName;
    ChipGroup daysActiveChips;
    Chip sun, mon, tue, wed, thu, fri, sat;
    ImageButton cancelButton, saveButton;

    // Declare Model
    SleepSchedule sleepSchedule;
    SonicEvent fallAsleepEvent, stayAsleepEvent, wakeUpEvent;

    /**
     * Simple Constructor for the fragment which requires a SleepSchedule Object to be passed in.
     * @param sleepSchedule An existing or new/empty SleepSchedule to be edited by this fragment.
     */
    public EditScheduleFrag (SleepSchedule sleepSchedule){
        this.sleepSchedule = sleepSchedule;

        fallAsleepEvent = sleepSchedule.getFallAsleepEvent();
        stayAsleepEvent = sleepSchedule.getStayAsleepEvent();
        wakeUpEvent = sleepSchedule.getWakeUpEvent();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // ----- INITIALIZE VIEW -----
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.edit_schedule, container, false);

        // ----- GET COMPONENTS -----
        cancelButton = view.findViewById(R.id.btn_cancel);
        saveButton = view.findViewById(R.id.btn_confirm);
        scheduleName = view.findViewById(R.id.schedule_name_field);
        daysActiveChips = view.findViewById(R.id.days_active_chip_group);
        fallAsleepSoundButton = view.findViewById(R.id.btn_fall_asleep_sound);
        fallAsleepTimeButton = view.findViewById(R.id.btn_fall_asleep_time);
        stayAsleepSoundButton = view.findViewById(R.id.btn_stay_asleep_sound);
        stayAsleepTimeButton = view.findViewById(R.id.btn_stay_asleep_time);
        wakeUpTimeButton = view.findViewById(R.id.btn_wake_up_time);
        wakeUpSoundButton = view.findViewById(R.id.btn_wake_up_sound);
        sun = view.findViewById(R.id.chip_sunday);
        mon = view.findViewById(R.id.chip_monday);
        tue = view.findViewById(R.id.chip_tuesday);
        wed = view.findViewById(R.id.chip_wednesday);
        thu = view.findViewById(R.id.chip_thursday);
        fri = view.findViewById(R.id.chip_friday);
        sat = view.findViewById(R.id.chip_saturday);

        // ----- SET LISTENERS -----
        cancelButton.setOnClickListener(v -> getActivity().onBackPressed());
        saveButton.setOnClickListener(v -> {
            sleepSchedule.save(getContext());
            getActivity().onBackPressed();
        });
        sun.setOnCheckedChangeListener(this);
        mon.setOnCheckedChangeListener(this);
        tue.setOnCheckedChangeListener(this);
        wed.setOnCheckedChangeListener(this);
        thu.setOnCheckedChangeListener(this);
        fri.setOnCheckedChangeListener(this);
        sat.setOnCheckedChangeListener(this);

        fallAsleepSoundButton.setOnClickListener(new ButtonClickListener(getActivity(), new AddMusicFrag()));
        stayAsleepSoundButton.setOnClickListener(new ButtonClickListener(getActivity(), new AddMusicFrag()));
        wakeUpSoundButton.setOnClickListener(new ButtonClickListener(getActivity(), new AddMusicFrag()));

        fallAsleepTimeButton.setOnClickListener(v -> {
            DialogFragment timePicker = new FallAsleepTimePickerFragment(new FallAsleepTimeListener(sleepSchedule, fallAsleepTimeButton), fallAsleepEvent);
            timePicker.show(getActivity().getSupportFragmentManager(), "Fall Asleep Event Time Picker");
        });
        stayAsleepTimeButton.setOnClickListener(v -> {
            DialogFragment timePicker = new StayAsleepTimePickerFragment(new StayAsleepTimeListener(sleepSchedule, stayAsleepTimeButton), stayAsleepEvent);
            timePicker.show(getActivity().getSupportFragmentManager(), "Stay Asleep Event Time Picker");
        });
        wakeUpTimeButton.setOnClickListener(v -> {
            DialogFragment timePicker = new WakeUpTimePickerFragment(new WakeUpTimeListener(sleepSchedule, wakeUpTimeButton), wakeUpEvent);
            timePicker.show(getActivity().getSupportFragmentManager(), "Wake Up Event Time Picker");
        });

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime lt = fallAsleepEvent.getStartTime();
            SystemResources.getInstance(getActivity()).setAlarm(lt.getHour(), lt.getMinute());
        }

        // ----- POPULATE DATA -----
        populateSleepScheduleData();

        return view;
    }


    /**
     * Listener for the chip buttons. Updates the SleepSchedule's Schedule object accordingly.
     * @param buttonView the chip clicked
     * @param isChecked whether or not it's active
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int index = daysActiveChips.indexOfChild((Chip)buttonView);
        sleepSchedule.getSchedule().setDay(index, isChecked);
    }


    /**
     * Method to put the proper values into all fields in the fragment based on a SleepSchedule object
     */

    private void populateSleepScheduleData(){
        // Title
        scheduleName.setText(sleepSchedule.getName());


        // Chip group
        Schedule s = sleepSchedule.getSchedule();
        for (int i = 0; i < s.getDaysActive().size(); i++){
            if (s.getDaysActive().get(i)) { // if this day is active
                Chip chip = (Chip)daysActiveChips.getChildAt(i); // get the chip at this index
                chip.setChecked(s.getDaysActive().get(i)); // check it if that day is active
            }
        }

        // Times
        fallAsleepTimeButton.setText(fallAsleepEvent.formatStartTime());
        stayAsleepTimeButton.setText(stayAsleepEvent.formatStartTime());
        wakeUpTimeButton.setText(wakeUpEvent.formatStartTime());


        //TODO implement sound stuff
    }
}

/**
 * A class to implement the listener specific to the FallAsleepTime Button.
 */
class FallAsleepTimeListener implements TimePickerDialog.OnTimeSetListener{


    SleepSchedule sleepSchedule;
    Button fallAsleepTimeButton;

    public FallAsleepTimeListener(SleepSchedule schedule, Button button){
        this.sleepSchedule = schedule;
        this.fallAsleepTimeButton = button;
    }

    /**
     * Listener method, called once a time picker dialog has been closed. Updates the Sonic event of the SleepSchedule with the newly
     * selected time, and updates the button text.
     * @param view the timePickerDialog just used
     * @param hourOfDay the hour picked
     * @param minute the minute picked
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        LocalTime timePicked = LocalTime.of(hourOfDay, minute);
        sleepSchedule.getFallAsleepEvent().setStartTime(timePicked);
        fallAsleepTimeButton.setText(sleepSchedule.getFallAsleepEvent().formatStartTime()); 
    }
}


/**
 * A class to implement the listener specific to the StayAsleepTime Button.
 */
class StayAsleepTimeListener implements TimePickerDialog.OnTimeSetListener{


    SleepSchedule sleepSchedule;
    Button fallAsleepTimeButton;

    public StayAsleepTimeListener(SleepSchedule schedule, Button button){
        this.sleepSchedule = schedule;
        this.fallAsleepTimeButton = button;
    }

    /**
     * Listener method, called once a time picker dialog has been closed. Updates the Sonic event of the SleepSchedule with the newly
     * selected time, and updates the button text.
     * @param view the timePickerDialog just used
     * @param hourOfDay the hour picked
     * @param minute the minute picked
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        LocalTime timePicked = LocalTime.of(hourOfDay, minute);
        sleepSchedule.getStayAsleepEvent().setStartTime(timePicked);
        fallAsleepTimeButton.setText(sleepSchedule.getStayAsleepEvent().formatStartTime());

    }
}


/**
 * A class to implement the listener specific to the WakeUpTime Button.
 */
class WakeUpTimeListener implements TimePickerDialog.OnTimeSetListener{


    SleepSchedule sleepSchedule;
    Button fallAsleepTimeButton;

    public WakeUpTimeListener(SleepSchedule schedule, Button button){
        this.sleepSchedule = schedule;
        this.fallAsleepTimeButton = button;
    }

    /**
     * Listener method, called once a time picker dialog has been closed. Updates the Sonic event of the SleepSchedule with the newly
     * selected time, and updates the button text.
     * @param view the timePickerDialog just used
     * @param hourOfDay the hour picked
     * @param minute the minute picked
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        LocalTime timePicked = LocalTime.of(hourOfDay, minute);
        sleepSchedule.getWakeUpEvent().setStartTime(timePicked);
        fallAsleepTimeButton.setText(sleepSchedule.getWakeUpEvent().formatStartTime());
    }
}