package se.kth.iv1350.amazingpos.integration;
/**
* creates registry for lists
*/
public class RegistryCreator {
 /**
	 * Creates an instance of Registry Creator 
	 */
	public RegistryCreator() {
		
	}
	
	public ItemCatalog getItemCatalog() {
		return new ItemCatalog();
	}
	public CustomerList getCustomerList() {
		return new CustomerList();
	}   
}
