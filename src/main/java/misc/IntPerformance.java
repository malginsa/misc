package misc;

public class IntPerformance
{
    public static void main(String[] args)
    {
        long startTime = System.nanoTime();
        int n = 0;
        for (int i = 0; i < 1_000_000_000; i++)
        {
            n += 2 * i * i; // 0.832312686 s
//            n += 2 * (i * i); // 0.660837274 s
        }
        System.out.println((double) (System.nanoTime() - startTime) / 1_000_000_000 + " s");
        System.out.println("n = " + n);
    }
}
