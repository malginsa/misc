package util;

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

    // return seconds, taken to run operation
	public static double timeMeasurement(Runnable operation) {
		Instant timeStampStart = Instant.now();
		operation.run();
		Instant timeStampFinish = Instant.now();
		return Duration.between(timeStampStart, timeStampFinish).toMillis()/1000.;

	}

	public static void main(String[] args) {

		Instant start = Instant.now();

		final int ret = hardWorkSimulation(100_000_000);
		System.out.println("ret = " + ret);

		Instant stop = Instant.now();
		Duration d = Duration.between(start, stop);
		System.out.println("Elapsed: " + d.toMillis()/1000. + " sec");

	}
}
