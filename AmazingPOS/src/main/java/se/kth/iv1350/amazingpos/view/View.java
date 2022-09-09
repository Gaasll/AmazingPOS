package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.DTO.*;
import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.integration.*;
import se.kth.iv1350.amazingpos.model.*;

/**
 * This is a placeholder for the real view. It contains a hardcoded calls to the controller.
 * 
 */
public class View {
    private Controller contr;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFiileOutput());
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     * @throws DatabaseException
     * @throws InvalidItemException
     */
    public void runFakeExecution() throws InvalidItemException, DatabaseException {
        contr.startSale();
        System.out.println("A new sale has been started.");

        try{
            String itemID = "123456789";
        SaleDTO saleSpec = contr.addItem(itemID, 4);
        int value = saleSpec.getTotalAmount().getAmount();
        System.out.println("The item was added successfully. \n ");
        } catch(InvalidItemException exception){
            System.err.println("Invalid item identifier!");
        } catch(DatabaseException exception){
            System.err.println("Database seems to be offline! Please contact support : 0707415041");
        }

    
        System.out.println("The total amount to pay is: " + totalAmount + " kr. "); 
        Amount change = contr.pay(new Amount(320, "kr"));
        System.out.println("Payment approved.\n");
        System.out.println("Return " + change + " to customer.\n");


    }
}
