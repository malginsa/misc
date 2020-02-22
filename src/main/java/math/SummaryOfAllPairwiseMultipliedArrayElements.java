package math;

import org.junit.Assert;

import java.util.Random;

import static org.hamcrest.core.Is.is;

public class SummaryOfAllPairwiseMultipliedArrayElements
{
    public static long brutuforce(int[] input)
    {
        long result = 0;
        for (int i = 0; i < input.length; i++)
        {
            for (int j = i + 1; j < input.length; j++)
            {
                result += input[i] * input[j];
            }
        }
        return result;
    }

    public static long optimized(int[] input)
    {
        long result = 0;
        long sum = input[0];
        for (int i = 1; i < input.length; i++)
        {
            result += input[i] * sum;
            sum += input[i];
        }
        return result;
    }

    public static void main(String[] args)
    {
        int[] input = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; i++)
        {
            input[i] = random.nextInt(1000);
        }
        Assert.assertThat(brutuforce(input), is(optimized(input)));
    }
}
