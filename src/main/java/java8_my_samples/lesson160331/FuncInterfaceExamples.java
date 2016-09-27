package java8_my_samples.lesson160331;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FuncInterfaceExamples {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		
		Predicate<String> p2 = (s) -> list.add(s);
		Predicate<String> p3 = list::add;
		
		Consumer<String> c0 = list::add;
		
	}
}
