package se.kth.iv1350.amazingpos.startup;

import se.kth.iv1350.amazingpos.controller.*;
import se.kth.iv1350.amazingpos.view.View;
import se.kth.iv1350.amazingpos.model.*;
import se.kth.iv1350.amazingpos.integration.*;

/**
 * Starts the entire application, contains the main method used to start the application.
 */
public class Main {
    /**
     * The main method used to start the entire application.
     *
     * @param args The application does not take any command line parameters.
     * @throws DatabaseException
     * @throws InvalidItemException
     */
    public static void main(String[] args) throws InvalidItemException, DatabaseException {
		RegistryCreator regCreator = new RegistryCreator();
		ExternalSystemGenerator extSys = new ExternalSystemGenerator();
		CashRegister cashRegister = new CashRegister();
		Controller contr = new Controller(regCreator, extSys, cashRegister);
        View view = new View(contr);
        view.runFakeExecution();
	}

    


}

