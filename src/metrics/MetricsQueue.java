package metrics;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import util.Utils;

public class MetricsQueue {

	public static void main(final String[] args) {

		MetricsQueue metrics = new MetricsQueue();

		Instant start = Instant.now();
		metrics.doWork();
		Instant stop = Instant.now();

		Duration d = Duration.between(start, stop);

		System.out.println("Elapsed: " + d.toMillis()/1000. + " sec");

	}

	private void doWork() {

		Data data = new Data();
		ReferenceSequence sequence = new ReferenceSequence();

		Flag isProcessingCompleted = new Flag(false);

		BlockingQueue<CompletableFuture<Integer>> queue = new LinkedBlockingQueue<>(3);

		CompletableFuture<Long> summator = CompletableFuture
			.supplyAsync(() -> {
				long sum = 0;
				while (!isProcessingCompleted.isFlag() || !queue.isEmpty()) {
					try {
						if (isProcessingCompleted.isFlag()) System.out.println(isProcessingCompleted.isFlag());
//						if(queue.isEmpty()) System.err.println("queue is empty");
						CompletableFuture<Integer> processCF = queue.take();
						sum += processCF.get();
//						System.out.println("got");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return sum;
			});

		for (Record record : data) {

			Reference ref = sequence.getRef(record);

			try {
				queue.put(CompletableFuture.supplyAsync(() -> process(record, ref)));
//				System.out.println("put");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		isProcessingCompleted.setFlag(true);

		System.out.println(summator.join());

	}

	private int process(final Record record, final Reference ref) {
		int sum = 0;
		for (int i = 0; i < record.read.length; i++) {
			sum += record.read[i];
//			sum += ref.read[i];
		}
		return sum;
	}

}
