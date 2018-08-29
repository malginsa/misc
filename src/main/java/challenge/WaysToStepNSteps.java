package challenge;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 */
public class WaysToStepNSteps
{
    static int climbStairsRecursive(int n)
    {
        if (n==1) return 1;
        if (n==2) return 2;

        return climbStairsRecursive(n-1) + climbStairsRecursive(n-2);
    }

    static int climbStairsIterative(int n)
    {
        int fn_2 = 1, fn_1 = 2;
        if (n == 1) return fn_2;
        if (n == 2) return fn_1;
        int fn = 0;
        for (int i = 3; i <= n; i++)
        {
            fn = fn_2 + fn_1;
            fn_2 = fn_1;
            fn_1 = fn;
        }
        return fn ;
    }

    public static void main(String[] args)
    {
        System.out.println(climbStairsRecursive(16));
        System.out.println(climbStairsIterative(16));
    }
}
