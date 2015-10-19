/*
 * TCSS 305 – Autumn 2014 
 * Assignment 2 - ShoppingCart
 */

package model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This program stores information about customer's orders.
 * 
 * @author Trung Dang
 * @version October 12, 2014
 */
public class ShoppingCart {
    
    /** A field to store all the orders.*/
    private final Set<ItemOrder> myShoppingCart;
    
    /** A field to store customer's membership status.*/
    private boolean myMembership;;

    /**
     * This constructor initializes the ShoppingCart with a new data structure and default
     * membership status (which is false).
     */
    public ShoppingCart() {
        myShoppingCart = new HashSet<ItemOrder>();
        myMembership = false;
    }

    /**
     * This method adds an order into the data structure.
     * 
     * @param theOrder the current ItemOrder being made
     */
    public void add(final ItemOrder theOrder) {
        final Iterator<ItemOrder> itr = myShoppingCart.iterator();
        while (itr.hasNext()) {
            final ItemOrder itemOrder = itr.next();
            if (itemOrder.getItem().equals(theOrder.getItem())) {
                itr.remove();
            }
        }
        myShoppingCart.add(theOrder);
    }

    /**
     * This method sets the membership status into the given value.
     * 
     * @param theMembership the customer's membership status
     */
    public void setMembership(final boolean theMembership) {
        if (theMembership && calculateTotal().compareTo(BigDecimal.valueOf(20.00)) > 0) {
            myMembership = true;
        } else {
            myMembership = false;
        }
    }

    /**
     * This method calculates the total cost of this ShoppingCart which
     * contains multiple orders. 0.9 is the discount factor.
     * 
     * @return the total cost of this ShoppingCart.
     */
    public BigDecimal calculateTotal() {
        final Iterator<ItemOrder> itr = myShoppingCart.iterator();
        BigDecimal result = BigDecimal.valueOf(0.00);
        while (itr.hasNext()) {
            result = result.add(itr.next().calculateOrderTotal());
        }
        if (myMembership) {
            result = result.multiply(BigDecimal.valueOf(0.9));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return myShoppingCart.toString();
    }

}
