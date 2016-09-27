package java8_my_samples.lesson160504;

import java.util.Optional;

public class Person {

	// Person can have no car, in other worlds "car" can be "null"
	private Optional<Car> car = Optional.empty();

	public Optional<Car> getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = Optional.of(car);
	}
}
