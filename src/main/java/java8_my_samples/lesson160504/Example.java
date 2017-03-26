package java8_my_samples.lesson160504;

import java.util.Optional;

public class Example {

	// previous to Optional's implementation
//	private static String getCarInsurance0(Person person) {
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

    private static String getCarInsurance1(Person person) {
        Optional<Car> car = person.getCar();
        Optional<Insurance> insurance = car.flatMap(Car::getInsurance);
        Optional<String> name = insurance.map(Insurance::getName); // insurance always has a name, that is why .map()
        return name.orElse("Unknown");
    }

    private static String getCarInsurance2(Person person) {
		// in case of person is null?
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
		String indName = getCarInsurance2(person);
		
		person.setCar(new Car());
		person.getCar().ifPresent(car -> car.tune());
	}

}
