package by.kurlovich.harbor.starter;

import by.kurlovich.harbor.entity.Dock;
import by.kurlovich.harbor.entity.Harbor;
import by.kurlovich.harbor.entity.Ship;

import java.util.ArrayList;
import java.util.List;

public class HarborStarter {
    public static void main(String... args) {

        Harbor harbor = Harbor.getInstance();

        harbor.addDock(new Dock("d-1", 1)); //dock name, max ships
        harbor.addDock(new Dock("d-2", 1));
        harbor.addDock(new Dock("d-3", 1));

        List<Ship> shipsList = new ArrayList<>();

        shipsList.add(new Ship("sh-1", 20)); //ship name, max containers capacity
        shipsList.add(new Ship("sh-2", 30));
        shipsList.add(new Ship("sh-3", 15));

        for (Ship ship : shipsList) {
            Thread th = new Thread(ship);
            th.start();

        }
    }
}
