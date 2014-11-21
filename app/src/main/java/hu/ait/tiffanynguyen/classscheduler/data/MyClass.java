package hu.ait.tiffanynguyen.classscheduler.data;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by tiffanynguyen on 11/11/14.
 */
public class MyClass extends SugarRecord<MyClass> implements Serializable {

    String title;
    String description;
    String location;
    int startTime;
    int endTime;
    DayItem.DayType day;

    // needed for SugarORM
    public MyClass() {}


    public MyClass(String title, String description, String location, int startTime, int endTime, DayItem.DayType day) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public DayItem.DayType getDay() {
        return day;
    }

    public void setDay(DayItem.DayType day) {
        this.day = day;
    }
}
