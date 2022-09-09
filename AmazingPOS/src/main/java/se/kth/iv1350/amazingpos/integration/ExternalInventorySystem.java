package se.kth.iv1350.amazingpos.integration;

import se.kth.iv1350.amazingpos.DTO.ItemDTO;

/**
*
* represent external accountiing system
*/
public class ExternalInventorySystem {
    private static ExternalInventorySystem instance = null;
    

   

    /**
     * Looks if an item has more than quantity in stock
     * @param id item to look for
     * @param quantity amount of item to look for
     * @return true if enough items exist
     */
    public boolean inStock(int id, int quantity) {
        //checks if multiple items of the same ID are in stock, for example 4 bananas
       
        return false;
    }




    public static ExternalInventorySystem getInstance() {
        return null;
    }




    public ItemDTO retrieveItemInformation(int i) {
        return null;
    }




    public void getItem(int i) {
    }




  

    
}
