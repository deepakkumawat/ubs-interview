package com.ubs.opsit.interviews;


import com.ubs.opsit.interviews.util.TimeComponents;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ubs.opsit.interviews.util.BerlinClockConstant.*;

public class BerlinClockTimeConverter implements TimeConverter {

    private String[] berlinClockState;

    private BerlinClockTimeConverter processTopYellow(int seconds) {
        this.berlinClockState[TOP_SECOND] = (seconds%2 == 0)?LIGHT_YELLOW:LIGHT_OFF;
        return this;
    }

    private String getClockRow(int rowLength, Function<Integer, String> mapper) {
        return IntStream.range(0, rowLength)
                .mapToObj(mapper::apply)
                .collect(Collectors.joining());
    }

    private BerlinClockTimeConverter processHourUpper(int hours) {
        this.berlinClockState[HOUR_UPPER] =
                this.getClockRow(LIGHTS_4,
                        index -> index<(hours/5)?LIGHT_RED:LIGHT_OFF);
        return this;
    }

    private BerlinClockTimeConverter processHourLower(int hours) {
        this.berlinClockState[HOUR_LOWER] =
                this.getClockRow(LIGHTS_4,
                        index -> index<(hours%5)?LIGHT_RED:LIGHT_OFF);
        return this;
    }

    private BerlinClockTimeConverter processMinuteUpper(int minutes) {
        this.berlinClockState[MINUTE_UPPER] =
                this.getClockRow(LIGHTS_11,
                        index ->
                                (index < (minutes / 5))
                                        ? ((index + 1) % 3 == 0
                                            ? LIGHT_RED : LIGHT_YELLOW) : LIGHT_OFF);
        return this;
    }

    private BerlinClockTimeConverter processMinuteLower(int minutes) {
        this.berlinClockState[MINUTE_LOWER] =
                this.getClockRow(LIGHTS_4,
                        index -> index<(minutes%5)?LIGHT_YELLOW:LIGHT_OFF);
        return this;
    }

    private String getBerlinClockTime() {
        return String.join(ROW_JOINER, this.berlinClockState);
    }

    private String [] getBerlinClockBlankState () {
        return new String[ROW_SIZE];
    }

    @Override
    public String convertTime(String aTime) {
        TimeComponents timeComponents = new TimeComponents(aTime);
        this.berlinClockState = this.getBerlinClockBlankState();
        return this.processTopYellow(timeComponents.getSeconds())
                .processHourUpper(timeComponents.getHours())
                .processHourLower(timeComponents.getHours())
                .processMinuteUpper(timeComponents.getMinutes())
                .processMinuteLower(timeComponents.getMinutes())
                .getBerlinClockTime();
    }

}
