package se.kth.iv1350.amazingpos.DTO;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.amazingpos.model.Amount;

/**
 * A DTO for sale information 
 */
public class SaleDTO {
	private Amount totalAmount;
	private List<ItemDTO> itemsInCurrentSale = new ArrayList<>();
	private Amount change;
	
	
	/**
	 * creates a new instance representing a new sale; 
	 * @param change 
     * @param  totalAmount   total amount after scanning each item
	 * @param  itemInfo         an instance of itemDTO which is a place holder for information about an item      	  
	 */
	public SaleDTO(Amount totalAmount,List <ItemDTO> itemInfo, Amount change) {
		this.totalAmount = totalAmount;
		this.itemsInCurrentSale = itemInfo;
		this.change = change;
		
	}
	


    /**
	 * returns the running total 
	 *@return the running total after each sale  
	 */
	public Amount getTotalAmount() {
		return this.totalAmount;
	}
	
	public Amount getChange() {
		return this.change;
	}
	
	/**
	 * returns information about an item as an instance of ItemDTO 
	 */
	public List<ItemDTO> getItemInfo() {
		return this.itemsInCurrentSale;
	}
	
	

}