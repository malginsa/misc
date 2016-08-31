package lesson160405;

import java.util.Optional;

import library.Book;

public class StreamsExamples {

	static Book getBook() {
		return null;
	}

	static Optional<Book> getCorrectBook(){
		Book b = null;
		return Optional.ofNullable(b);
	}

	public static void main(String[] args) {
		
//		IntStream.iterate(1, i -> i*2).limit(10).forEach(System.out::println);
//		
//		Optional<Integer> max = Arrays.asList(1,2,3,4,5).stream().map(i -> i+1).max(Integer::compareTo);
//		
//		Optional<Integer> noMax = Arrays.asList(2,2,2).stream().max(Integer::compareTo);
//
//		System.out.println(noMax);
//
//		IntStream.rangeClosed(1, 5).forEach(System.out::println);

//		Book.library.stream().filter(b -> b.getTopic().equals(Topic.PROGRAMMING)).forEach(System.out::println);
		
//		Book.library.stream().map(b -> b.getTitle()).forEach(System.out::println);
//		Book.library.stream().map(Book::getAuthors).forEach(System.out::println);
		
//		Book.library.stream().sorted(Comparator.comparing(Book::getTitle)).forEach(System.out::println);
		
//		Book.library.stream().sorted(new Comparator<Book>() {
//			@Override
//			public int compare(Book b1, Book b2) {
//				return b1.getTitle().compareTo(b2.getTitle());
//			}
//		}).forEach(System.out::println);

//		Book.library.stream()
//			.flatMap(b -> b.getAuthors().stream())
//			.forEach(System.out::println);
		
//		Book.library.stream()
//			.map(Book::getAuthors)
//			.flatMap(listAuthors -> listAuthors.stream())
//			.forEach(System.out::println);

//		Book.library.stream()	// creation
//			.map(Book::getTitle)	// transformation
//			.collect(Collectors.toList())	// termination
//			.forEach(System.out::println);

//		Stream<String> stremOfTitles = Book.library.stream()
//			.map(Book::getTitle);
//		System.out.println(stremOfTitles.collect(Collectors.toList()));

//		// transformation - laizy: dont process until invoke termination
//		Stream<String> stremOfTitles = Book.library.stream()
//				.map(book -> {
//					System.out.println(book);
//					return book.getTitle();
//				});
//		stremOfTitles.limit(1).forEach(System.out::println);

//		int sum = Book.library.stream()
//			.flatMapToInt(book -> IntStream.of(book.getPageCounts()))
//			.sum();
//		System.out.println(sum);

//		long count = Book.library.stream()
//			.filter(book -> book.getTopic() == Book.Topic.FICTION)
//			.count();
//		System.out.println(count);
			
		
//		Optional<Book> anyFictionBook = Book.library.stream()
//			.filter(book -> book.getTopic() == Book.Topic.DETECTIVE)
//			.findAny();
////		System.out.println(anyFictionBook);
//		
//
//		Book emptyBook = new Book(){
//			@Override
//			public String toString() {
//				return "Empty book";
//			}
//		};
//		
//		Book someBook = anyFictionBook.orElse(emptyBook);
////		System.out.println(someBook);
//		
//	
//		Book b = getBook();
//		b = b == null ? emptyBook : b;
//		Optional.ofNullable(getBook()).orElse(emptyBook);
//		
//		System.out.println(getCorrectBook().orElse(emptyBook));


// old-school incorrect 
//		Map<Book.Topic, List<Book>> booksByTopic = new ConcurrentHashMap<>();
//		Book.library.parallelStream()  // ? using parallelStream leads to Ex 
//			.peek(book -> { // код в блоке ниже меняет внешние объекты, 
//				// даже если исп-ть stream() вместо parallelStream() можно получить Race Condition
//				// ConcurrentHashMap недостаточно, нужен ещё и "ConcurrentList"
//				Topic topic = book.getTopic();
//				List<Book> list = booksByTopic.get(topic);
//				if (null == list) {
//					list = new ArrayList<>();
//				}
//				list.add(book);
//				booksByTopic.put(topic, list);
//			})
//			.anyMatch(book -> false); // будут обработаны все книги
//		System.out.println(booksByTopic.get(Book.Topic.valueOf("FICTION")));


// new fashion incorrect too
//		Map<Book.Topic, List<Book>> booksByTopic2 = new ConcurrentHashMap<>();
//		for (Book.Topic topic : Book.Topic.values()) {
//			booksByTopic2.put(topic, new ArrayList<>());
//		}
//		Book.library.stream() // ? using parallelStream leads to Ex 
//			.peek(book -> booksByTopic2.get(book.getTopic()).add(book))
//			.anyMatch(book -> false); // будут обработаны все книги
//		System.out.println(booksByTopic2.get(Book.Topic.valueOf("FICTION")));

// correct
//		Map<Topic, List<Book>> byTopic = 
//			Book.library.stream()
//				.collect(Collectors.groupingBy(Book::getTopic));
//		System.out.println(byTopic.get(Book.Topic.valueOf("FICTION")));

		
//		int[] numbers = new int[] {1,2,3,4,5,6,7,8,9};
//		OptionalInt sum = 
//			Arrays.stream(numbers)
//				.parallel()
//				//.reduce((a,b) -> a + b);
//				.reduce(Integer::sum);

//		// to find a maximum
//		OptionalInt sum = 
//				Arrays.stream(numbers)
//					.parallel()
//					.reduce((a,b) -> a > b ? a : b);

//		// count numbers
//		OptionalInt sum = 
//				Arrays.stream(numbers)
//					.parallel()
//					.map(i -> 1)
//					.reduce(Integer::sum);
//		System.out.println(sum);
	
		
		
	}

}














