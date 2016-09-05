package util;

import misc.StringGenerator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.time.Instant;
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

	public static int hardWorkSimulation(int length) {
		final Random random = new Random();
		int ret = 0;
		for (int i = 0; i < length; i++) {
			ret += random.nextInt();
		}
		return ret;
	}

    /**
     return seconds, taken to run operation
    */
	public static double timeMeasurement(Runnable operation) {
		Instant timeStampStart = Instant.now();
		operation.run();
		Instant timeStampFinish = Instant.now();
		return Duration.between(timeStampStart, timeStampFinish).toMillis()/1000.;
	}

    public static void printSystemResourcesInfo() {

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long max = Runtime.getRuntime().maxMemory();
        /* Maximum amount of memory the JVM will attempt to use */

        /* Total amount of free memory available to the JVM */
        final long free = Runtime.getRuntime().freeMemory();

        /* Total memory currently in use by the JVM */
        final long total = Runtime.getRuntime().totalMemory();

        final long used = total - free;

        System.out.print("Memory in MBytes: \tmaximum = " + ( max / 1_000_000) + " \t");
        System.out.print("total = " + total / 1_000_000 + " \t");
        System.out.print("free = " + free / 1_000_000 + " \t");
        System.out.println("used = " + used / 1_000_000);
    }

    public static void main(String[] args) {
	}
}
