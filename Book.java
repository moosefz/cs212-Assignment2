/** Class Book is an Item (extends Item) that implements the Comparable and Serializable interfaces. Book consists of
 * an author, title, and year.
 *
 * @author: Mustafa Fawaz
 * @StudentId: 103184737
 * @Version: 1
 * @Date: 11/18/2019
 */

import java.io.Serializable;

public class Book extends Item implements Comparable<Book>, Serializable {

    private String author;
    private String title;
    private int year;

    //Default Constructor for Book
    public Book() {
        //Every item must set the price and quantity
        super("Book");
    }

    /**
     * Display is a method that writes the formatted object to the console.
     */
    public void Display() {
        System.out.printf("[%d] || %s -- \'%s\', by %s. %d. Price: %.2f, Quantity: %d.\n", getItem_id(),
                getTitle(), title, author, year, getPrice(), getQuantity());
    }

    /**
     * Purchase is a method that checks the quantity of an item. If the quantity is greater than 1, it decrements
     * the quantity. Otherwise, the item is deleted.
     * @return 1 if decremented, 0 if to be removed.
     */
    public int Purchase() {
        if (getQuantity() > 1) {
            decQuantity();
            System.out.println("Item purchased. Quantity updated.\n");
            return 1;
        } else
            System.out.println("Item purchased. Removed from inventory.");
            return 0;
    }


    /**
     * Setters for Book consisting of setAuthor, setBookTitle and setYear
     * @param author, title, year
     * @return 1 if set, 0 otherwise.
     */
    //Setter for book author
    public int setAuthor(String author) {
        if(!author.isEmpty()) {
            this.author = author;
            return 1;
        } return 0;
    }

    //Setter for book title
    public int setBookTitle(String title) {
        if(!title.isEmpty()) {
            this.title = title;
            return 1;
        } return 0;
    }

    //Setter for book year
    public int setYear(int year) {
        if(isValidYear(year)) {
            this.year = year;
            return 1;
        } return 0;
    }

    /**
     * getAuthor returns the author of the book
     * @return String author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * isValidYear is a validator that checks if the year is valid.
     * @param year
     * @return true if successful, false otherwise.
     */
    private boolean isValidYear(int year) {
        if(year >= 0)
            return true;
        return false;
    }

    public String toString() {
        return String.format("[%d] || %s -- \'%s\', by %s. %d. Price: %.2f, Quantity: %d.\n", getItem_id(),
                getTitle(), title, author, year, getPrice(), getQuantity());
    }

    /**
     * compareTo compares Books based on Author. Used for sorting. Required for the Comparable interface.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Book o) {
        return this.author.compareTo(o.getAuthor());
    }
}
