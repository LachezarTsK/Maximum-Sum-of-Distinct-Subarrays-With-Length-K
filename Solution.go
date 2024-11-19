
package main

import "fmt"

const NOT_FOUND int64 = 0

func maximumSubarraySum(input []int, sizeSubarray int) int64 {
    var frequencyMembersSubarray []int = initializeFrequencyMembersSubarray(input, sizeSubarray)
    var numberOfUniqueValuesInSubarray int = initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray)

    var sumSubarray int64 = initializeSumSubarray(input, sizeSubarray)
    var maxSumSubarray int64 = NOT_FOUND
    if numberOfUniqueValuesInSubarray == sizeSubarray {
        maxSumSubarray = sumSubarray
    }

    for i := 1; i <= len(input)-sizeSubarray; i++ {
        frequencyMembersSubarray[input[i - 1]]--
        if frequencyMembersSubarray[input[i - 1]] == 0 {
            numberOfUniqueValuesInSubarray--
        }

        frequencyMembersSubarray[input[i + sizeSubarray - 1]]++
        if frequencyMembersSubarray[input[i + sizeSubarray - 1]] == 1 {
            numberOfUniqueValuesInSubarray++
        }

        sumSubarray += int64(-input[i - 1] + input[i+sizeSubarray - 1])

        if numberOfUniqueValuesInSubarray == sizeSubarray && sumSubarray > maxSumSubarray {
            maxSumSubarray = sumSubarray
        }
    }
    return maxSumSubarray
}

func initializeFrequencyMembersSubarray(input []int, sizeSubarray int) []int {
    // Alternatively: maxValue := slices.Max(input)
    maxValue := 0
    for _, value := range input {
        maxValue = max(maxValue, value)
    }

    frequencyMembersSubarray := make([]int, maxValue+1)
    for i := 0; i < sizeSubarray; i++ {
        frequencyMembersSubarray[input[i]]++
    }
    return frequencyMembersSubarray
}

func initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray []int) int {
    numberOfUniqueValuesInSubarray := 0
    for i := range frequencyMembersSubarray {
        if frequencyMembersSubarray[i] > 0 {
            numberOfUniqueValuesInSubarray++
        }
    }
    return numberOfUniqueValuesInSubarray
}

func initializeSumSubarray(input []int, sizeSubarray int) int64 {
    var sumSubarray int64 = 0
    for i := 0; i < sizeSubarray; i++ {
        sumSubarray += int64(input[i])
    }
    return sumSubarray
}
