package se.kth.iv1350.amazingpos.controller;

import se.kth.iv1350.amazingpos.integration.InvalidItemException;
//import se.kth.iv1350.amazingpos.model.Receipt;
//import se.kth.iv1350.amazingpos.DTO.CustomerDTO;
import se.kth.iv1350.amazingpos.DTO.ItemDTO;
import se.kth.iv1350.amazingpos.DTO.SaleDTO;
import se.kth.iv1350.amazingpos.integration.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.amazingpos.model.*;
import se.kth.iv1350.amazingpos.view.TotalRevenueFiileOutput;
import se.kth.iv1350.amazingpos.view.TotalRevenueView;
/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private Sale sale;
    private ExternalSystemGenerator extSys;
    private CashRegister cashRegister;
    private RegistryCreator regCreator;
	private List<Observer> Observers = new ArrayList<>();
    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale. 
     */
    public Sale startSale() {
		this.sale = new Sale();
        for(Observer obs : Observers)
            sale.addSaleObserver(obs);
        return sale.getSaleInformation();
    }
    

    public Controller(RegistryCreator regCreator, ExternalSystemGenerator extSys, CashRegister cashRegister) {
        this.cashRegister = cashRegister;
        this.extSys = extSys;
        this.regCreator = regCreator;
    }
   

        public Controller() {
    }

	
        /**
	     * adds an item to the current sale
	     * @param itemID   the identification of an item
	     * @param itemQuantitiy     the quantity of items 
	     * @return returns an object of type sale
	     */

		public SaleDTO addItem(String itemID, int itemQuantity) throws InvalidItemException, DatabaseException {
			try{
				ItemCatalog itemCatalog =  regCreator.getItemCatalog();
	    		ItemDTO itemInfo = itemCatalog.findItem(itemID); 
				SaleDTO currentSale;
				if (itemInfo != null) {
					itemInfo.setItemQuantity(itemQuantity);
					currentSale = this.sale.addItem(itemInfo);	    		
				}
				return currentSale;
			} catch(InvalidItemException | DatabaseException exception){
				System.err.println(" (log): " + exception.getMessage());
				throw exception;
			}
		}
	    
	     
	    /**
	     * Handles sale payment. Updates the cash register 
	     * @param amtPaid the amount paid
	     * @return change the amount of change for customer to recieve
	     */
	    public Amount pay(Amount amtPaid) {
	    	Amount change = sale.countPayment(amtPaid);
	    	Payment payment = new Payment(amtPaid);
	    	cashRegister.Payment(payment);
	    	Printer printer = extSys.getPrinter();
	    	sale.printReceipt(printer);
	    	return change;
	    }

		public void addSaleObserver(TotalRevenueView totalRevenueView){
			Observers.add(totalRevenueView);
		}

        
}
