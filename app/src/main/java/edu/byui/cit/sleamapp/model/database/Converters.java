package edu.byui.cit.sleamapp.model.database;

import androidx.room.TypeConverter;

import java.time.LocalTime;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public ArrayList<Boolean> fromString(String value){
        char[] values = value.toCharArray();
        ArrayList<Boolean> data = new ArrayList<>();
        for (char c : values){
            data.add(c == 't');
        }
        return data;
    }

    @TypeConverter
    public String booleanArrayToString(ArrayList<Boolean> data){
        StringBuilder value = new StringBuilder();
        for (Boolean b : data){
            if(b)
                value.append("t");
            else
                value.append("f");
        }
        return value.toString();
    }
    @TypeConverter
    public static LocalTime toDate(String timeString) {
        if (timeString == null) {
            return null;
        } else {
            return LocalTime.parse(timeString);
        }
    }

    @TypeConverter
    public static String toDateString(LocalTime time) {
        if (time == null) {
            return null;
        } else {
            return time.toString();
        }
    }
}
