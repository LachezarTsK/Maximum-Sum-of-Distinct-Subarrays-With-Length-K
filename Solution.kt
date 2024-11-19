
import kotlin.math.max

class Solution {

    private companion object {
        const val NOT_FOUND: Long = 0
    }

    fun maximumSubarraySum(input: IntArray, sizeSubarray: Int): Long {
        val frequencyMembersSubarray: IntArray = initializeFrequencyMembersSubarray(input, sizeSubarray)
        var numberOfUniqueValuesInSubarray: Int = initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray)

        var sumSubarray: Long = initializeSumSubarray(input, sizeSubarray)
        var maxSumSubarray: Long = if (numberOfUniqueValuesInSubarray == sizeSubarray) sumSubarray else NOT_FOUND

        for (i in 1..input.size - sizeSubarray) {
            --frequencyMembersSubarray[input[i - 1]]
            if (frequencyMembersSubarray[input[i - 1]] == 0) {
                --numberOfUniqueValuesInSubarray
            }

            ++frequencyMembersSubarray[input[i + sizeSubarray - 1]]
            if (frequencyMembersSubarray[input[i + sizeSubarray - 1]] == 1) {
                ++numberOfUniqueValuesInSubarray
            }

            sumSubarray += -input[i - 1] + input[i + sizeSubarray - 1]

            if (numberOfUniqueValuesInSubarray == sizeSubarray && sumSubarray > maxSumSubarray) {
                maxSumSubarray = sumSubarray
            }
        }
        return maxSumSubarray
    }

    private fun initializeFrequencyMembersSubarray(input: IntArray, sizeSubarray: Int): IntArray {
        // Alternatively: val maxValue = input.max()
        var maxValue = 0
        for (value in input) {
            maxValue = max(maxValue, value)
        }

        val frequencyMembersSubarray = IntArray(maxValue + 1)
        for (i in 0..<sizeSubarray) {
            ++frequencyMembersSubarray[input[i]]
        }
        return frequencyMembersSubarray
    }

    private fun initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray: IntArray): Int {
        var numberOfUniqueValuesInSubarray = 0
        for (i in frequencyMembersSubarray.indices) {
            if (frequencyMembersSubarray[i] > 0) {
                ++numberOfUniqueValuesInSubarray
            }
        }
        return numberOfUniqueValuesInSubarray
    }

    private fun initializeSumSubarray(input: IntArray, sizeSubarray: Int): Long {
        var sumSubarray: Long = 0
        for (i in 0..<sizeSubarray) {
            sumSubarray += input[i]
        }
        return sumSubarray
    }
}
