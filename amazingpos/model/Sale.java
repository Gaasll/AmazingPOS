package se.kth.iv1350.amazingpos.model;
import se.kth.iv1350.amazingpos.DTO.*;
import se.kth.iv1350.amazingpos.integration.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {
    private LocalTime saleTime;

    private Amount totalAmount = new Amount(0,"kr");
	private List<ItemDTO> itemsInCurrentSale = new ArrayList<>();
	private Amount change;

	private SaleDTO saleSpec;
    
    /**
     * Creates a new instance and saves the time of the sale.
     */
    public Sale() {
        saleTime = LocalTime.now();
    }   
	/**
	 * adds an new item to the current sale, updates the total aount including VAT
	 * @param itemInfo contains information about an item
	 * @param quantity the number of items to be added
	 * @return  information about current sale
	 */
    public SaleDTO addItem(ItemDTO itemSpec) {
		this.itemsInCurrentSale.add(itemSpec);
		int quantity = itemSpec.getItemQuantity();
		updateRunningTotal(itemSpec, quantity);
		saleSpec = new SaleDTO (this.totalAmount, this.itemsInCurrentSale, this.change);
		return saleSpec;
	}
	private void updateRunningTotal(ItemDTO itemSpec, int quantity) {
		Amount priceAfterVat = this.countItemPriceIncludinVAT(itemSpec);
		int amountOfPriceAfterVat = priceAfterVat.getAmount();
		int amountToUpdateRunningTotal = quantity *amountOfPriceAfterVat;
		this.totalAmount.addAmount(amountToUpdateRunningTotal);
	}
	private Amount countItemPriceIncludinVAT(ItemDTO itemSpec) {
		Amount priceOfItem = itemSpec.getPrice();
		int amountOfPrice = priceOfItem.getAmount();
		double itemVAT = itemSpec.getItemVAT();
		double priceIncludingVAT = amountOfPrice +(amountOfPrice * itemVAT);
		int roundedPriceAfterVat = (int) Math.round(priceIncludingVAT);
		return new Amount(roundedPriceAfterVat,"kr");
	}

	/**
	 * calculates the change amount to return to a customer.
	 * @param amountPaid by the customer
	 * @return returns the change amount
	 */
	public Amount countPayment(Amount amountPaid) {
		int amountInChange= amountPaid.substructAmount(this.totalAmount);
		change = new Amount(amountInChange, "kr");
		saleSpec = new SaleDTO(this.totalAmount, this.itemsInCurrentSale, this.change);
		return change;
	}
	
	/**
	* Prints a receipt for the current sale 
	*/
	public void printReceipt(Printer printer) {
		Receipt receipt = new Receipt(saleSpec);
		printer.printReceipt(receipt);
	}
    
    
}
