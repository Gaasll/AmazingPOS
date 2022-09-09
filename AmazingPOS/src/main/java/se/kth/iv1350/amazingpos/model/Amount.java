package se.kth.iv1350.amazingpos.model;

/**
 * 
 *This class shows the amount of money and currency 
 */

public class Amount {
    
    private String currency;
    private int amount;

    /**
	 * creates an instance of Amount
	 * @param amount    the amount of money
	 * @param currency  the currency for money
	 */
    public Amount (int amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }
    
    /**
	 * returns currency 
	 * @return
	 */
    public String getCurrency(){
        return this.currency;
    }

    /**
	 * returns the amount of money
	 * @return amount of money
	 */
    public int getAmount(){
        return this.amount;
    }

    /**
	 * sets the amount and the currency in one string 
	 * @return string of amount and currency
	 */
    public String toString(){
        return this.amount + " " + this.currency;
    }

    /**
	 * substructs two amounts
	 *  @param amountToSubstruct the amount to substruct
     *  @return the diifference
	 */
    public int substructAmount (Amount amountToSubstruct){
        int diff = this.amount - amountToSubstruct.amount;
        return diff;
    }

    /**
	 * adds two amounts
	 * @return
	 */
    public void addAmount (int amount){
        this.amount += amount;
    }
}
