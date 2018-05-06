package com.ubs.opsit.interviews.util;

import java.util.regex.Pattern;

public class TimeComponents {
    private int hours;
    private int minutes;
    private int seconds;
    private final String time;

    private static final String TIME24HOURS_PATTERN =
            "([01]?[0-9]|2[0-4]):[0-5][0-9]:[0-5][0-9]";
    private static Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);

    public TimeComponents(String time) {
        this.time = time;
        this.parseTimeString();
    }

    public void parseTimeString() {
        this.validateTime();
        String[] timeComponents = this.time.split(":");
        this.hours = Integer.parseInt(timeComponents[0]);
        this.minutes = Integer.parseInt(timeComponents[1]);
        this.seconds = Integer.parseInt(timeComponents[2]);
    }

    public boolean validateTime() {
        if (!pattern.matcher(this.time).matches()) {
            throw new IllegalArgumentException("Invalid Time String Passed");
        }
        return true;
    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public String getTime() {
        return this.time;
    }
}
