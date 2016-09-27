package java8_my_samples.lesson160504;

import java.util.Optional;

// How to operate with classes, which instances can be null

public class OfNullable {

	public static class B {
		public static String getB() {
			return null;
		}
	}
	
	public static void main(String[] args) {
		
		Optional<String> ofNullable = 
			Optional.ofNullable( B.getB() );
		
		System.out.println(ofNullable);
	}
}
