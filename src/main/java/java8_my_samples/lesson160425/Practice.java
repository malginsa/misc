package java8_my_samples.lesson160425;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Practice {

	public static void main(String... args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300), 
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400), 
			new Transaction(mario, 2012, 710), 
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950));

		// Query 1: Find all transactions from year 2011 and sort them by value
		// (small to high).

		System.out.println( "1: " + transactions.stream()
			.filter(t -> 2011 == t.getYear())
//			.sorted((a,b) -> a.getValue() - b.getValue())
			.sorted(Comparator.comparing(Transaction::getValue))
			.collect(toList())
		);

		// Query 2: What are all the unique cities where the traders work?

		System.out.println( "2: " + transactions.stream()
			.map(t -> t.getTrader().getCity())
			.distinct()
			.collect(toList())
		);
		

		// Query 3: Find all traders from Cambridge and sort them by name.

		System.out.println( "3: " + transactions.stream()
			.map(t -> t.getTrader())
			.distinct()
			.filter(t -> t.getCity().equals("Cambridge"))
			.sorted(Comparator.comparing(Trader::getName))
			.collect(toList())
		);

		// Query 4: Return a string of all traders’ names sorted alphabetically.
		
//		System.out.println( "4: " + transactions.stream()
//			.map(t -> t.getTrader().getName())
//			.distinct()
//			.sorted()
//			.reduce("", (a, b) -> a + " " + b)
//		);

//		System.out.println( "4: " + transactions.stream()
//			.map(t -> t.getTrader().getName())
//			.distinct()
//			.sorted()
//			.map(s -> new StringBuffer(s))
//			.reduce(new StringBuffer("Traders: "), (a, b) -> a.append(" ").append(", ").append(b))
//			.toString()
//		);

		System.out.println( "4: " + transactions.stream()
			.map(t -> t.getTrader().getName())
			.distinct()
			.sorted()
			.collect(Collectors.joining(", "))
		);

		
		// Query 5: Are there any trader based in Milan?
		
		System.out.println( "5: " + 
			transactions.stream()
				.anyMatch(t -> t.getTrader().getCity().equals("Milan"))
			);
		
		// Query 6: Update all transactions so that the traders from Milan are
		// set to Cambridge.
		
		System.out.println("6.1: ");
		List<Transaction> transactions6 = new ArrayList<>();
		for (Transaction transaction : transactions) {
			transactions6.add(transaction.clone());
		}
		System.out.println("before " + transactions6);
//		transactions6.stream()
//			.filter(trans -> trans.getTrader().getCity().equals("Milan"))
//			.peek(t -> t.getTrader().setCity("Cambridge"))
//			.anyMatch(t -> false);
		transactions6.stream()
			.filter(trans -> trans.getTrader().getCity().equals("Milan"))
			// foreach -- transformation and termination operation
			.forEach(t -> t.getTrader().setCity("Cambridge"));
		System.out.println("after " + transactions6);

		System.out.print( "6.2: " ); 
//		transactions.stream()
//			.filter(t -> t.getTrader().getCity().equals("Cambridge"))
//			.forEach(t -> System.out.print(t.getValue() + " "));
		transactions.stream()
			.filter(t -> t.getTrader().getCity().equals("Cambridge"))
			.map(Transaction::getValue)
			.forEach(System.out::println);
		System.out.println();


		// Query 7: What's the highest value in all the transactions?

		System.out.println( "7: " + transactions.stream()
//			.mapToInt(t -> t.getValue())
			.mapToInt(Transaction::getValue)
//			.max()
//			.reduce((a,b) -> a > b ? a : b)
			.reduce(Integer::max)
		);

		// Query 8: Find transaction with a minimum value
		
		System.out.println( "8: " + transactions.stream()
//			.reduce((a,b) -> a.getValue() < b.getValue() ? a : b)
			.min(Comparator.comparing(Transaction::getValue))
		);
	}
}