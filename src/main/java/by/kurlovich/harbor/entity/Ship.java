package by.kurlovich.harbor.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Ship extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dock.class);
    private static ReentrantLock lock = new ReentrantLock();
    Harbor harbor = Harbor.getInstance();
    private String shipName;
    private int maxContainers;
    private int containers;
    private boolean isDocked = false;
    private Dock shipDock;

    public Ship(String shipName, int maxContainers) {
        this.shipName = shipName;
        this.maxContainers = maxContainers;
        this.containers = new Random().nextInt(maxContainers);

        LOGGER.debug("Ship {} created. Max containers {}, on board containers {}.", shipName, maxContainers, containers);
    }

    @Override
    public void run() {
        try {

            while (!isDocked) {
                for (Dock dock : harbor.getDocksList()) {
                    if (dock.getShipCapacity() > 0 && !isDocked) {
                        lock.lock();
                        dockShip(dock);
                        shipDock = dock;
                        dock.unloadShip(containers);
                        containers = dock.loadShip(maxContainers);
                        TimeUnit.SECONDS.sleep(1);
                        undockShip(shipDock);
                        lock.unlock();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void dockShip(Dock dock) {
        if (dock.dockRequest()) {
            isDocked = true;
            LOGGER.debug("Ship {} docked to dock {}.", shipName, dock.getDockName());
        }
    }

    private void undockShip(Dock dock) {
        dock.undockRequest();
        //isDocked=false;
        LOGGER.debug("Ship {} undocked from dock {}.", shipName, dock.getDockName());
    }
}
