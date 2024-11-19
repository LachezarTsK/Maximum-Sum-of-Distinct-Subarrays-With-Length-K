
public class Solution {

    private static final int NOT_FOUND = 0;

    public long maximumSubarraySum(int[] input, int sizeSubarray) {
        int[] frequencyMembersSubarray = initializeFrequencyMembersSubarray(input, sizeSubarray);
        int numberOfUniqueValuesInSubarray = initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray);

        long sumSubarray = initializeSumSubarray(input, sizeSubarray);
        long maxSumSubarray = numberOfUniqueValuesInSubarray == sizeSubarray ? sumSubarray : NOT_FOUND;

        for (int i = 1; i <= input.length - sizeSubarray; ++i) {
            --frequencyMembersSubarray[input[i - 1]];
            if (frequencyMembersSubarray[input[i - 1]] == 0) {
                --numberOfUniqueValuesInSubarray;
            }

            ++frequencyMembersSubarray[input[i + sizeSubarray - 1]];
            if (frequencyMembersSubarray[input[i + sizeSubarray - 1]] == 1) {
                ++numberOfUniqueValuesInSubarray;
            }

            sumSubarray += -input[i - 1] + input[i + sizeSubarray - 1];

            if (numberOfUniqueValuesInSubarray == sizeSubarray && sumSubarray > maxSumSubarray) {
                maxSumSubarray = sumSubarray;
            }
        }
        return maxSumSubarray;
    }

    private int[] initializeFrequencyMembersSubarray(int[] input, int sizeSubarray) {
        // Alternatively: int maxValue = Arrays.stream(input).max().getAsInt();
        int maxValue = 0;
        for (int value : input) {
            maxValue = Math.max(maxValue, value);
        }

        int[] frequencyMembersSubarray = new int[maxValue + 1];
        for (int i = 0; i < sizeSubarray; ++i) {
            ++frequencyMembersSubarray[input[i]];
        }
        return frequencyMembersSubarray;
    }

    private int initializeNumberOfUniqueValuesInSubarray(int[] frequencyMembersSubarray) {
        int numberOfUniqueValuesInSubarray = 0;
        for (int i = 0; i < frequencyMembersSubarray.length; ++i) {
            if (frequencyMembersSubarray[i] > 0) {
                ++numberOfUniqueValuesInSubarray;
            }
        }
        return numberOfUniqueValuesInSubarray;
    }

    private long initializeSumSubarray(int[] input, int sizeSubarray) {
        long sumSubarray = 0;
        for (int i = 0; i < sizeSubarray; ++i) {
            sumSubarray += input[i];
        }
        return sumSubarray;
    }
}
