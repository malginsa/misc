package metrics;

import java.util.Iterator;
import java.util.Random;

public class Data implements Iterable<Record> {

//	public static final int MAX = 1_000_000; // количество ридов
	public static final int MAX = 100; // количество ридов
	public static final int READ_SIZE = 1000;
	private static final Random random = new Random();

	private static final char[] LETTERS = {'A','C','G','T'};

	@Override
	public Iterator<Record> iterator() {
		return new Iterator<Record>() {
			private int count;

			@Override
			public boolean hasNext() {
				return count < MAX;
			}

			@Override
			public Record next() {
				count++;
				char[] data = new char[READ_SIZE];
				for (int i = 1; i <= data.length; i++) {
//					data[i] = LETTERS[random.nextInt(LETTERS.length)];
//					data[i-1] = (char)i;
					data[i-1] = 1;
				}
				return new Record(data);
			}

// not necessary in java8			
//			@Override
//			public void remove() {
//				// TODO Auto-generated method stub
//			}
		};
	}

}
