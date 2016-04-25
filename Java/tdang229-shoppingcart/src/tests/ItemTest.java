/*
 * TCSS 305 � Autumn 2014 
 * Assignment 2 - ShoppingCart
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;

import model.Item;
import model.ItemOrder;

import org.junit.Before;
import org.junit.Test;

/**
 * This program tests the Item.java class.
 * 
 * @author Trung Dang
 * @version October 12, 2014
 */
public class ItemTest {

    /** A field to store my test item's name. */
    private static final String MY_ITEM = "XBOX 360";

    /** A field to store my test item's price. */
    private static final BigDecimal MY_PRICE = BigDecimal.valueOf(399.00);

    /** A field to store my test item's quantity. */
    private static final int MY_TEST_QUANTITY = 9;

    /** A field to store my test bulk quantity. */
    private static final int MY_TEST_BULK_QUANTITY = 11;

    /** A field to store my test item's bulk quantity. */
    private static final int MY_BULK_QUANTITY = 10;

    /** A field to store my test item's bulk price. */
    private static final BigDecimal MY_BULK_PRICE = BigDecimal.valueOf(3900.00);

    /** A field to store the command to print onto the console. */
    private static final boolean MY_CONSOLE_OUTPUT = false; // Set to true to see console
                                                            // output when running

    /** A field to store my test item. */
    private Item myTestItem;

    /** A field to store my other test item. */
    private Item myOtherTestItem;

    /**
     * This method runs before each test methods to create new items using
     * normal and bulk constructor.
     */
    @Before
    public void setUp() {
        myTestItem = new Item(MY_ITEM, MY_PRICE);
        myOtherTestItem = new Item(MY_ITEM, MY_PRICE, MY_BULK_QUANTITY, MY_BULK_PRICE);
    }

    /**
     * Test method for {@link Item#hashCode()}.
     */
    @Test
    public void testHashCode() {
        final BigDecimal testPrice = BigDecimal.valueOf(399.00);
        final Item testItem = new Item(MY_ITEM, testPrice);
        final int testBulkQuantity = 10;
        final BigDecimal testBulkPrice = BigDecimal.valueOf(3900.00);

        final Item otherTestItem =
                        new Item(MY_ITEM, testPrice, testBulkQuantity, testBulkPrice);
        /*
         * Only prints the test value into the console for reference when
         * consoleOutput is true.
         */
        if (MY_CONSOLE_OUTPUT) {
            System.out.println("Normal constructor: " + myTestItem.hashCode());
            System.out.println("Normal constructor: " + testItem.hashCode());
            System.out.println("Bulk constructor: " + myOtherTestItem.hashCode());
            System.out.println("Bulk constructor: " + otherTestItem.hashCode());
        }
        assertEquals("HashCode() fails for normal constructor", myTestItem.hashCode(),
                     testItem.hashCode());
        assertEquals("HashCode() fails for bulk constructor.", myOtherTestItem.hashCode(),
                     otherTestItem.hashCode());
    }

    /**
     * Test method for {@link Item#calculateItemTotal(integer)}. 
     * 3591: cost of 9 items 
     * 4389: cost of 11 items with bulk price
     */
    @Test
    public void testCalculateItemTotal() {
        final Item itemForTotal = new Item(MY_ITEM, MY_PRICE, MY_BULK_QUANTITY, MY_BULK_PRICE);
        final BigDecimal singlePrice = BigDecimal.valueOf(3591.00);
        final BigDecimal bulkPrice = BigDecimal.valueOf(4299.00);
        if (MY_CONSOLE_OUTPUT) {
            System.out.println(myTestItem.calculateItemTotal(MY_TEST_QUANTITY));
            System.out.println(myOtherTestItem.calculateItemTotal(MY_TEST_BULK_QUANTITY));
        }
        assertEquals("calculateItemTotal(integer) fails for normal item with name and price",
                     myTestItem.calculateItemTotal(MY_TEST_QUANTITY), singlePrice);
        assertEquals("calculateItemTotal(integer) fails for bulk item with name, price, "
                        + "bulk quantity, and bulk price",
                        myOtherTestItem.calculateItemTotal(MY_TEST_BULK_QUANTITY), bulkPrice);

        assertEquals("calculateItemTotal(integer) fails for bulk item with name, price, "
                        + "different bulk quantity, and bulk price",
                        itemForTotal.calculateItemTotal(9), singlePrice);
    }

