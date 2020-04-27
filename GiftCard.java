/** Class GiftCard is an Item (extends Item) that implements the Comparable and Serializable interfaces. Consists of a
 * label and manufacturer.
 *
 * @author: Mustafa Fawaz
 * @StudentId: 103184737
 * @Version: 1
 * @Date: 11/18/2019
 */


import java.io.Serializable;

public class GiftCard extends Item implements Comparable<GiftCard>, Serializable {

    private String label;
    private String manufacturer;

    public GiftCard() {
        //Every item must set the price and quantity
        super("Gift Card");
    }

    /**
     * Setters for GiftCard, include setManufacturer, setLabel
     * @param manufacturer, label
     * @return 1 if set, 0 otherwise.
     */
    public int setManufacturer(String manufacturer) {
        if (!manufacturer.isEmpty()) {
            this.manufacturer = manufacturer;
            return 1;
        } return 0;
    }

    public int setLabel(String label) {
        if(!label.isEmpty()) {
            this.label = label;
            return 1;
        } return 0;
    }

    /**
     * getLabel returns the label of the GiftCard
     * @return String label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Display is a method that writes the formatted object to the console.
     */
    public void Display() {
        System.out.printf("[%d] || %s -- %s : %s -- Value: %.2f -- Quantity: %d\n", getItem_id(),
                getTitle(), label, manufacturer, getPrice(), getQuantity());
    }

    /**
     * Purchase is a method that checks the quantity of an item. If the quantity is greater than 1, it decrements
     * the quantity. Otherwise, the item is deleted.
     * @return 1 if decremented, 0 if to be removed.
     */
    public int Purchase() {
        if(getQuantity() > 1) {
            decQuantity();
            System.out.println("Item purchased. Quantity updated.\n");
            return 1;
        } else
            System.out.println("Item purchased. Removed from inventory.");
            return 0;
    }

    //toString method
    public String toString() {
        return String.format("[%d] || %s -- %s, %s. Value: %.2f, Quantity: %d.\n", getItem_id(),
                getTitle(), label, manufacturer, getPrice(), getQuantity());
    }

    /**
     * compareTo compares GiftCards based on Label. Used for sorting. Required for the Comparable interface.
     * @param o
     * @return
     */
    @Override
    public int compareTo(GiftCard o) {
        return this.label.compareTo(o.getLabel());
    }

}
