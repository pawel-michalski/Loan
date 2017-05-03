package com.loan.utils;

import org.joda.time.DateTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;


public class DateUtilsTest {

    private static final SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
    private String askLonaTimeOk = "15:25:00";
    private String askLonaTimeNotOk = "00:25:00";
    private String timeFrom="00:00:00";
    private String timeTo="06:00:00";

    @Test
    public void isTimeBetweenTwoTimeOk() throws Exception {
        assertFalse(DateUtils.isTimeBetweenTwoTime(timeFrom,timeTo ,askLonaTimeOk));
    }
    @Test
    public void isTimeBetweenTwoTimeNoTOk() throws Exception {
        assertTrue(DateUtils.isTimeBetweenTwoTime(timeFrom,timeTo ,askLonaTimeNotOk));
    }
}