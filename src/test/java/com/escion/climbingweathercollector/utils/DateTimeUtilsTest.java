package com.escion.climbingweathercollector.utils;

import com.escion.climbingweathercollector.dto.common.Period;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DateTimeUtilsTest {

    private static final String timezone = "Europe/Rome";

    @Test
    public void test12am() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1586995200); //12am UTC - 2am
        Assertions.assertEquals(Period.NIGHT.name(), period);
    }

    @Test
    public void test10am() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1587031200); //10am UTC - 12am
        Assertions.assertEquals(Period.MORNING.name(), period);
    }

    @Test
    public void test21pm() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1587070800); //9pm UTC  - 11pm
        Assertions.assertEquals(Period.EVENING.name(), period);
    }

    @Test
    public void test1pm() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1587042000); //1pm UTC - 3pm
        Assertions.assertEquals(Period.AFTERNOON.name(), period);
    }

    @Test
    public void test12pm() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1587038400); //12pm UTC - 14pm
        Assertions.assertEquals(Period.AFTERNOON.name(), period);
    }

    private String callPeriodOfTheDay(Integer timestamp) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = DateTimeUtils.class.getDeclaredMethod("getPeriodOfDay", Integer.TYPE, String.class);
        m.setAccessible(true); //if security settings allow this
       return (String) m.invoke(null, timestamp, timezone);
    }
}
