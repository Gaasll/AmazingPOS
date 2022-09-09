package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.model.*;;

/**
 * TotalRevenueView sends information to display 
 */
public class TotalRevenueView implements Observer{
    private double totalPaid;

    /**
     *
     */
    public TotalRevenueView(){
        totalPaid = 0;
    }
/**
 * Updates amount 
 * @param amount
 */
    @Override
    public void update(double amount) {
        totalPaid += amount;
        System.out.println(totalToString());

    }

   
    public String totalToString(){
        return "OBSERVER: "+ totalPaid + " SEK since start.";
    }
    @Override
    public void newSale(double totalPrice) {
        
        
    }
}