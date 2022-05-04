package se.kth.iv1350.amazingpos.model;
import se.kth.iv1350.amazingpos.DTO.*;
import java.time.LocalDateTime;
//import java.util.*;

/**
 * Represents one receipt, which proves the payment of one sale.
 */
public class Receipt {
    private SaleDTO saleSpec;
    private int countTotalVAT;
    /**
	* Creates a new instance.
	*
	* @param saleSpec The sale specification 
	*/
    public Receipt (SaleDTO saleSpec){
        this.saleSpec = saleSpec;
    }
    /**
	 * Creates a string with the entire content of the receipt.
	 * @return receipt string formed and ready. 
	 */
    public  String receiptToString (){
        StringBuilder builder = new StringBuilder();
        appendString (builder, "Reciept");
        builder.append("\n");
        LocalDateTime saleTimeAndDate = LocalDateTime.now();
        appendString(builder, "Sale time: " + saleTimeAndDate.toString());
        builder.append("\n");

        for (ItemDTO next : saleSpec.getItemInfo()){
            appendString(builder, "Item: " + next.getName());
            appendString(builder, "Prce: " + next.getPrice().toString());
            appendString(builder, "Quantity: " + next.getItemQuantity());
            builder.append("\n");
            countTotalVAT(next);
        }

        appendString(builder, "Total Price: " + saleSpec.getTotalAmount().toString());
        builder.append("\n");

        appendString(builder, "VAT for Entire Sale: " + new Amount(countTotalVAT, "kr").toString());
        builder.append("\n");
       
        appendString(builder, "Amount paid: " + calculateAmountPaid(saleSpec).toString());
        builder.append("\n");
     
        appendString(builder, "Change: " + saleSpec.getChange().toString());
        builder.append("\n");
        //appendString(builder, "Sale time: " + saleTimeAndDate.toString());
        return builder.toString();


    }

        private Amount calculateAmountPaid(SaleDTO saleSpec) {
            int change = saleSpec.getChange().getAmount();
            int totalAmount = saleSpec.getTotalAmount().getAmount();
            int amountPaid = change + totalAmount;
            return new Amount(amountPaid, "kr");
        }
        
        private double vatForItem(ItemDTO item) {
            Amount priceOfItem = item.getPrice();
               int quantityOfItem = item.getItemQuantity();
            int amountOfPrice = priceOfItem.getAmount();
               double vatRate = item.getItemVAT();
            double VATForItem = quantityOfItem*(amountOfPrice * vatRate);
            return VATForItem;
        }
        private void countTotalVAT(ItemDTO item) {
			double itemVat = vatForItem(item);
			countTotalVAT += itemVat; 
		 }


        private void appendString (StringBuilder builder, String string) {
            builder.append(string);
            builder.append("\n");
        }

}
