package lesson160505;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExampleIntStream {

	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 7, 11, 13, 239);

		IntStream intNumbers = numbers.stream().mapToInt(i -> i);
		System.out.println(intNumbers.max());

		System.out.println(numbers.stream().mapToInt(i -> i).summaryStatistics());

		IntStream.range(0, 10).parallel().forEach((i) -> System.out.print(" " + i));
		System.out.println();
		System.out.println();
		
		Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
			.boxed()
			.flatMap(a -> IntStream.rangeClosed(a, 100).
				filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
				.boxed()
				.map(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));

		pythagoreanTriples.forEach(i -> System.out.println(" " + i[0] + " " + i[1] + " " + i[2] ));

		Stream.iterate(new int[] {0, 1}, t -> new int[] {t[1], t[0]+t[1]})
			.limit(10)
			.map(t -> t[0])
			.forEach(System.out::println);
		
		Stream.generate(Math::random)
			.limit(3)
			.forEach(System.out::println);
		
		IntSupplier fibSupplier = new IntSupplier () {
			private int prev = 0;
			private int curr = 1;
			public int getAsInt() {
				int next = this.prev + this.curr;
				this.prev = this.curr;
				this.curr = next;
				return prev;
			}
		};
		IntStream.generate(fibSupplier)
			.limit(10)
			.forEach(System.out::println);

	}
}
