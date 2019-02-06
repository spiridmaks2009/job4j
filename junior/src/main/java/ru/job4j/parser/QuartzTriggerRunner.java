package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Класс, описывающий триггер планировщика
 *
 * @author maksimspiridonov
 */
public class QuartzTriggerRunner  {
    private static final Logger LOG = LogManager.getLogger(QuartzTriggerRunner.class.getName());

    public void task() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail =
                newJob(QuartzJob.class).withIdentity("job").build();
        Config config = new Config();
        config.init();
        CronTrigger trigger = newTrigger().withIdentity("trigger")
                .withSchedule(cronSchedule(config.get("cron.time"))).forJob("job").build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        LOG.info("Quartz trigger is runned");
    }
}
