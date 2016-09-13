package misc;

public class maxMemory {

	public static void main(String[] args) {
		
		System.out.println(Runtime.getRuntime().maxMemory() / 1024. /1024./1024.);
	}
}
