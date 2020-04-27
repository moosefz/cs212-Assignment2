/** Class Shoe is an Item (extends Item) that implements the Comparable and Serializable interfaces. Shoes consist of a
 * Colour and size.
 *
 * @author: Mustafa Fawaz
 * @StudentId: 103184737
 * @Version: 1
 * @Date: 11/18/2019
 */
import java.io.Serializable;

public class Shoe extends Item implements Comparable<Shoe>, Serializable {

    private String colour; //White, silver, red, beige, brown, blue, black, pink
    public double size; //Shoe size

    //Default Constructor
    public Shoe() {
        super("Shoe");
    }

    /**
     * Setters for Shoes, includes setColour, setSize
     * @param colour, size
     * @return 1 if set, 0 otherwise
     */
    //Set shoe colour
    public int setColour(String colour) {
        if(colour.matches("White|Silver|Red|Beige|Brown|Blue|Black|Pink|white|silver|red|beige|brown|blue|black|pink")) {
            this.colour = colour;
            return 1;
        } return 0;
    }


    //Set shoe size
    public int setSize(double size) {
        if(size >= 0) {
            this.size = size;
            return 1;
        } return 0;
    }

    /**
     * getSize is a getter method for shoe parameter size
     * @return size
     */
    //Getter for object shoe size
    public double getSize() {
        return this.size;
    }

    /**
     * Display is a method that writes the formatted object to the console.
     */
    public void Display() {
        System.out.printf("[%d] || %s -- %s, Size: %.1f -- Price: %.2f -- Quantity: %d\n", getItem_id(),
                getTitle(), colour, size, getPrice(), getQuantity());
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
        } else System.out.println("Item purchased. Removed from inventory.");
            return 0;
    }

    //toString Method
    public String toString() {
        return String.format("[%d] || %s -- Colour: %s, Size: %.1f. Price: %.2f, Quantity: %d.\n", getItem_id(),
                getTitle(), colour, size, getPrice(), getQuantity());
    }

    /**
     * compareTo compares Shoes based on size. Used for sorting. Required for the Comparable interface.
     * @param o
     * @return 1 if greater, -1 if less, 0 if equal.
     */
    @Override
    public int compareTo(Shoe o) {
        if(this.size > o.getSize()) {
            return 1;
        } else if(this.size < o.getSize()) {
            return -1;
        } else
            return 0;
    }
}
