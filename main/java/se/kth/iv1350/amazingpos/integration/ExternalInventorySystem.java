package se.kth.iv1350.amazingpos.integration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import se.kth.iv1350.amazingpos.DTO.ItemDTO;
import se.kth.iv1350.amazingpos.model.Item;

/**
*
* represent external accountiing system
*/
public class ExternalInventorySystem {
    private static ExternalInventorySystem instance = null;
    private static List<Item> currentInventory = new ArrayList<>();
    

   

    /**
     * Looks if an item has more than quantity in stock
     * @param id item to look for
     * @param quantity amount of item to look for
     * @return true if enough items exist
     */
    public boolean inStock(int id, int quantity) {
        if (currentInventory.size() >= id) {

            Item it = currentInventory.get(id);
            if (it.getQuantity() > 0)
                return true;
        }

        return false;
    
    }




    public static ExternalInventorySystem getInstance() {
         
         try {
            Scanner scnr = new Scanner(new File("src/main/se/kth/salessystem/integration/ids.txt"));
            String[] temp;
            Item item;
            int line = 0;
            while (scnr.hasNextLine()) {
                temp = scnr.nextLine().split("#");
                item = new Item(Integer.parseInt(temp[3]), Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), temp[2], line);
                currentInventory.add(item); 
                line++;
            }
        } catch (FileNotFoundException e) { 
            System.out.println("Error. File not found. ");
            e.printStackTrace();
        }
        return instance;
    }




    public Item getItem(int id) throws InvalidItemException, DatabaseException{
        if(id == 100) throw new DatabaseException();
        
        for (Item item : currentInventory) {
			if (item.getItemIdentifier() == id) {
					return item;
			}
		}
		throw new InvalidItemException("Identifier: " + id + ", Is not valid!");
    }


    
}
