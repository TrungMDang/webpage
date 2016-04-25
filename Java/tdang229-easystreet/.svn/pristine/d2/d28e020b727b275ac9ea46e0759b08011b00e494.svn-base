/*
 * TCSS 305 – Autumn 2014 
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This program stores information about a Truck object to be used in EasyStreetGUI.
 * 
 * @author Trung Dang
 * @version October 16, 2014
 */
public class Truck extends AbstractVehicle {

    /** The default death time before a truck revives. */
    private static final int TRUCK_DEATH_TIME = 0;

    /**
     * This constructor initializes a truck with given position and direction.
     * 
     * @param theX The X coordinate on the GUI
     * @param theY The Y coordinate on the GUI
     * @param theDir The initial direction of a truck
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, TRUCK_DEATH_TIME);
    }
    /** 
     * This method decides whether a Truck can pass through the given terrain. A Truck travels
     * on street and through lights.
     * 
     * @param theTerrain One of the terrain neighbors this Truck
     * @param theLight A light neighbors this Truck
     * @return true if the terrain is street or light.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = false;
        if (theTerrain.equals(Terrain.STREET) || theTerrain.equals(Terrain.LIGHT)) {
            result = true;
        }
        return result;
    }
    /**
     * This method checks all the neighboring terrain to determine the preferred direction of
     * a Truck object.
     * 
     * @param theNeighbors A map that contains information about neighboring terrain with 
     * <br> the following form: <Direction, Terrain>
     * @return A preferred direction of this Truck object.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = null;
        final List<Direction> dirList = new ArrayList<Direction>();
        final Iterator<Direction> itr = theNeighbors.keySet().iterator();
        while (itr.hasNext()) {
            final Direction dir = itr.next();
            if (theNeighbors.get(dir).equals(Terrain.STREET)
                            || theNeighbors.get(dir).equals(Terrain.LIGHT)) {
                dirList.add(dir);
            }
        }
        Direction randomDir = null;
        if (dirList.contains(getDirection()) || dirList.contains(getDirection().left())
                        || dirList.contains(getDirection().right())) {
            boolean error = true;
            do {
                randomDir = Direction.random();
                if (randomDir.equals(getDirection().reverse()) 
                                || !dirList.contains(randomDir)) {
                    error = true;
                } else {
                    error = false;
                }
            } while (error);
            result = randomDir;
        } else {
            result = getDirection().reverse();
        }
        return result;
    }
}
