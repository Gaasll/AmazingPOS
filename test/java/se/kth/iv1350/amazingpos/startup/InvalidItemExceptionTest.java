package se.kth.iv1350.amazingpos.startup;
import se.kth.iv1350.amazingpos.integration.*;;
import se.kth.iv1350.amazingpos.controller.*;
import se.kth.iv1350.amazingpos.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.xml.crypto.Data;

public class InvalidItemExceptionTest {
    private ExternalInventorySystem ext;
    @BeforeEach
    void setUp() {
        ext = ExternalInventorySystem.getInstance();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetIncorrectID() {
        boolean isCorrect = false;
        try{
            ext.getItem(29);
        }
        catch (InvalidItemException itemNotFoundException){
            if(itemNotFoundException.getIncorrectID() == 29) isCorrect = true;
        }
        catch (DatabaseException databaseNotFoundException){
            databaseNotFoundException.getAdminMessage();
        }
        assertTrue(isCorrect, "ID getter from internal exception ItemNotFoundException not working");



    }

    @Test
    void testGetMessage() {
        boolean msgEqualsExpectedMessage = false;

        try{
            ext.getItem(213);
        }catch (InvalidItemException exception){
            if(exception.getMessage().equals("Invalid ID")) msgEqualsExpectedMessage = true;
        }
        catch (DatabaseException dbNotFound){
            dbNotFound.getAdminMessage();
        }
        assertTrue(msgEqualsExpectedMessage, "Not found");
    }

    @Test
    void testGetAdminMessage() {
        boolean msgEqualsExpectedMessage = false;

        try{
            ext.getItem(2321);
        }catch (InvalidItemException exception){
            String s = exception.getAdminMessage();
            System.out.println(s);
            if(s.contains("\n" +
                    "Invalid ID number\n" +
                    "\n" +
                    " End of Log \n" +
                    "\n")) msgEqualsExpectedMessage = true;
        }
        catch (DatabaseException dbNotFound){
            dbNotFound.getAdminMessage();
        }
        assertTrue(msgEqualsExpectedMessage, "");
    }
}