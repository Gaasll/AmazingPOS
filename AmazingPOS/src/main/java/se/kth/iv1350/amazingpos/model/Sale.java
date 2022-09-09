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
	private List<Item> itemsInCurrentSale = new ArrayList<>();
	private Amount change;
     private double totalPrice;
	private SaleDTO saleSpec;

    private List<Observer> Observers = new ArrayList<>();
    
    /**
     * Creates a new instance and saves the time of the sale.
     */
    public Sale() {
        saleTime = LocalTime.now();
    }  

	 /**
     * adds an new item to the current sale
     * @param item item to add.
     * @param invSys ext to check in stock
     * @return true if the item exists in inventorysys 
     */
	boolean addItem(Item item, ExternalInventorySystem invSys) { 
        boolean addedItemSuccesfully = false;
        if (invSys.inStock(item.itemID)) {
            for (Item i :
                    itemsInCurrentSale) {
                if (i.itemID == item.itemID) {
                    i.quantity += item.quantity;
                    addedItemSuccesfully = true;
                }
            }
            if (!addedItemSuccesfully) {
                itemsInCurrentSale.add(item);
            }
            totalPrice += item.itemPrice * item.quantity;
            return true;
        }
        return false;


    }

	/**
     * Adds multiple items of one type to the sale.
     * @param itemID itemIdentfier.
     * @param quantity amount of items to add(ex: 6 oranges)
     * @param ext   InventorySystem to check stock for
     * @return true if all items exist inStock.
     */
    public boolean addItemMult(int itemID, int quantity, ExternalInventorySystem invSys) {
        Item temp = null;
        try{
            temp = invSys.getItem(itemID);}
        catch (DatabaseException e) {
            throw new RuntimeException(e);
        } catch (InvalidItemException e) {
            throw new RuntimeException(e);
        }
        boolean addItemWasSuccess = false;
        if (invSys.inStock(itemID, quantity) && temp != null) {
            for (int i = 0; i < quantity; i++) {
                addItemWasSuccess = this.addItem(temp, invSys);
            }
        }
        return addItemWasSuccess;
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

    public SaleDTO endSale(String string, String string2) {
        return null;
    }

	public Object getLastItemNameInList() {
		return null;
	}

    public static Object getTotalPriceAndTaxForEntirePurchase() {
        return null;
    }

    public static Object getTotalPriceAndTax() {
        return null;
    }

    public Object getItemInfo() {
        return null;
    }

        
    private void notifyObservers(){
        for(Observer obs : Observers){
            obs.newSale(this.totalPrice);
        }
    }
        
    /**
     * Observer will be notified when a new sale has been made.
     * @param obs Is the variable for the observer to notify. 
     */
    public void addSaleObserver(Observer obs){
        Observers.add(obs);
    }

    public Sale getSaleInformation() {
        return null;
    }
    
    
}
