/** StoreFront class is a class which allows a user to add items to inventory setting required attributes, displaying
 *  all items in the cart and displaying specific items sorted by varying parameters.
 *
 * @author: Mustafa Fawaz
 * @StudentId: 103184737
 * @Version: 1
 * @Date: 11/18/2019
 */

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class StoreFront {

    //LinkedList to store items
    LinkedList<Item> invList = new LinkedList<>();
    String employeeName;

    //# of elements in linkedList once loaded. Used to update itemID counter
    int invSize = loadFromFile();

    //Default Constructor
    public StoreFront() {
        employeeName = "Moose";
    }

    /**
     * openStore is a method which calls upon all other methods in the StoreFront class to drive the operation of the
     * inventory application via switch case. Includes navigation menu and ability to work with items.
     */
    public void openStore() {

        Scanner sc = new Scanner(System.in);

        //Update itemId counter based on items loaded in to continue.
        if(Item.getCounter() == 0)
            Item.setCounter(invSize);

        //Menu Parameters
        int mainSelect = 0;

        //Main Menu
        while(mainSelect != 8) {
            System.out.println("Java Storefront -- Main Menu: ");
            //If selection is 8, end.
            mainSelect = mainMenu();

            switch (mainSelect) {

                //Add new item to inventory
                case 1: addInventoryMenu(); break;

                //Display all items
                case 2:
                    System.out.println("Inventory:");
                    displayItems(); break;

                //Display books sorted by author name
                case 3: booksByAuthor(); break;

                //Display gifts sorted by Label
                case 4: giftsByLabel(); break;

                //Display shoes sorted by Size
                case 5: shoesBySize(); break;

                //Delete an item by item_id
                case 6: deleteItem(selectItem()); break;

                //Purchase an item by removing from inventory
                case 7: purchaseItem(selectItem()); break;

               //Exit Case
                case 8: System.out.println("Logging off..."); break;
                default: System.out.println("Invalid selection. Please select a valid menu item.");
           }
        }
    }

    //Used for String capture
    public String getInput() {
        String input;
        Scanner s2 = new Scanner(System.in);
        return input = s2.nextLine();
    }

    /**
     * mainMenu is a method which displays the main menu to the user and receives an integer input from the user as
     * menu selection.
     * @return the integer related to the menu item selected
     */
    public int mainMenu() {

        Scanner sc = new Scanner(System.in);
        int selection;

        System.out.print("1. Add Item to Inventory \n" +
                "2. Display All Items \n" +
                "3. Display only Books sorted by Author Name \n" +
                "4. Display only Gifts sorted by Label \n" +
                "5. Display only Shoes sorted by size \n" +
                "6. Delete an item by item_id \n" +
                "7. Purchase an item by removing the purchased quantity from the inventory \n" +
                "8. Exit \n"
        );
        return selection = sc.nextInt();
    }

    /**
     * displayItems is a method that displays the contents of the linkedlist to the console.
     * If the linkedlist is empty, the user is notified.
     */
    public void displayItems() {
        if(invList.isEmpty()) {
            System.out.println("Inventory list is empty.\n");
        } else {
            for (Item temp : invList) {
                System.out.print(temp);
            }
            System.out.println();
        }
    }

    /**
     * addInventoryMenu is a method that presents a sub-menu to the user based off the selection "Add item to Inventory"
     * Selection is inputted by the user as an integer and depending on the selection, the relevant method is called.
     */
    public void addInventoryMenu() {
        int itemSelect;
        Scanner sc = new Scanner(System.in);

        //Submenu
        System.out.println("Select item type: ");
        System.out.print("1. Book \n" + "2. Gift Card \n" + "3. Shoe \n");
        itemSelect = sc.nextInt();

        //Adding Book to inventory - request Book information from user
        if(itemSelect == 1) addBook();
            //Adding Gift Card to Inventory - request gift card information from user
        else if (itemSelect == 2) addGiftCard();
            //Adding Shoe to Inventory - request Shoe information from user
        else if (itemSelect == 3) addShoe();
        //Save linkedList to file
        saveToFile();
    }

    /**
     * addBook is a method which instantiates a Book. Requests various inputs from the user for the attributes of the
     * Book class. Once all inputs are gathered, helper methods in the Book class are used to set the attributes and
     * the item is added to the invList linkedlist.
     */
    private void addBook() {
        //New book object
        Scanner sc = new Scanner(System.in);
        Book bk = new Book();

        System.out.print("Book Title: ");
        while(bk.setBookTitle(getInput()) != 1) {
            System.out.print("Not a valid title. Try again: ");
        }
        System.out.print("Author: ");
        while(bk.setAuthor(getInput()) != 1) {
            System.out.print("Not a valid author. Try again: ");
        }
        System.out.print("Year: ");
        while(bk.setYear(sc.nextInt()) != 1) {
            System.out.print("Not a valid year. Try again: ");
        }
        System.out.print("Price: ");
        while(bk.setPrice(sc.nextDouble()) != 1) {
            System.out.print("Not a valid price. Try again: ");
        }
        System.out.print("Quantity: ");
        while(bk.setQuantity(sc.nextInt()) != 1) {
            System.out.print("Not a valid quantity. Try again: ");
        }

        //Add to list
        invList.add(bk);
    }

    /**
     * addGiftCard is a method which instantiates a GiftCard. Requests various inputs from the user for the attributes
     * of the GiftCard class. Once all inputs are gathered, helper methods in the GiftCard class are used to set the
     * attributes and the item is added to the invList linkedlist.
     */
    private void addGiftCard() {
        //New gift card object
        Scanner sc = new Scanner(System.in);
        GiftCard gc = new GiftCard();

        System.out.print("Label: ");
        while(gc.setLabel(getInput()) != 1) {
            System.out.println("Not a valid label. Try again: ");
        }
        System.out.print("Manufacturer: ");
        while(gc.setManufacturer(getInput()) != 1){
            System.out.print("Not a valid Manufacturer. Try again: ");
        }
        System.out.print("Value: ");
        while(gc.setPrice(sc.nextDouble()) != 1) {
            System.out.print("Not a valid value. Try again: ");
        }
        System.out.print("Quantity: ");
        while(gc.setQuantity(sc.nextInt()) != 1) {
            System.out.print("Not a valid quantity. Try again: ");
        }
        //Add gift card to list
        invList.add(gc);
    }

    /**
     * addBook is a method which instantiates a Shoe. Requests various inputs from the user for the attributes of the
     * Shoe class. Once all inputs are gathered, helper methods in the Shoe class are used to set the attributes and
     * the item is added to the invList linkedlist.
     */
    private void addShoe() {
        //New shoe object
        Scanner sc = new Scanner(System.in);
        Shoe sh = new Shoe();

        System.out.print("Colour: ");
        while(sh.setColour(getInput()) != 1) {
            System.out.print("Colour not found. Try again: ");
        }
        System.out.print("Size: ");
        while(sh.setSize(sc.nextDouble()) != 1) {
            System.out.print("Size not available. Try again: ");
        }
        System.out.print("Price: ");
        while(sh.setPrice(sc.nextDouble()) != 1) {
            System.out.print("Not a valid price. Try again: ");
        }
        System.out.print("Quantity: ");
        while(sh.setQuantity(sc.nextInt()) != 1) {
            System.out.print("Not a valid quantity. Try again: ");
        }
        //Add to LinkedList
        invList.add(sh);
    }

    /**
     * booksByAuthor is a method that checks the invList linkedlist for Book objects and copies them to an array.
     * Array is sorted by the Book attribute "Author" and displayed to the user.
     */
    private void booksByAuthor() {
        //Convert invList to array
        Item[] itemsB = invList.toArray(new Item[invList.size()]);
        Item[] books = new Book[itemsB.length];
        int bookCount = 0;
        //Copy only books from array
        for(int i = 0; i < itemsB.length; i++) {
            if(itemsB[i].getClass().equals(Book.class)) {
                books[bookCount] = itemsB[i];
                bookCount++;
            }
        }
        if(bookCount > 0) {
            //Sort Books
            Arrays.sort(books, 0, bookCount);
            //Display all Books
            System.out.println("\nAll books sorted by Author: ");
            //Display all shoes
            for (int j = 0; j < bookCount; j++) {
                books[j].Display();
            }
        } else System.out.println("\nNo Books in inventory.");
        System.out.println();
    }

    /**
     * shoesBySize is a method that checks the invList linkedList for Shoe objects and copies them to an array.
     * Array is sorted by the Shoe attribute "Size" and displayed to the user.
     */
    private void shoesBySize() {
        Item[] items = invList.toArray(new Item[invList.size()]);
        Item[] shoes = new Shoe[items.length];
        int shoeCount = 0;
        //Copy only shoes from array
        for(int i = 0; i < items.length; i++) {
            if(items[i].getClass().equals(Shoe.class)) {
                shoes[shoeCount] = items[i];
                shoeCount++;
            }
        }
        if(shoeCount > 0) {
            //Sort Shoes
            Arrays.sort(shoes, 0, shoeCount);
            //Display all shoes
            System.out.println("\nAll Shoes sorted by size: ");
            for (int j = 0; j < shoeCount; j++) {
                shoes[j].Display();
            }
        } else System.out.println("\nNo Shoes in inventory.");
        System.out.println();
    }

    /**
     * giftsByLabel is a method that checks the invList linkedList for GiftCard objects and copies them to an array.
     * Array is sorted by the GiftCard attribute "Label" and displayed to the user.
     */
    private void giftsByLabel() {
        Item[] itemsG = invList.toArray(new Item[invList.size()]);
        Item[] gifts = new GiftCard[itemsG.length];
        int giftCount = 0;
        //Copy only gift cards from array
        for(int i = 0; i < itemsG.length; i++) {
            if(itemsG[i].getClass().equals(GiftCard.class)) {
                gifts[giftCount] = itemsG[i];
                giftCount++;
            }
        }
        //Check if giftCards exist
        if(giftCount > 0) {
            //Sort Shoes
            Arrays.sort(gifts, 0, giftCount);
            //Display all Gift Cards
            System.out.println("\nAll Gift Cards sorted by Label: ");
            //Display all shoes
            for (int j = 0; j < giftCount; j++) {
                gifts[j].Display();
            }
        } else System.out.println("\nNo Gift Cards in inventory.");
        System.out.println();
    }

    /**
     * selectItem is a method that displays the inventory list to a user.
     * @return selected item from the list (itemId)
     */
    public int selectItem() {
        Scanner sc = new Scanner(System.in);

        if (invList.isEmpty()) {
            System.out.println("\nInventory list is empty.\n");
            return -1;
        } else
            System.out.println("Select an item from the list below by [item id]: ");
            displayItems();
            System.out.print("\nItem ID: ");
            //Search key
            return sc.nextInt();
    }

    /**
     * deleteItem is a method that deletes a specific item from the invList linkedList based on user selected
     * itemId (key)
     * @param key
     */
    private void deleteItem(int key) {

            if(key == -1) return;

            int index = 0;
            int count = 0;

            //Parse inventory list for matching item id and return index value if found. For loop stops once found.
            for(Item temp : invList) {
                if(temp.getItem_id() == key) {
                    index = invList.indexOf(temp);
                    ++count;
                    break;
                }
            }
            //Remove index position in the linkedList if found, otherwise, item does not exist
            if(count == 1) {
                invList.remove(index);
                System.out.println("\nItem Removed.");
                displayItems();
                //Update save file
                saveToFile();
            } else if(count == 0) {
                System.out.println("Item ID does not exist in inventory\n");
            }
    }

    /**
     * purchaseItem is a method that will decrement an items quantity based on user selection (itemID - key).
     * This calls the method Purchase() from each class.
     * @param key
     */
    public void purchaseItem(int key) {

        if(key == -1) return;

        //Method parameters
        int index = 0;
        int count = 0;

        //Parse inventory list for matching item id and return index value if found. For loop stops once found.
        for (Item temp : invList) {
            if (temp.getItem_id() == key) {
                index = invList.indexOf(temp);
                ++count;
                break;
            }
        }

        //Get the object from the linkedList, check quantity and then remove if q == 1
        if(count == 1 && invList.get(index).Purchase() == 0) {
            invList.remove(index);
            saveToFile();
        } else if(count == 0) {
            System.out.println("Item ID does not exist in inventory");
        } else
            saveToFile();
    }

    /**
     * saveToFile is a method that uses object serialization to save the contents of the invList to a file
     */
    public void saveToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("inventory.ser"));
            oos.writeObject(invList);
            oos.close();
            System.out.println("List saved.\n");
        } catch(IOException ioe){}
    }

    /**
     * loadFromFile is a method that uses object deserialization to load the objects from a file to the invList
     * @return Number of elements read
     */
    public int loadFromFile() {
        LinkedList tempList;
        try {
            //Opening I/O streams
            FileInputStream fis = new FileInputStream("inventory.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            //Checking for object types
            tempList = (LinkedList) ois.readObject();
            for(int i = 0; i < tempList.size(); i++) {
                invList.add(i, (Item)tempList.get(i));
            }
            //Closing I/O streams
            ois.close();
            fis.close();
            System.out.println("Inventory list loaded.");
        } catch(Exception exc) {}

        return(invList.size());
    }

}
