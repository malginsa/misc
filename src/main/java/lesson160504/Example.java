package lesson160504;

import java.util.Optional;

public class Example {

	// previous to Optional's implementation 
//	private static String getCarInsurance(Person person) {
//		if (null != person) {
//			Car car = person.getCar();
//			if (null != car) {
//				Insurance insurance = car.getInsurance();
//				if (null != insurance) {
//					String insName = insurance.getName();
//					if (null != insName) {
//						return insName;
//					}
//				}
//			}
//		}
//		return "Unknown";
//	}
	
	private static String getCarInsurance(Person person) {
		
		// whether person is null?
		return Optional.ofNullable(person)
			.flatMap(Person::getCar)
			.flatMap(Car::getInsurance)
			.filter(ins -> ins.getName() == "Renessans")
			.map(Insurance::getName)
//			.orElse("No insurance");
				// lazy calculate result in time of invocation
			.orElseGet(() -> "none of " + " insurance");
	}
	
	
	public static void main(String[] args) {
		
		Person person = new Person();
		String indName = getCarInsurance(person);
		
		person.setCar(new Car());
		person.getCar().ifPresent(car -> car.tune());
	}

}
