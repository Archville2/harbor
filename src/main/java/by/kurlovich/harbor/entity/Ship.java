package by.kurlovich.harbor.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Ship extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dock.class);
    private String shipName;
    private int maxContainers;
    private int containers;

    public Ship(String shipName, int maxContainers) {
        this.shipName = shipName;
        this.maxContainers = maxContainers;
        this.containers = new Random().nextInt(maxContainers);

        LOGGER.debug("Ship {} created. Max containers {}, on board containers {}.", shipName, maxContainers, containers);
    }

    @Override
    public void run() {

    }
}
