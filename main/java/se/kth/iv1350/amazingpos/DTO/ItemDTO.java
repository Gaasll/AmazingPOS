package se.kth.iv1350.amazingpos.DTO;

import se.kth.iv1350.amazingpos.model.Amount;

/**
 *contains information about one item
 */
public class ItemDTO {
	private String itemName ;
	private Amount itemPrice;
	private double itemVAT;
	public String itemID;
	private int itemQuantity;
	
	/**
	 * creates a new instans representing a new item; 
	 * @param  name      		the name of an item
	 * @param  price     		the price of an item
	 * @param  vatRate 	 		the rate of VAT of an item
	 * @param  itemID 	        the identification code of an item  
	 */
	public ItemDTO(String name, Amount price, double vatRate, String itemIdentifier) {
		this.itemName = name;
		this.itemPrice = price;
		this.itemVAT = vatRate;
		this.itemID = itemIdentifier;
	}
	
	/**
	 * returns the name of an item 
	 *@return the name of an item  
	 */
	public String getName() {
		return this.itemName;
	}
	
	/**
	 * returns the price of an item 
	 *@return the name of an item  
	 */
	public Amount getPrice() {
		return this.itemPrice;
	}
	
	/**
	 * returns the VAT Rate of an item 
	 *@return the VAT rate of an item  
	 */
	public double getItemVAT() {
		return this.itemVAT;
	}
	
	/**
	 * returns the identification of an item 
	 *@return the identification of an item  
	 */
	public String  getItemID() {
		return this.itemID;
	}
	/**
	 * sets the number of items that should be added to a sale
	 * @param itemQuantity the number of items to be added
	 */
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public int getItemQuantity() {
		return this.itemQuantity;
	}
}
