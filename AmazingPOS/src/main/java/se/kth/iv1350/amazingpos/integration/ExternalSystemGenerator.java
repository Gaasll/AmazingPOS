package se.kth.iv1350.amazingpos.integration;
/**
* generating af External systems.
*/
public class ExternalSystemGenerator {
 /**
	 * Get the value of the printer
	 * @return the value of the printer
	 */
	public Printer getPrinter() {
		return new Printer();
	}

public static ExternalSystemGenerator getInstance() {
	return null;
}

public void getItem(int i) {
}   
}
