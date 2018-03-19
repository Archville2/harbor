package by.kurlovich.harbor.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Harbor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Harbor.class);
    private static Harbor instance;
    private static ReentrantLock lock = new ReentrantLock();
    private List<Dock> docksList = new ArrayList<>();
    private String harborName;
    private int warehouseCapacity;
    private int warehouseContainers;

    public Harbor(String harborName, int warehouseCapacity) {
        this.harborName = harborName;
        this.warehouseCapacity = warehouseCapacity;
        this.warehouseContainers = 50;
    }

    public static Harbor getInstance() {
        lock.lock();
        if (instance == null) {
            instance = new Harbor("hr-1", 300);
            LOGGER.debug("Harbor {} created.", instance.harborName);
        }
        lock.unlock();
        return instance;
    }

    public void addDock(Dock dock) {
        docksList.add(dock);
        LOGGER.debug("Dock {} added to harbor {}.", dock.getDockName(), instance.harborName);
    }

    public List<Dock> getDocksList() {
        return docksList;
    }

    public void unloadContainersFromShip(int containers) {
        warehouseContainers += containers;
    }

    public int loadContainersToShip(int shipCapacity) {
        return new Random().nextInt(shipCapacity);
    }
}
