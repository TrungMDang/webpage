/*
 * TCSS 305 - Autumn 2014
 * Assignment 5 Part 1 - PowerPaint
 */

package gui;

import java.awt.EventQueue;

/**
 * This class runs the powerPaint program.
 * 
 * @author Trung Dang
 * @version November 4, 2014
 */
public final class PowerPaintMain {

    /**
     * A private constructor to disallow extension of this class.
     */
    private PowerPaintMain() {
        //Do nothing
    }

    /**
     * Construct a new powerPaintGUI in a thread safe invocation.
     * 
     * @param theArgs command line argument
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGUI();   
            }
        });
    }
}
