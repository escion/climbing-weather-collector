package com.escion.climbingweathercollector.utils;

import com.escion.climbingweathercollector.dto.common.Period;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class CommonsUtilsTest {

    private static final String timezone = "Europe/Rome";

    @Test
    public void test2am() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1586995200); //12am UTC - 2am
        Assertions.assertEquals(Period.NIGHT.name(), period);
    }

    @Test
    public void test12pm() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1587031200); //10am UTC - 12pm
        Assertions.assertEquals(Period.AFTERNOON.name(), period);
    }

    @Test
    public void test11pm() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1587070800); //9pm UTC  - 11pm
        Assertions.assertEquals(Period.EVENING.name(), period);
    }

    @Test
    public void test3pm() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1587042000); //1pm UTC - 3pm
        Assertions.assertEquals(Period.AFTERNOON.name(), period);
    }

    @Test
    public void test2pm() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1587038400); //12pm UTC - 2pm
        Assertions.assertEquals(Period.AFTERNOON.name(), period);
    }

    @Test
    public void test12am() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String period = callPeriodOfTheDay(1587074400); //10pm UTC - 12am
        Assertions.assertEquals(Period.NIGHT.name(), period);
    }

    @Test
    public void testAverage(){
        Assertions.assertEquals(0.0, CommonsUtils.average(null, 20.0));
        Assertions.assertEquals(0.0, CommonsUtils.average(null, null));
        Assertions.assertEquals(0.0, CommonsUtils.average(20.0, null));
        Assertions.assertEquals(10.0, CommonsUtils.average(20.0, 0.0));
        Assertions.assertEquals(56.4, CommonsUtils.average(23.658, 89.21));
    }

    private String callPeriodOfTheDay(Integer timestamp) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return CommonsUtils.getPeriodOfDay(timestamp, timezone);
    }
}
