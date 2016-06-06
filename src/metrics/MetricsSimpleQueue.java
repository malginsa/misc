package metrics;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;

public class MetricsSimpleQueue {

	public static void main(final String[] args) {

		MetricsSimpleQueue metrics = new MetricsSimpleQueue();

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
		BlockingQueue<CompletableFuture<Integer>> queue = new LinkedBlockingQueue<>(1);

		CompletableFuture<Long> summator = CompletableFuture
			.supplyAsync(() -> {
				long sum = 0;
				while (!isProcessingCompleted.isFlag()) {
					try {
//						if (isProcessingCompleted.isFlag()) System.out.println("flag = " + isProcessingCompleted.isFlag());
						if(queue.isEmpty()) System.out.println("queue is empty");
//						System.out.println("taking..");
						CompletableFuture<Integer> processCF = queue.take();
//						System.out.println("taken");
						sum += processCF.get();
//						hardWorkSimulation(100_000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("summator finished");
				return sum;
			});

		for (Record record : data) {

			Reference ref = sequence.getRef(record);

			try {
//				System.out.println("putting..");
				queue.put(CompletableFuture.supplyAsync(() -> process(record, ref)));
//				System.out.println("put");
//				hardWorkSimulation(100_000_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		isProcessingCompleted.setFlag(true);

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
