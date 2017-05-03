package com.loan.scheduler;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Calendar;
import java.util.Date;


public class SchedulerTest {
    private final Logger log = Logger.getLogger(SchedulerTest.class);

    @Test
    public void processLoans() throws Exception {
        org.springframework.scheduling.support.CronTrigger trigger =
                new CronTrigger("5 * * * * * ");
        Calendar today = Calendar.getInstance();
        final Date todayTime = today.getTime();

        Date nextExecutionTime = trigger.nextExecutionTime(
                new TriggerContext() {
                    @Override
                    public Date lastScheduledExecutionTime() {
                        return todayTime;
                    }

                    @Override
                    public Date lastActualExecutionTime() {
                        return todayTime;
                    }

                    @Override
                    public Date lastCompletionTime() {
                        return todayTime;
                    }
                });
    }

}