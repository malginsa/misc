package misc;

public class FinalSample {

	private static int doWork() {
		int[] first = {0,1,2,3,4};
		int[] second = {0,1,6,7,8};
		
		for (int i = 0; i < first.length; ++i) {
			System.out.println(i);
		}
		
		return 777;
	}

	public static void main(String[] args) {
		
		doWork();
	}
	
}
