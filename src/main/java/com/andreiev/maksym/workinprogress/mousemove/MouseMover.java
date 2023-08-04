package com.andreiev.maksym.workinprogress.mousemove;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.Random;

public class MouseMover {
    private final Logger logger = LogManager.getLogger(MouseMover.class);

    public void move() {
        try {
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            int x = Double.valueOf(mouseLocation.getX()).intValue();
            int y = Double.valueOf(mouseLocation.getY()).intValue();

            Random random = new Random();
            x += random.nextInt(10);
            y += random.nextInt(10);

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int height = gd.getDisplayMode().getHeight();
            int width = gd.getDisplayMode().getWidth();
            if (x > height) x = height;
            if (y > height) y = width;

            Robot robot = new Robot();
            robot.mouseMove(x, y);
            logger.info("Moving  mouse to x={} y={}", x, y);
        } catch (AWTException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
