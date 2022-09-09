package se.kth.iv1350.amazingpos.startup;

import se.kth.iv1350.amazingpos.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * tests an item. usually by comparing strings.
 */
class TestItem {
    private Item item;
    private String str;

    @BeforeEach
    void setUp() { //setups ItemTests
        item = new Item(1, 20, 0.25, "\"cola\"", 0);
        str = "1 \"Cola\" -  20kr";
    }

    @AfterEach
    void tearDown() {
        item = null;
        str = null;
    }


}