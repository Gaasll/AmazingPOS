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
     */
    public static void main(String[] args) {
		RegistryCreator regCreator = new RegistryCreator();
		ExternalSystemGenerator extSys = new ExternalSystemGenerator();
		CashRegister cashRegister = new CashRegister();
		Controller contr = new Controller(regCreator, extSys, cashRegister);
        View view = new View(contr);
        view.runFakeExecution();
	}

    


}

