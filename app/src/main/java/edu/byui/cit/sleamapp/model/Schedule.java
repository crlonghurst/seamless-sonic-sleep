package edu.byui.cit.sleamapp.model;

import androidx.room.Ignore;

import java.util.ArrayList;

/**
 * Author: Joel Jossie
 * Created: 12 November 2020
 *
 * This class right now is basically just a container for an ArrayList of booleans that
 * determines the days that are active on any given schedule. SleepSchedule uses it to
 * know which days to activate itself. Although the ArrayList could just be a field in
 * the SleepSchedule class, I'm keeping this here in case its functionality gets more
 * complex over time.
 */
public class Schedule {

    public static final int DEFAULT_WEEK_LENGTH = 7;

    private ArrayList<Boolean> daysActive;
    private static final String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public Schedule() {
        daysActive = new ArrayList<>(DEFAULT_WEEK_LENGTH);
        // Initialize ArrayList
        for (int i = 0; i < DEFAULT_WEEK_LENGTH; i++){
            daysActive.add(false);
        }
    }

    @Ignore
    public Schedule(ArrayList<Boolean> daysActive) {
        this.daysActive = daysActive;
    }


    public ArrayList<Boolean> getDaysActive() {
        return daysActive;
    }

    public void setDaysActive(ArrayList<Boolean> daysActive) {
        this.daysActive = daysActive;
    }

    public void setDay(int day, boolean active) {
        daysActive.set(day, active);
    }

    public String formatDays(){
        StringBuilder dayFormats = new StringBuilder();
        for (int i = 0; i < daysActive.size(); i++){
            if (daysActive.get(i)) {
                if (dayFormats.length() != 0) { // If the StringBuilder isn't empty, add separator
                    dayFormats.append(", ");
                }
                dayFormats.append(days[i]);
            }
        }
        return dayFormats.toString();
    }

}
