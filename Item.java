/** Item is an abstract class that is the driver for each of the items in this application. Each item has a unique ID,
 * a price, a quantity and a title (name).
 *
 * @author: Mustafa Fawaz
 * @StudentId: 103184737
 * @Version: 1
 * @Date: 11/18/2019
 */


import java.io.Serializable;

public abstract class Item implements Serializable {

    private int item_id; // > 0
    private static int counter = 0; //Used to increment each unique item_id
    private double price; // >= 0
    private int quantity; // >= 0
    private String title; //Cannot be empty

    //Default constructor
    public Item() {
        item_id = ++counter;
        this.title = "Unallocated Item";
    }

    //Overloaded Constructor sets the price, quantity and title for each item
    public Item(String title) {
        this();
        this.title = title;
    }

    /**
     * Abstract Methods - Display() displays the object to console, Purchase() decrements the quantity of an object.
     */
    //Item is displayed to console
    public abstract void Display();
    //Item is removed from inventory
    public abstract int Purchase();

    /**
     *  Helper Methods below
     */

    public static void setCounter(int size) {
        counter = size;
    }

    public static int getCounter() {
        return counter;
    }

    /**
     * setPrice is a method which sets the price of the item. Validated to check if price is >= 0
     * @param price
     * @return 1 if set, 0 otherwise.
     */
    public int setPrice(double price) {
        if(isValidPrice(price)) {
            this.price = price;
            return 1;
        } return 0;
    }

    /**
     * setQuantity is a method which sets the quantity of the item. Validated to check if the quantity is >= 0
     * @param quantity
     * @return 1 if set, 0 otherwise.
     */
    //Set item quantity utilizing validator and return value to confirm quantity set
    public int setQuantity(int quantity) {
        if(isValidQuantity(quantity)) {
            this.quantity = quantity;
            return 1;
        } return 0;
    }

    /**
     * All getter methods below for item attributes
     * @return price (double), quantity (integer), title (String), item_id (integer)
     */
    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getTitle() {
        return this.title;
    }

    public int getItem_id() {
        return this.item_id;
    }

    //Decrement item_id once method is called. Used for purchase() method
    public void decQuantity() {
        --this.quantity;
    }

    /**
     * Validators for item attributes, used with setter methods
     * @param price, quantity
     * @return Boolean true, false
     */
    private boolean isValidPrice(double price) {
        if(price >= 0)
            return true;
        return false;
    }

    //Validtor for checking item quantity
    private boolean isValidQuantity(int quantity) {
        if(quantity >= 0)
            return true;
        return false;
    }
}
