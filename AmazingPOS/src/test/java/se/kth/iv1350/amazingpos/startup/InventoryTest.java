package se.kth.iv1350.amazingpos.startup;

import se.kth.iv1350.amazingpos.DTO.*;
import se.kth.iv1350.amazingpos.model.*;
import se.kth.iv1350.amazingpos.integration.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class InventoryTest {
    
    private ExternalInventorySystem inventorySystem;
    private Sale sale;

    @BeforeEach
    void setUp() {
        inventorySystem = ExternalInventorySystem.getInstance();
        sale = new Sale();
    }

    @AfterEach
    void tearDown() {
        inventorySystem = null;
        sale = null;
    }

    @Test
    void colaZeroTest()  {
        ItemDTO itemDTO = null;
        
        try { 
            itemDTO = inventorySystem.retrieveItemInformation(1);
        } catch (InvalidItemException  e) {
            fail(e.getMessage());
        }
        String expectedName = "Cola Zero";
        assertEquals(expectedName,itemDTO.getName(), "Wrong ItemDTO name");
    }
    @Test
    void retrievePringlesDTOInformationTest() throws InvalidItemException, DatabaseException
    {
        ItemDTO itemDTO = null;
        try {
            itemDTO = inventorySystem.retrieveItemInformation(2);
        } catch (InvalidItemException | DatabaseException e) {
            fail(e.getMessage());
        }
        String expectedName = "Pringles";
        assertEquals(expectedName,itemDTO.getName(), "Wrong ItemDTO name");
    }

}