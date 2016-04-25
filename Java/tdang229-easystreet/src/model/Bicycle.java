/*
 * TCSS 305 – Autumn 2014 
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.Map;

/**
 * This program stores information about a Bicycle object to be used in EasyStreetGUI.
 * 
 * @author Trung Dang 
 * @version October 16, 2014
 */
public class Bicycle extends AbstractVehicle {
    
    /** The default death time before a bicycle revives.*/
    private static final int BICYCLE_DEATH_TIME = 20;
    
    /**
     * This constructor passes information about a Bicycle object to the superclass Abstract-
     * Vehicle.
     * 
     * @param theX The initial X coordinate of a Bicycle
     * @param theY The initial Y coordinate of a Bicycle
     * @param theDir The initial direction of a Bicycle
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, BICYCLE_DEATH_TIME);
    }
    /** 
     * This method decides whether a Bicycle can pass through the given terrain and street
     * light status. A bicycle travels on trail, street and stop at red or yellow light.
     * 
     * @param theTerrain One of the terrain neighbors this Bicycle
     * @param theLight A light neighbors this Bicycle
     * @return true if the terrain is a trail, street, or green light.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = false;
        if (theTerrain.equals(Terrain.TRAIL) || theTerrain.equals(Terrain.STREET)
                        || theTerrain.equals(Terrain.LIGHT)
                        && theLight.equals(Light.GREEN)) {
            result = true;
        }
        return result;
    }
    /**
     * This method checks all the neighboring terrain to determine the preferred direction of
     * a Bicycle object.
     * 
     * @param theNeighbors A map that contains information about neighboring terrain with 
     * <br> the following form: <Direction, Terrain>
     * @return A preferred direction of this Bicycle object.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = null;
        result = checkTrail(theNeighbors);
        if (result == null) {
            if ((theNeighbors.get(getDirection()) == Terrain.STREET) 
                            || (theNeighbors.get(getDirection()) == Terrain.LIGHT)) {
                //go straight
                result = getDirection();
            } else if ((theNeighbors.get(getDirection().left()) == Terrain.STREET) 
                            || (theNeighbors.get(getDirection().left()) == Terrain.LIGHT)) {
                //turn left
                result = getDirection().left(); 
            } else if ((theNeighbors.get(getDirection().right()) == Terrain.STREET) 
                            || (theNeighbors.get(getDirection().right()) == Terrain.LIGHT)) {
                //turn right
                result = getDirection().right();
            } else {
                //reverse
                result = getDirection().reverse(); 
            }
        }
       
        return result;
    }
    /**
     * This method helps the chooseDirection method by checking if there is a trail neighboring
     * this Bicycle object.
     * 
     * @param theNeighbors A map that contains information about neighboring terrain with 
     * <br> the following form: <Direction, Terrain>
     * @return A direction that indicates there is a trail
     */
    private Direction checkTrail(final Map<Direction, Terrain> theNeighbors) {
        Direction result = null;
        if (theNeighbors.get(Direction.CENTER).equals(Terrain.TRAIL)) { //on a trail
            result = getDirection();
        } else if (theNeighbors.get(getDirection().left()).equals(Terrain.TRAIL)) { 
            //trail to the left
            result = getDirection().left();
        } else if (theNeighbors.get(getDirection().right()).equals(Terrain.TRAIL)) {
            //trail to the right
            result = getDirection().right();
        }
        return result;
    }
}
