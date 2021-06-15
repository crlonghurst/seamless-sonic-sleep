package edu.byui.cit.sleamapp.controller;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import edu.byui.cit.sleamapp.R;
import edu.byui.cit.sleamapp.model.SleepSchedule;

/**
 * Author: Joel Jossie
 * Created: 10 December 2020
 *
 * Controller class for schedule_card.xml. Represents a sleep schedule as a CardView in SchedulesFrag.
 */
public class ScheduleCard extends CardView {

    SleepSchedule sleepSchedule;

    private ScheduleCard(@NonNull Context context) {
        super(context);
    }
    public ScheduleCard(@NonNull Context context, SleepSchedule sleepSchedule){
        this(context);
        this.sleepSchedule = sleepSchedule;
    }

    public void populateData(){
        // GET COMPONENTS
        TextView scheduleName = findViewById(R.id.alarm_name);
        TextView scheduleTime = findViewById(R.id.alarm_time);
        TextView scheduleDays = findViewById(R.id.alarm_days);

        // SET VALUES
        scheduleName.setText(sleepSchedule.getName());
        String timeText = sleepSchedule.getFallAsleepEvent().formatStartTime() + " - " +
                sleepSchedule.getStayAsleepEvent().formatStartTime() + " - " +
                sleepSchedule.getWakeUpEvent().formatStartTime();
        scheduleTime.setText(timeText);
        scheduleDays.setText(sleepSchedule.getSchedule().formatDays());
    }

}
