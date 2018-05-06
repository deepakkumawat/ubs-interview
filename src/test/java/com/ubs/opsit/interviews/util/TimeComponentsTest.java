package com.ubs.opsit.interviews.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class TimeComponentsTest {

    @Test(expected = NullPointerException.class)
    public void nullTimeStringTest() {
        TimeComponents timeComponents = new TimeComponents(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void blankTimeStringTest() {
        TimeComponents timeComponents = new TimeComponents("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidTimeStringTest() {
        TimeComponents timeComponents = new TimeComponents("asdbdf");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidHourTimeStringTest() {
        TimeComponents timeComponents = new TimeComponents("32:44:43");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidMinuteTimeStringTest() {
        TimeComponents timeComponents = new TimeComponents("22:85:23");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidSecondTimeStringTest() {
        TimeComponents timeComponents = new TimeComponents("22:32:99");
    }

    @Test
    public void validTimeStringHourTest() {
        TimeComponents timeComponents = new TimeComponents("22:32:44");
        assertEquals(timeComponents.getHours(), 22);
    }

    @Test
    public void validTimeStringMinuteTest() {
        TimeComponents timeComponents = new TimeComponents("22:32:44");
        assertEquals(timeComponents.getMinutes(), 32);
    }

    @Test
    public void validTimeStringSecondsTest() {
        TimeComponents timeComponents = new TimeComponents("22:32:44");
        assertEquals(timeComponents.getSeconds(), 44);
    }

}
