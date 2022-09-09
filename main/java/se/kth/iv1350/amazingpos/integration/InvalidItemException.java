package se.kth.iv1350.amazingpos.integration;


public class InvalidItemException extends Exception {
    /**
     * Creates a new instance of InvalidItemIdException.
     * @param itemId represents the item id.
     */
    public InvalidItemException(int itemID){
        super("The item with id: " + itemID + " can't be found.");
    }

    public int getIncorrectID() {
        return 0;
    }

    public String getAdminMessage() {
        return null;
    }
}
