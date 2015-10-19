/*
 * TCSS 305 – Autumn 2014 
 * Assignment 2 - ShoppingCart
 */

package model;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * This program stores information about a customer's order including the name of the item,
 * price, bulk quantity, and bulk price.
 * 
 * @author Trung Dang
 * @version October 12, 2014
 */
public final class ItemOrder {
    
    /** A field to store an item of Item class.*/
    private final Item myItem;
    
    /** A field to store a quantity.*/
    private final int myQuantity;

    /**
     * This constructor initializes the ItemOrder object with an item and a quantity.
     * 
     * @param theItem a single item being ordered
     * @param theQuantity the quantity of that order
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        myItem = theItem;
        myQuantity = theQuantity;
    }

    /**
     * This method calculates the total cost of this item order.
     * 
     * @return the total cost of the order of a single item
     */
    public BigDecimal calculateOrderTotal() {
        return myItem.calculateItemTotal(myQuantity);
    }

    /**
     * This method returns the current Item object in this ItemOrder.
     * 
     * @return the current item being ordered
     */
    public Item getItem() {
        return myItem;
    }

    /**
     * {@inheritDoc}
     * 
     * 
     */
    @Override
    public String toString() {
        final StringBuilder sB = new StringBuilder(30);
        sB.append("Name: ");
        
        //Use this to access the private fields of the Item object. I found this method in the
        //textbook and searched for it on StackOverFlow
        Field f;
        Object name = null;
        try {
            f = myItem.getClass().getDeclaredField("myItemName");
            f.setAccessible(true);
            name = f.get(myItem);
        } catch (final NoSuchFieldException e) {
            e.printStackTrace();
        } catch (final SecurityException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
        
        sB.append(name);
        sB.append("; Quantity: ");
        sB.append(myQuantity);
        return sB.toString();
    }
}
