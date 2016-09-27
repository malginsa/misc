package java8_my_samples.lesson160504;

import java.util.Optional;

public class Car {

	// Initially the car can have no insurance 
	private Optional<Insurance> insurance = Optional.empty();

	public Optional<Insurance> getInsurance() {
		return insurance;
	}
	
	public void setInsurance(Insurance insurance) {
		// Optional.ofNullable(...) -- null is accepted
		// Optional.of(...) -- null is not accepted
		this.insurance = Optional.of(insurance);
		// Because insurance can't be null in our business model.
		// In that place we are arrest the problem, prevent
		// spreading it further.
	}

	public void tune() {
		System.out.println("tuned");
	}
}
