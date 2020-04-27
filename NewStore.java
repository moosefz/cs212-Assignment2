/**
 * @author: Mustafa Fawaz
 * @StudentId: 103184737
 * @Version: 1
 * @Date: 11/18/2019
 */

/**
 *  NewStore class instantiates a StoreFront object, and calls the openStore method of StoreFront. This prompts
 *  the beginning of the inventory system.
 */


public class NewStore {
    public static void main(String[] args) {
        StoreFront s1 = new StoreFront();
        s1.openStore();
    }
}
