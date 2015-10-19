/*
 * TCSS 305 - Easy Street
 */

package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import model.Direction;
import model.Human;
import model.Light;
import model.Terrain;

import org.junit.Test;

/**
 * Unit tests for class Human.
 * 
 * @author Alan Fowler (acfowler@uw.edu)
 * @version Autumn 2014
 */
public class HumanTest {

    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;
    
    /** Test method for Human constructor. */
    @Test
    public void testHumanConstructor() {
        final Human h = new Human(10, 11, Direction.CENTER, Terrain.GRASS);
        
        assertEquals("Human x coordinate not initialized correctly!", 10, h.getX());
        assertEquals("Human y coordinate not initialized correctly!", 11, h.getY());
        assertEquals("Human direction not initialized correctly!",
                     Direction.CENTER, h.getDirection());
        assertEquals("Human death time not initialized correctly!", 50, h.getDeathTime());
        assertTrue("Human isAlive() fails initially!", h.isAlive());
    }
    
    /** Test method for Human setters. */
    @Test
    public void testHumanSetters() {
        final Human h = new Human(10, 11, Direction.CENTER, Terrain.GRASS);
        
        h.setX(12);
        assertEquals("Human setX failed!", 12, h.getX());
        h.setY(13);
        assertEquals("Human setY failed!", 13, h.getY());
        h.setDirection(Direction.NORTH);
        assertEquals("Human setDirection failed!", Direction.NORTH, h.getDirection());
    }

    /**
     * Test method for {@link Human#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        // start from each terrain type except WALL
        for (final Terrain testTerrain : Terrain.values()) {
            if (testTerrain == Terrain.WALL) {
                continue; // Humans do not start on Walls
            }
            final Human human = new Human(0, 0, Direction.CENTER, testTerrain);
            // go to each terrain type
            for (final Terrain t : Terrain.values()) {
                // try the test under each light condition
                for (final Light l : Light.values()) {
                    if ((t.equals(testTerrain))
                        || (t == Terrain.LIGHT && testTerrain == Terrain.STREET)
                        || (t == Terrain.STREET && testTerrain == Terrain.LIGHT)) {
                        // humans can pass the terrain they start on under any light
                        // conditions, and can also pass lights if they start on
                        // streets and vice-versa

                        assertTrue("Human started on " + testTerrain
                                                   + " should be able to pass " + t
                                                   + ", with light " + l,
                                   human.canPass(t, l));
                    } else {
                        // humans can't leave their terrain

                        assertFalse("Human started on " + testTerrain
                                     + " should NOT be able to pass " + t
                                     + ", with light " + l, human.canPass(t, l));
                    }
                }
            }
        }
    }

    /**
     * Test method for {@link Human#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirection() {
        // humans need to stay on their own terrain, but otherwise can do anything
        // Humans can not choose Center. Choosing Center would result in no movement.

        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.WALL);

        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.WALL) { // Vehicles don't start on Walls.
                neighbors.put(Direction.CENTER, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, t);
                final Human human = new Human(0, 0, Direction.CENTER, t);
                int tries = 0;
                boolean seenSouth = false;
                boolean seenEast = false;
                while (tries < TRIES_FOR_RANDOMNESS) {
                    tries = tries + 1;
                    final Direction dir = human.chooseDirection(neighbors);
                    assertTrue("invalid dir chosen, should be east or south, was " + dir,
                               dir == Direction.EAST || dir == Direction.SOUTH);
                    seenSouth = seenSouth || dir == Direction.SOUTH;
                    seenEast = seenEast || dir == Direction.EAST;
                }
                assertTrue("Human chooseDirections should be random!",
                           seenSouth && seenEast);
                
                neighbors.put(Direction.EAST, Terrain.WALL);
                tries = 0;
                while (tries < TRIES_FOR_RANDOMNESS) {
                    tries = tries + 1;
                    final Direction dir = human.chooseDirection(neighbors);
                    assertSame("invalid dir chosen, should be south, was " + dir,
                               dir, Direction.SOUTH);
                }
            }
        }
    }

}
