/*
 * TCSS 305 Autumn 2014 Assignment 2 - Shopping Cart
 */

package view;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import model.Item;

/**
 * ShoppingMain provides the main method for a simple shopping cart GUI
 * displayer and calculator.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman (Formatting and Comments)
 * @author Alan Fowler (Numerous changes including use of BigDecimal and file input)
 * @version Autumn 2014
 */

public final class ShoppingMain {
    
    /**
     * The filename of the file containing the items to display in the cart.
     */
    private static final String ITEM_FILE = "items.txt";

    /**
     * A private constructor, to prevent instances from being constructed.
     */
    private ShoppingMain() {
    }

    /**
     * Reads item information from a file and returns an array of Item objects.
     * 
     * @return and array of Item objects created from data in an input file
     */
    private List<Item> readItemsFromFile() {
        final List<Item> items = new LinkedList<>();
        Scanner input = null;
        
        try {
            input = new Scanner(new File(ITEM_FILE));
        } catch (final IOException ioe) {
            input = new Scanner(getClass().getResourceAsStream(ITEM_FILE));
        }
        
        // Note: some prices from Amazon.com in October 2014 are used
        while (input.hasNextLine()) {
            final Scanner line = new Scanner(input.nextLine());
            line.useDelimiter(";");
            final String itemName = line.next();
            final BigDecimal itemPrice = line.nextBigDecimal();
            if (line.hasNext()) {
                final int bulkQuantity = line.nextInt();
                final BigDecimal bulkPrice = line.nextBigDecimal();
                items.add(new Item(itemName, itemPrice, bulkQuantity, bulkPrice));
            } else {
                items.add(new Item(itemName, itemPrice));
            }
            line.close();
        }
        input.close();
    
        return items;
    }

    /**
     * The main() method - displays and runs the shopping cart GUI.
     * 
     * @param theArgs Command line arguments, ignored by this program.
     */
    public static void main(final String... theArgs) {
        final ShoppingMain sm = new ShoppingMain(); 
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShoppingFrame(sm.readItemsFromFile());     
            }
        });
    } // end main()

} // end class ShoppingMain
