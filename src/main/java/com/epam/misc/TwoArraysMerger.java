package com.epam.misc;

import java.util.Arrays;

public class TwoArraysMerger
{
    public static int[] merge(int[] a, int[] b)
    {
        if (null == a)
        {
            throw new IllegalArgumentException("The first parameter can't be null.");
        }
        if (null == b)
        {
            throw new IllegalArgumentException("The second parameter can't be null.");
        }

        int[] result = new int[a.length + b.length];
        int iA = 0, iB = 0, iR = 0;

        while (iR < result.length)
        {
            if (iA >= a.length)
            {
                result[iR++] = b[iB++];
                continue;
            }
            if (iB >= b.length)
            {
                result[iR++] = a[iA++];
                continue;
            }
            if (a[iA] < b[iB])
            {
                result[iR++] = a[iA++];
            } else {
                result[iR++] = b[iB++];
            }
        }

        return result;
    }
}