    /**
     * Test method for {@link Item#toString()}
     */
    @Test
    public void testToString() {
        final Item itemForInvalidParam = new Item(MY_ITEM, MY_PRICE, 0, 
                                                  BigDecimal.valueOf(-1.00));
        final Item itemForInvalidParamA = new Item(MY_ITEM, MY_PRICE, MY_BULK_QUANTITY, 
                                                   BigDecimal.valueOf(-1.00));
        if (MY_CONSOLE_OUTPUT) {
            System.out.println(myTestItem.toString());
            System.out.println(myOtherTestItem.toString());
            System.out.println(itemForInvalidParam.toString());
            System.out.println(itemForInvalidParamA.toString());
        }
        //same item
        assertEquals("toString() fails for normal item", myTestItem.toString(),
                        "XBOX 360, $399.00");
        assertEquals("toString() fails for bulk item", myOtherTestItem.toString(),
                        "XBOX 360, $399.00 (10 for $3,900.00)");

        //0 bulk quantity and less than or equal to 0.00 bulk price
        assertEquals("toString() fails for bulk item with invalid parameter",
                     itemForInvalidParam.toString(), "XBOX 360, $399.00");
        assertEquals("toString() fails for bulk item with invalid bulk price",
                     itemForInvalidParamA.toString(), "XBOX 360, $399.00");
    }

    /**
     * Test method for {@link Item#equals(Object)}. 
     */
    @Test
    public void testEqualsObject() {
        // same name and price
        final Item itemForTestEqual = new Item(MY_ITEM, MY_PRICE);
        assertEquals(myTestItem, itemForTestEqual);

        // same name, price, bulk quantity, bulk price
        final Item itemForTestEqual1 =
                        new Item(MY_ITEM, MY_PRICE, MY_BULK_QUANTITY, MY_BULK_PRICE);
        myOtherTestItem.equals(itemForTestEqual1);
        assertEquals(myOtherTestItem, itemForTestEqual1);

        // null object
        final Item nullItem = null;
        assertFalse(myTestItem.equals(nullItem));
        assertFalse(myOtherTestItem.equals(nullItem));

        // different name
        final Item itemForTestEqual2 =
                        new Item("PlayStation", MY_PRICE, MY_BULK_QUANTITY, MY_BULK_PRICE);
        assertFalse(myOtherTestItem.equals(itemForTestEqual2));

        // different price
        final Item itemForTestEqual3 = new Item(MY_ITEM, BigDecimal.valueOf(0.5));
        final Item itemForTestEqual4 = new Item(MY_ITEM, BigDecimal.valueOf(500.00));
        final Item itemForTestEqual3A = new Item(MY_ITEM, BigDecimal.valueOf(500.00),
                                                 MY_BULK_QUANTITY, MY_BULK_PRICE);
        assertFalse(myTestItem.equals(itemForTestEqual3));
        assertFalse(myTestItem.equals(itemForTestEqual4));
        assertFalse(myOtherTestItem.equals(itemForTestEqual3A));
        //        
        // different bulk quantity
        final Item itemForTestEqual5 = new Item(MY_ITEM, MY_PRICE, 9, MY_BULK_PRICE);
        assertFalse(myOtherTestItem.equals(itemForTestEqual5));
        final Item itemForTestEqual5A = new Item(MY_ITEM, MY_PRICE, -1, MY_BULK_PRICE);
        assertFalse(myOtherTestItem.equals(itemForTestEqual5A));
        final Item itemForTestEqual5C = new Item(MY_ITEM, MY_PRICE, 0, MY_BULK_PRICE);
        assertFalse(myOtherTestItem.equals(itemForTestEqual5C));

        // different bulk price
        final Item itemForTestEqual6 =
                        new Item(MY_ITEM, MY_PRICE, MY_BULK_QUANTITY,
                                 BigDecimal.valueOf(390.00));
        final Item itemForTestEqual6A =
                        new Item(MY_ITEM, MY_PRICE, MY_BULK_QUANTITY,
                                 BigDecimal.valueOf(400.00));
        final Item itemForTestEqual6B =
                        new Item(MY_ITEM, MY_PRICE, MY_BULK_QUANTITY,
                                 BigDecimal.valueOf(0.00));
        final Item itemForTestEqual6C =
                        new Item(MY_ITEM, MY_PRICE, MY_BULK_QUANTITY,
                                 BigDecimal.valueOf(3900.00));
        assertFalse(myOtherTestItem.equals(itemForTestEqual6));
        assertFalse(myOtherTestItem.equals(itemForTestEqual6A));
        assertFalse(myOtherTestItem.equals(itemForTestEqual6B));
        assertEquals(myOtherTestItem, itemForTestEqual6C);

        //different class
        final ItemOrder itemForTestEqual7 = new ItemOrder(myTestItem, 9);
        assertFalse(myTestItem.equals(itemForTestEqual7));

        //different class and null
        final ItemOrder itemForTestEqual8 = null;
        assertFalse(myTestItem.equals(itemForTestEqual8));

        //Different name and price
        final Item itemForTestEqual9 = new Item("PlayStation", BigDecimal.valueOf(300.00));
        assertFalse(myTestItem.equals(itemForTestEqual9));
    }

}
