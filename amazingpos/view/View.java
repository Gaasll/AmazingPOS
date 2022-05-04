package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.DTO.*;
import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.model.Receipt;

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
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     */
    public void runFakeExecution() {
        contr.startSale();
        System.out.println("A new sale has been started.");
        String itemID = "123456789";
        SaleDTO saleSpec = contr.addItem(itemID, 4);
        int value = saleSpec.getTotalAmount().getAmount();
        System.out.println("The item was added successfully. \n ");
	    System.out.println("The total amount to pay is: " + value + " kr. "); 
        Amount change = contr.pay(new Amount(320, "kr"));
        System.out.println("Payment approved.\n");
        System.out.println("Return " + change + " to customer.\n");


    }
}
