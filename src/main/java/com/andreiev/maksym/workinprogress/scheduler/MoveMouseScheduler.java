package com.andreiev.maksym.workinprogress.scheduler;

import com.andreiev.maksym.workinprogress.mousemove.MouseMover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MoveMouseScheduler {
    private final Logger logger = LogManager.getLogger(MoveMouseScheduler.class);

    private final MouseMover mouseMover;
    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledFuture;

    public MoveMouseScheduler(MouseMover mouseMover) {
        this.mouseMover = mouseMover;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void schedule(long interval) {
        Runnable moveMouseRunnable = mouseMover::move;
        scheduledFuture = scheduler.scheduleWithFixedDelay(moveMouseRunnable, 0, interval, TimeUnit.MINUTES);
        logger.debug("Scheduled to move mouse every {} min", interval);
    }

    public void stop() {
        scheduledFuture.cancel(true);
    }
}
