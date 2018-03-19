package by.kurlovich.harbor.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dock {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dock.class);
    private String dockName;
    private int shipCapacity;
    private Harbor harbor = Harbor.getInstance();

    public Dock(String dockName, int shipCapacity) {
        this.dockName = dockName;
        this.shipCapacity = shipCapacity;

        LOGGER.debug("Dock {} created. Ship capacity is {}", dockName, shipCapacity);
    }

    public String getDockName() {
        return dockName;
    }

    public int getShipCapacity() {
        return shipCapacity;
    }

    public boolean dockRequest() {
        if (shipCapacity > 0) {
            shipCapacity--;
            return true;
        }
        return false;
    }

    public void undockRequest() {
        shipCapacity++;
    }

    public void unloadShip(int containers) {
        harbor.unloadContainersFromShip(containers);
    }

    public int loadShip(int shipCapacity) {
        return harbor.loadContainersToShip(shipCapacity);
    }
}
