package by.kurlovich.harbor.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Harbor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Harbor.class);
    private static Harbor harbor;
    private static ReentrantLock lock = new ReentrantLock();
    private List<Dock> docksList = new ArrayList<>();
    private String harborName;
    private int warehouseCapacity;

    public Harbor(String harborName, int warehouseCapacity) {
        this.harborName = harborName;
        this.warehouseCapacity = warehouseCapacity;
    }

    public static Harbor getInstance() {
        lock.lock();
        if (harbor == null) {
            harbor = new Harbor("hr-1", 100);
            LOGGER.debug("Harbor {} created.", harbor.harborName);
        }
        lock.unlock();
        return harbor;
    }

    public void addDock(Dock dock) {
        docksList.add(dock);
        LOGGER.debug("Dock {} added to harbor {}.", dock.getDockName(), harbor.harborName);
    }
}
