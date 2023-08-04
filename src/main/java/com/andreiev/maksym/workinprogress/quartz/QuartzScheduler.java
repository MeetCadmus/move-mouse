package com.andreiev.maksym.workinprogress.quartz;

import com.andreiev.maksym.workinprogress.mousemove.MouseMover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzScheduler {
    private final Logger logger = LogManager.getLogger(QuartzScheduler.class);
    private final MouseMover mouseMover;

    public QuartzScheduler(MouseMover mouseMover) {
        this.mouseMover = mouseMover;
    }

    public void schedule() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("mouseMover", mouseMover);

        JobDetail job = newJob(MouseMoverJob.class)
                .withIdentity("myJob")
                .setJobData(jobDataMap)
                .build();

        CronTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 8-16 ? * MON-FRI"))
                .build();

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
