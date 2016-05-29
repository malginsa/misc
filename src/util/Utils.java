package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class Utils {

	private final static Random random = new Random();
	
    private static final DecimalFormat formatter = 
    	new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

	static public void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static public void randomDelay(int permanent, int variable) {
		int delay = permanent + random.nextInt(variable);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static public void randomDelay(int variable) {
		randomDelay(0, variable);
	}

	static public void randomDelay() {
		randomDelay(500, 2000);
	}
	
    public static double format(double number) {
        synchronized (formatter) {
            return new Double(formatter.format(number));
        }
    }

}
