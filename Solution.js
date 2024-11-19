
/**
 * @param {number[]} input
 * @param {number} sizeSubarray
 * @return {number}
 */
var maximumSubarraySum = function (input, sizeSubarray) {
    const NOT_FOUND = 0;
    const frequencyMembersSubarray = initializeFrequencyMembersSubarray(input, sizeSubarray);
    let numberOfUniqueValuesInSubarray = initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray);

    let sumSubarray = initializeSumSubarray(input, sizeSubarray);
    let maxSumSubarray = numberOfUniqueValuesInSubarray === sizeSubarray ? sumSubarray : NOT_FOUND;

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


/**
 * @param {number[]} input
 * @param {number} sizeSubarray
 * @return {number[]}
 */
function  initializeFrequencyMembersSubarray(input, sizeSubarray) {
    let maxValue = Math.max(...input);
    const frequencyMembersSubarray = new Array(maxValue + 1).fill(0);

    for (let i = 0; i < sizeSubarray; ++i) {
        ++frequencyMembersSubarray[input[i]];
    }
    return frequencyMembersSubarray;
}

/**
 * @param {number[]} frequencyMembersSubarray
 * @return {number}
 */
function initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray) {
    let numberOfUniqueValuesInSubarray = 0;
    for (let i = 0; i < frequencyMembersSubarray.length; ++i) {
        if (frequencyMembersSubarray[i] > 0) {
            ++numberOfUniqueValuesInSubarray;
        }
    }
    return numberOfUniqueValuesInSubarray;
}

/**
 * @param {number[]} input
 * @param {number} sizeSubarray
 * @return {number}
 */
function initializeSumSubarray(input, sizeSubarray) {
    let sumSubarray = 0;
    for (let i = 0; i < sizeSubarray; ++i) {
        sumSubarray += input[i];
    }
    return sumSubarray;
}
