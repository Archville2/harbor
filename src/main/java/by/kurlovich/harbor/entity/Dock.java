package by.kurlovich.harbor.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dock {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dock.class);
    private String dockName;
    private int shipCapacity;

    public Dock(String dockName, int shipCapacity) {
        this.dockName = dockName;
        this.shipCapacity = shipCapacity;

        LOGGER.debug("Dock {} created.", dockName);
    }

    public String getDockName() {
        return dockName;
    }
}
