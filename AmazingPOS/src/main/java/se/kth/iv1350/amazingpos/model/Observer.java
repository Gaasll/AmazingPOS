package se.kth.iv1350.amazingpos.model;

public interface Observer {
    void update(double amount);

    public String totalToString();
    public void newSale(double totalPrice);
}