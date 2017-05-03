package com.loan.utils;

import org.junit.Test;
import java.text.SimpleDateFormat;
import static org.junit.Assert.*;


public class DateUtilsTest {

    private static final SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
    private final String timeFrom="00:00:00";
    private final String timeTo="06:00:00";

    @Test
    public void isTimeBetweenTwoTimeOk() throws Exception {
        String askLonaTimeOk = "15:25:00";
        assertFalse(DateUtils.isTimeBetweenTwoTime(timeFrom,timeTo , askLonaTimeOk));
    }
    @Test
    public void isTimeBetweenTwoTimeNoTOk() throws Exception {
        String askLonaTimeNotOk = "00:25:00";
        assertTrue(DateUtils.isTimeBetweenTwoTime(timeFrom,timeTo , askLonaTimeNotOk));
    }
}