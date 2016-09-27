package misc;

// 
// Copying elements from one array to other in different thread

import util.Utils;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreTest {

	private static final int ARRAY_SIZE = 4; 
	private static final int BLOCK_SIZE = 3;
	private static final int CNT_BLOCKS = ARRAY_SIZE / BLOCK_SIZE;
	private static final int REMAINED = ARRAY_SIZE % BLOCK_SIZE;

	private static Integer[] src;
	private static Integer[] dst;

	private static Semaphore semaphore = new Semaphore(0);
	private static ExecutorService executor = Executors.newSingleThreadExecutor();

	public static void main(String[] args) {
		doInitArrays();
		startExtraction();
		doReplace();
		gentlyStopExtraction();
		printArray("src: ", src);
	}

	private static void doReplace() {
		for (int block = 0; block < CNT_BLOCKS; block++) {
			semaphore.acquireUninterruptibly();
			for (int index = block * BLOCK_SIZE; index < (block+1) * BLOCK_SIZE; index++) {
				replaceElement(src, dst, index);
			}
		}
		if(REMAINED != 0) {
			Utils.randomDelay(100, 1000);
			semaphore.acquireUninterruptibly();
			for (int index = BLOCK_SIZE * CNT_BLOCKS; index < BLOCK_SIZE * CNT_BLOCKS + REMAINED; index++) {
				replaceElement(src, dst, index);
			}
		}
	}

	private static void gentlyStopExtraction() {
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void startExtraction() {
		executor.execute(() -> {
			for (int i = 0; i < src.length; i++) {
				extractElement(src, i);
				if(((i+1) % BLOCK_SIZE) == 0) {
					semaphore.release();
					System.out.println("sem=" + semaphore.availablePermits());
				}
			}
			if(REMAINED != 0) {
				semaphore.release();
			}
		});
	}

	private static void extractElement(Integer[] array, int i) {
		Utils.randomDelay(100, 500);
		System.out.println(array[i] + " out  ");
		array[i] = null;
	}

	private static void replaceElement(Integer[] src, Integer[] dst, int index) {
		Utils.randomDelay(100, 1000);
		System.out.println(src[index] + " replaced with " + dst[index] + "  sem=" + semaphore.availablePermits());
		src[index] = dst[index];
	}

	private static void doInitArrays() {
		src = IntStream
			.range(0, ARRAY_SIZE)
			.map(i -> i*2)
			.boxed()
			.toArray(size -> new Integer[size]);
		dst = IntStream
				.range(0, ARRAY_SIZE)
				.map(i -> i*2+1)
				.boxed()
				.toArray(size -> new Integer[size]);
		printArray("src: ", src);
		printArray("dst: ", dst);
	}

	private static void printArray(String header, Integer[] array) {
		System.out.print(header);
		Arrays.stream(array).forEachOrdered(i -> System.out.print(i + " "));
		System.out.println();
	}
}
