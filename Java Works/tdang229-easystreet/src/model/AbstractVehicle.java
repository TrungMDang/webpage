/*
 * TCSS 305 – Autumn 2014 
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.Map;

/**
 * An abstract class that contains all the common information needed to create subclasses that
 * implement the Vehicle interface.
 *  
 * @author Trung Dang
 * @version October 16, 2014
 */
public abstract class AbstractVehicle implements Vehicle {
    
    private static final int TEST = 1;
    /** A string constant for truck.*/
    private static final String TRUCK = "truck";
    
    /** A string constant for car.*/
    private static final String CAR = "car";
    
    /** A string constant for bicycle.*/
    private static final String BICYCLE = "bicycle";
    
    /** A string constant for huamn.*/
    private static final String HUMAN = "human";

    /** An instance field to hold the X coordinate of a vehicle. */
    private int myX;

    /** An instance field to hold the Y coordinate of a vehicle. */
    private int myY;

    /** An instance field to hold the direction of a vehicle. */
    private Direction myDir;

    /** An instance field to hold the status of a vehicle (alive or dead). */
    private boolean myIsAlive;

    /** An instance field to hold the death cycles counted before a vehicle revives. */
    private int myDeathCycles;

    /** An instance field to hold the default death cycle of a vehicle. */
    private final int myVehicleDeathTime;

    /** An instance field to hold a copy of the initial X coordinate of a vehicle. */
    private final int myXCopy; 

    /** An instance field to hold the copy of the initial Y coordinate of a vehicle. */
    private final int myYCopy;

    /** An instance field to hold a copy of the initial direction of a vehicle. */
    private final Direction myDirCopy;
    
    
    
    /**
     * This constructor initializes the state of a Vehicle object using information passed by
     * its subclasses.
     * 
     * @param theX The X coordinate of a vehicle
     * @param theY The Y coordinate of a vehicle
     * @param theDir The direction of a vehicle
     * @param theVehicleDeathTime The default death cycles of a vehicle
     */
    protected AbstractVehicle(final int theX, 
                              final int theY, 
                              final Direction theDir, 
                              final int theVehicleDeathTime) {
        myX = theX;
        myY = theY;
        myDir = theDir;
        myIsAlive = true;
        myDeathCycles = 0;
        myVehicleDeathTime = theVehicleDeathTime;
        myXCopy = theX;
        myYCopy = theY;
        myDirCopy = theDir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return Direction.random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (myIsAlive && theOther.isAlive() && myVehicleDeathTime > theOther.getDeathTime()) {
            myIsAlive = false;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getDeathTime() {
        return myVehicleDeathTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImageFileName() {
        String fileName = null;
        if (myIsAlive) {
            fileName = getAliveImage();
        } else {
            fileName = getDeadImage();
        }
        return fileName;
    }
    /**
     * This method returns a name of the alive image file such as "human.gif".
     * 
     * @return A string that represents the name of the vehicles' alive image files.
     */
    private String getAliveImage() {
        String fileName = null;
        switch (this.getClass().getSimpleName().toLowerCase()) {
            case TRUCK:
                fileName = "truck.gif"; break;
            case CAR:
                fileName = "car.gif"; break;
            case BICYCLE:
                fileName = "bicycle.gif"; break;
            case HUMAN:
                fileName = "human.gif"; break;
            default:
                break;
        }
        return fileName;
    }
    /**
     * This method returns a name of the dead image file such as "human_dead.gif".
     * 
     * @return A name of the Vehicles' dead image files
     */
    private String getDeadImage() {
        String fileName = null;
        switch (this.getClass().getSimpleName().toLowerCase()) {
            case TRUCK:
                fileName = "truck_dead.gif"; break;
            case CAR:
                fileName = "car_dead.gif"; break;
            case BICYCLE:
                fileName = "bicycle_dead.gif"; break;
            case HUMAN:
                fileName = "human_dead.gif"; break;
            default:
                break;
        }
        return fileName;
    }

    /**
     * {@inheritDoc}
     * 
     * @return The current direction of this vehicle
     */
    @Override
    public Direction getDirection() {
        return myDir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return myX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return myY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return myIsAlive;
    }

    /**
     * {@inheritDoc}
     * 
     * This method updates the status (alive or dead) of a vehicle once its death cycles is 
     * reached. For example, this method updates the Car from dead to alive once it has run 
     * 10 times.
     * 
     */
    @Override
    public void poke() {
        if (!myIsAlive) {
            myDeathCycles += 1;
        }
        if (myDeathCycles >= myVehicleDeathTime) {
            myIsAlive = true;
            setDirection(Direction.random());
            myDeathCycles = 0;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        setX(myXCopy);
        setY(myYCopy);
        myIsAlive = true;
        setDirection(myDirCopy);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDir = theDir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(final int theX) {
        myX = theX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final int theY) {
        myY = theY;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(30);
        sb.append(this.getClass().getSimpleName());
        sb.append(": ");
        sb.append(myDeathCycles);
        return sb.toString();
    }
}
