package se.kth.iv1350.amazingpos.integration;
import se.kth.iv1350.amazingpos.model.Receipt;
/**
* represents the printer
*/
public class Printer {
    public Printer(){
	}
	/**
	* Prints the specified receipt. 
	* @param receipt
	*/
	public void printReceipt(Receipt receipt) {
		System.out.println(receipt.receiptToString());
	}
}

