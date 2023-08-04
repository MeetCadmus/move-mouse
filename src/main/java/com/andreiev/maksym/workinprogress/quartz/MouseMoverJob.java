package com.andreiev.maksym.workinprogress.quartz;

import com.andreiev.maksym.workinprogress.mousemove.MouseMover;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class MouseMoverJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        MouseMover mouseMover = (MouseMover) jobExecutionContext.getJobDetail().getJobDataMap().get("mouseMover");
        mouseMover.move();
    }
}