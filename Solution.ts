
function maximumSubarraySum(input: number[], sizeSubarray: number): number {
    const NOT_FOUND = 0;
    const frequencyMembersSubarray: number[] = initializeFrequencyMembersSubarray(input, sizeSubarray);
    let numberOfUniqueValuesInSubarray: number = initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray);

    let sumSubarray: number = initializeSumSubarray(input, sizeSubarray);
    let maxSumSubarray: number = numberOfUniqueValuesInSubarray === sizeSubarray ? sumSubarray : NOT_FOUND;

    for (let i = 1; i <= input.length - sizeSubarray; ++i) {
        --frequencyMembersSubarray[input[i - 1]];
        if (frequencyMembersSubarray[input[i - 1]] === 0) {
            --numberOfUniqueValuesInSubarray;
        }

        ++frequencyMembersSubarray[input[i + sizeSubarray - 1]];
        if (frequencyMembersSubarray[input[i + sizeSubarray - 1]] === 1) {
            ++numberOfUniqueValuesInSubarray;
        }

        sumSubarray += -input[i - 1] + input[i + sizeSubarray - 1];

        if (numberOfUniqueValuesInSubarray === sizeSubarray && sumSubarray > maxSumSubarray) {
            maxSumSubarray = sumSubarray;
        }
    }
    return maxSumSubarray;
};

function initializeFrequencyMembersSubarray(input: number[], sizeSubarray: number): number[] {
    let maxValue = Math.max(...input);
    const frequencyMembersSubarray = new Array(maxValue + 1).fill(0);

    for (let i = 0; i < sizeSubarray; ++i) {
        ++frequencyMembersSubarray[input[i]];
    }
    return frequencyMembersSubarray;
}

function initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray: number[]): number {
    let numberOfUniqueValuesInSubarray = 0;
    for (let i = 0; i < frequencyMembersSubarray.length; ++i) {
        if (frequencyMembersSubarray[i] > 0) {
            ++numberOfUniqueValuesInSubarray;
        }
    }
    return numberOfUniqueValuesInSubarray;
}

function initializeSumSubarray(input: number[], sizeSubarray: number): number {
    let sumSubarray = 0;
    for (let i = 0; i < sizeSubarray; ++i) {
        sumSubarray += input[i];
    }
    return sumSubarray;
}
