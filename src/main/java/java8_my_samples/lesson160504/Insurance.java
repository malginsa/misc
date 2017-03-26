package java8_my_samples.lesson160504;

public class Insurance {

	private final String name;

	// "name" can't be null -> no Optional, "name" must be initialized
	public Insurance(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
