/*
 * TCSS 305 – Autumn 2014 
 * Assignment 2 - ShoppingCart
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * This class stores data including name, price, bulk quantity, and bulk price
 * of a single item.
 * 
 * @author Trung Dang
 * @version 11 October, 2014
 */
public final class Item {

    /** The name of a single item. */
    private final String myItemName;

    /** The price of that single item. */
    private final BigDecimal myItemPrice;

    /** The bulk quantity of that item to be eligible for bulk price. */
    private final int myBulkQuantity;

    /** The bulk price of that item. */
    private final BigDecimal myBulkPrice;

    /**
     * This constructor initializes the state of the item object.
     * 
     * @param theName the name of one single item
     * @param thePrice the price of that single item
     */
    public Item(final String theName, final BigDecimal thePrice) {
        myItemName = theName;
        myItemPrice = thePrice;
        myBulkQuantity = 0;
        myBulkPrice = BigDecimal.valueOf(0.00);
    }

    /**
     * This constructor initializes the state of the item object with a bulk
     * order.
     * 
     * @param theName the name of one single item
     * @param thePrice the price of that single item
     * @param theBulkQuantity the bulk quantity to be eligible for bulk price
     * @param theBulkPrice the bulk price of the item
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        myItemName = theName;
        myItemPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
    }

    /**
     * This method calculates the cost of a single item.
     * 
     * @param theQuantity the total quantity a customer orders
     * @return the total price for this item's order
     */
    public BigDecimal calculateItemTotal(final int theQuantity) {
        BigDecimal total = BigDecimal.valueOf(0.00);
        if (myBulkQuantity > 0) {
            if (theQuantity >= myBulkQuantity) {
                final BigDecimal bulkTotal =
                                myBulkPrice.multiply(BigDecimal.valueOf(theQuantity
                                                                        / myBulkQuantity));
                final BigDecimal leftOvers =
                                myItemPrice.multiply(BigDecimal.valueOf(theQuantity
                                                                        % myBulkQuantity));
                total = total.add(bulkTotal.add(leftOvers));
            } else {
                total = myItemPrice.multiply(BigDecimal.valueOf(theQuantity));
            }
        } else {
            total = myItemPrice.multiply(BigDecimal.valueOf(theQuantity));
        }
        return total;
    }

    /**
     * {@inheritDoc}
     * 
     * @return a string with format as follows: <br>
     *         myItemName, myItemPrice (BulkQuantity for BulkPrice).
     */
    @Override
    public String toString() {
        final NumberFormat nF = NumberFormat.getCurrencyInstance();
        final StringBuilder sB = new StringBuilder();
        sB.append(myItemName);
        sB.append(", ");
        sB.append(nF.format(myItemPrice));
        if (myBulkQuantity > 0 && myBulkPrice.compareTo(BigDecimal.valueOf(0.00)) > 0) {
            sB.append(" (");
            sB.append(myBulkQuantity);
            sB.append(" for ");
            sB.append(nF.format(myBulkPrice));
            sB.append(')');
        }
        return sB.toString();
    }

    /**
     * Return true if the two items have identical name, price, bulk quantity
     * and bulk price.
     * 
     * @param theOther the object passed to the method for comparison
     * @return result true when items have the same name, price, bulk quantity,
     *         and bulk price. If they don't have bulk quantity nor bulk price,
     *         return true when they have identical name and price.
     */
    @Override
    public boolean equals(final Object theOther) {
        boolean result = false;
        if (theOther != null && theOther.getClass() == getClass()) {
            final Item otherItem = (Item) theOther;
            if (myBulkQuantity > 0) {
                result = checkEquals(myItemName, myItemPrice, 
                                     myBulkQuantity, myBulkPrice, otherItem);
            } else if (myItemName.equals(otherItem.myItemName)
                            && (myItemPrice.compareTo(otherItem.myItemPrice) == 0)) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * This method returns the hashCode value of this Item object.
     * 
     * @return the hashCode value of this item object
     */
    @Override
    public int hashCode() {
        return Objects.hash(myItemName, myItemPrice, myBulkQuantity, myBulkPrice);
    }

    /**
     * This method helps the equals method to compare two Items.
     * 
     * @param theName the name of this Item
     * @param thePrice  the price of this Item
     * @param theBulkQuantity the bulk quantity of this item
     * @param theBulkPrice the bulk price of this item
     * @param theOtherItem  the Item to be compared
     * @return true if two items have the same name, price, bulk quantity, and
     *         bulk price
     */
    private boolean checkEquals(final String theName, final BigDecimal thePrice,
                                final int theBulkQuantity,
                                final BigDecimal theBulkPrice, final Item theOtherItem) {
        boolean result = false;
        if (theName.equals(theOtherItem.myItemName)
                        && (thePrice.compareTo(theOtherItem.myItemPrice) == 0)
                        && (theBulkQuantity == theOtherItem.myBulkQuantity)
                        && theBulkPrice.compareTo(theOtherItem.myBulkPrice) == 0) {
            result = true;
        }
        return result;
    }
}
