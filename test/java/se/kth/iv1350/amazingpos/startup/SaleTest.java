package se.kth.iv1350.amazingpos.startup;

import se.kth.iv1350.amazingpos.integration.*;
import se.kth.iv1350.amazingpos.DTO.SaleDTO;
import se.kth.iv1350.amazingpos.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * testiing sale
 */
public class SaleTest {
    private Sale sale;
    private ExternalAccountingSystem ac;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        ac = new ExternalAccountingSystem();
        sale.addItemMult(1,1, ExternalInventorySystem.getInstance());

    }

    @AfterEach
    void tearDown() {
        sale = null;
    }
    

    @Test
    void addColaZeroToSaleTest()
    {
        addColaZeroToSale();
        String expectedName = "Cola Zero";
        assertEquals(expectedName, sale.getItemInfo(), "Wrong name");
    }

   

    @Test
    void addSnickersToSaleTest()
    {
        addSnickersToSale();
        String expectedName = "Snickers";
        assertEquals(expectedName, sale.getItemInfo(), "Wrong name");
    }

    

    @Test
    void endSaleWithoutAnyItemsTest()
    {
        int expectedTotalPriceWithTax = 0;
        assertEquals(expectedTotalPriceWithTax, Sale.getTotalPriceAndTax(),
                "Wrong total price with tax");
    }


    private void addSnickersToSale() {
    }
    private void addColaZeroToSale() {
    }

}
