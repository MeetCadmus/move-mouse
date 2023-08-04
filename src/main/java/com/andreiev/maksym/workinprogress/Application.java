package com.andreiev.maksym.workinprogress;

import com.andreiev.maksym.workinprogress.mousemove.MouseMover;
import com.andreiev.maksym.workinprogress.quartz.QuartzScheduler;

public class Application {
    public static void main(String[] args) {
        QuartzScheduler quartzScheduler = new QuartzScheduler(new MouseMover());
        quartzScheduler.schedule();
    }
}
