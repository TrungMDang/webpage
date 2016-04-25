/*
 * TCSS 305 � Autumn 2014 
 * Assignment 3 - EasyStreet
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;

import org.junit.Test;

/**
 * JUnit test case for Truck object.
 * 
 * @author Trung Dang
 * @version October 24, 2014
 */
public class TruckTest {
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /**
     * Test method for {@link model.Truck#Truck(integer, integer, Direction)}.
     */
    @Test
    public void testTruckContructor() {
        final Truck testTruck = new Truck(9, 22, Direction.EAST);

        assertEquals("Truck X coordinate not initialized correctly!", 9, testTruck.getX());
        assertEquals("Truck Y coordinate not initialized correctly!", 22, testTruck.getY());
        assertEquals("Truck's direction not initialized correctly!",
                     Direction.EAST, testTruck.getDirection());
        assertEquals("Truck's death time not initialized correctly!", 0, 
                     testTruck.getDeathTime());
        assertTrue("Truck isAlive() fails initially!", testTruck.isAlive());
    }
    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Truck testTruck = new Truck(9, 22, Direction.EAST);

        testTruck.setX(19);
        assertEquals("Truck setX failed!", 19, testTruck.getX());
        testTruck.setY(94);
        assertEquals("Truck setY failed!", 94, testTruck.getY());
        testTruck.setDirection(Direction.WEST);
        assertEquals("Truck setDirection failed!", Direction.WEST, testTruck.getDirection());
    }

    /**
     * Test method for {@link model.Truck#canPass(model.Terrain, model.Light)}.
     */
    @Test
    public void testCanPass() {
        final Truck testTruck = new Truck(9, 22, Direction.EAST);
        //Test each light 
        for (final Light light : Light.values()) {
            //Test each terrain
            for (final Terrain t : Terrain.values()) {
                if (t.equals(Terrain.STREET) || t.equals(Terrain.LIGHT)) {
                    assertTrue("Truck should be able to pass through street with" + light,
                               testTruck.canPass(t, light));
                } else {
                    assertFalse("Truck should not be able to pass to other terrain other than"
                                    + " street and lights", testTruck.canPass(t, light));
                }
            }
        }
    }

    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirection() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.WEST, Terrain.WALL);

        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.WALL && (t.equals(Terrain.STREET) || t.equals(Terrain.LIGHT))) {
                neighbors.put(Direction.CENTER, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, t);
                final Truck testTruck = new Truck(0, 0, Direction.CENTER);
                int tries = 0;
                boolean seenEast = false;
                boolean seenSouth = false;
                while (tries < TRIES_FOR_RANDOMNESS) {
                    tries = tries + 1;
                    final Direction dir = testTruck.chooseDirection(neighbors);
                    assertTrue("Invalid dir chosen, should be North or South, was " + dir,
                               dir == Direction.EAST || dir == Direction.SOUTH);
                    seenSouth = seenSouth || dir == Direction.SOUTH;
                    seenEast = seenEast || dir == Direction.EAST;
                }
                assertTrue("Truck chooseDirections should be random!",
                           seenSouth && seenEast);
                testOnePossibleDirection(neighbors, testTruck);
                
            }
        }

    } 
    /**
     * A helper method for testChooseDirection.
     * 
     * @param theNeighbors A map contains information about neighboring terrains of this Truck
     * @param theTestTruck A test Truck object
     */
    private void testOnePossibleDirection(final Map<Direction, Terrain> theNeighbors, 
                                          final Truck theTestTruck) {
        int tries = 0;
        theNeighbors.put(Direction.EAST, Terrain.WALL);
        while (tries < TRIES_FOR_RANDOMNESS) {
            tries = tries + 1;
            final Direction dir = theTestTruck.chooseDirection(theNeighbors);
            assertSame("Invalid dir chosen, should be south, was " + dir,
                       dir, Direction.SOUTH);
        }
    }
    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testMoreChooseDirection() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        //Test for reverse
        final Truck testTruck = new Truck(0, 0, Direction.WEST);
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.WALL);
        neighbors.put(Direction.CENTER, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.WEST, Terrain.WALL);
        assertEquals("Truck should reverse when possible.", 
                     Direction.EAST, testTruck.chooseDirection(neighbors));
        //Test for turn left
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        neighbors.put(Direction.CENTER, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.WEST, Terrain.WALL);
        assertEquals("Truck should turn left", 
                     Direction.SOUTH, testTruck.chooseDirection(neighbors));
        //Test for turn right         
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.WALL);
        neighbors.put(Direction.CENTER, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.WEST, Terrain.WALL);
        assertEquals("Truck should turn right", Direction.NORTH, 
                     testTruck.chooseDirection(neighbors));
    }
}


