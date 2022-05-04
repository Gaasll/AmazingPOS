package se.kth.iv1350.amazingpos.integration;
import se.kth.iv1350.amazingpos.DTO.ItemDTO;
import se.kth.iv1350.amazingpos.model.Amount;
/**
* This class represents item catalog
*/
public class ItemCatalog {
    /**
    * This class responsibly for finding seached item
    */
    public ItemDTO findItem(String itemIdentifier) {
        ItemDTO foundItem = new ItemDTO("Cola Zero", new Amount(20, "kr"), 0.05, "123456789");
        return foundItem  ;
    }
    
}
