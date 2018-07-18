package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TaskScheduling implements SchedulingConfigurer {

    @Autowired
    private ConfigReader configReader;

    @Autowired
    private ServiceA service1;


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        List<String> configs = configReader.list();

        System.out.println(configs);

        for (String cron : configs) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    service1.printText();
                }
            };

            taskRegistrar.addTriggerTask(run, new Trigger() {
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext) {
                    System.out.println(
                            new SimpleDateFormat("HH:mm:ss").format(new Date()) +
                                    "thread =" + run +
                                    ": cron" + cron);
                    CronTrigger trigger = new CronTrigger(cron);
                    Date nextExec = trigger.nextExecutionTime(triggerContext);
                    return nextExec;
                }
            });
        }
    }
}
