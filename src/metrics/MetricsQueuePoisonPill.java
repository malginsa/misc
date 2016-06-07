package metrics;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;

public class MetricsQueuePoisonPill {

	private static Integer QUEUE_SIZE = 77;
	
	public static void main(final String[] args) {

		MetricsQueuePoisonPill metrics = new MetricsQueuePoisonPill();

		Instant start = Instant.now();
		metrics.doWork();
		Instant stop = Instant.now();

		Duration d = Duration.between(start, stop);

		System.out.println("Elapsed: " + d.toMillis()/1000. + " sec");

	}

	private void doWork() {

		Data data = new Data();
		ReferenceSequence sequence = new ReferenceSequence();

		BlockingQueue<CompletableFuture<Integer>> queue = 
			new LinkedBlockingQueue<>(QUEUE_SIZE);

		CompletableFuture<Long> summator = CompletableFuture
			.supplyAsync(() -> {
				long sum = 0;
				while (true) {
					try {
						CompletableFuture<Integer> processCF = queue.take();
						Integer resultCF = processCF.get();
						if (null == resultCF) break;
						sum += resultCF;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return sum;
			});

		for (Record record : data) {

			Reference ref = sequence.getRef(record);

			try {
				queue.put(CompletableFuture.supplyAsync(
					() -> process(record, ref)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		try {
			queue.put(CompletableFuture.supplyAsync(() -> null));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("summ = " + summator.join());

	}

	private int process(final Record record, final Reference ref) {
		int sum = 0;
		for (int i = 0; i < record.read.length; i++) {
			sum += record.read[i];
		}
		return sum;
	}

}
