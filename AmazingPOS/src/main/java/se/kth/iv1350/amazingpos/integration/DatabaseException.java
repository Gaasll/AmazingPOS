package se.kth.iv1350.amazingpos.integration;

public class DatabaseException extends Exception{
    /**
     * Creates a DatabaseException when the database server is simply not running..
     */
    public DatabaseException(){
        super("No connection with database.");
    }

    public String getAdminMessage() {
        return null;
    }
}
    

