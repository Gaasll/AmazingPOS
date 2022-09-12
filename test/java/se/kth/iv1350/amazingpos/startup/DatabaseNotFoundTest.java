package se.kth.iv1350.amazingpos.startup;
//package se.kth.iv1350.amazingpos.model*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.amazingpos.integration.DatabaseException;
import se.kth.iv1350.amazingpos.integration.ExternalInventorySystem;



/**
 * Tests a databse not connected
 */

class DatabaseExceptionTest {

    private ExternalInventorySystem ext;
    @BeforeEach
    void setUp() {
        ext = ExternalInventorySystem.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

   
    @Test
    void testGetAdminMessage() {
        boolean databaseErrorCorrespondsToError = false;

        try{
            ext.getItem(6);
        }
        catch (DatabaseException dbExc){
            String s = dbExc.getAdminMessage().substring(dbExc.getAdminMessage().indexOf('\n'));
            System.out.println(s);
            if(s.contains("Database not found")) databaseErrorCorrespondsToError = true;

        }
        catch (InvalidItemExceptionTest itemNoFound){
            itemNoFound.getStackTrace();
        }
        assertTrue(databaseErrorCorrespondsToError, "Database error");

    }
}
