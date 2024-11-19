
using System;

public class Solution
{
    private static readonly int NOT_FOUND = 0;

    public long MaximumSubarraySum(int[] input, int sizeSubarray)
    {
        int[] frequencyMembersSubarray = InitializeFrequencyMembersSubarray(input, sizeSubarray);
        int numberOfUniqueValuesInSubarray = InitializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray);

        long sumSubarray = InitializeSumSubarray(input, sizeSubarray);
        long maxSumSubarray = numberOfUniqueValuesInSubarray == sizeSubarray ? sumSubarray : NOT_FOUND;

        for (int i = 1; i <= input.Length - sizeSubarray; ++i)
        {
            --frequencyMembersSubarray[input[i - 1]];
            if (frequencyMembersSubarray[input[i - 1]] == 0)
            {
                --numberOfUniqueValuesInSubarray;
            }

            ++frequencyMembersSubarray[input[i + sizeSubarray - 1]];
            if (frequencyMembersSubarray[input[i + sizeSubarray - 1]] == 1)
            {
                ++numberOfUniqueValuesInSubarray;
            }

            sumSubarray += -input[i - 1] + input[i + sizeSubarray - 1];

            if (numberOfUniqueValuesInSubarray == sizeSubarray && sumSubarray > maxSumSubarray)
            {
                maxSumSubarray = sumSubarray;
            }
        }
        return maxSumSubarray;
    }

    private int[] InitializeFrequencyMembersSubarray(int[] input, int sizeSubarray)
    {
        // Alternatively: int maxValue = input.Max();
        int maxValue = 0;
        foreach (int value in input)
        {
            maxValue = Math.Max(maxValue, value);
        }

        int[] frequencyMembersSubarray = new int[maxValue + 1];
        for (int i = 0; i < sizeSubarray; ++i)
        {
            ++frequencyMembersSubarray[input[i]];
        }
        return frequencyMembersSubarray;
    }

    private int InitializeNumberOfUniqueValuesInSubarray(int[] frequencyMembersSubarray)
    {
        int numberOfUniqueValuesInSubarray = 0;
        for (int i = 0; i < frequencyMembersSubarray.Length; ++i)
        {
            if (frequencyMembersSubarray[i] > 0)
            {
                ++numberOfUniqueValuesInSubarray;
            }
        }
        return numberOfUniqueValuesInSubarray;
    }

    private long InitializeSumSubarray(int[] input, int sizeSubarray)
    {
        long sumSubarray = 0;
        for (int i = 0; i < sizeSubarray; ++i)
        {
            sumSubarray += input[i];
        }
        return sumSubarray;
    }
}
