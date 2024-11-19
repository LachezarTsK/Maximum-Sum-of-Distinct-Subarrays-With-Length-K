
#include <span>
#include <ranges>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {

    static const int NOT_FOUND = 0;

public:
    long long maximumSubarraySum(const vector<int>& input, int sizeSubarray) const {
        vector<int> frequencyMembersSubarray = initializeFrequencyMembersSubarray(input, sizeSubarray);
        int numberOfUniqueValuesInSubarray = initializeNumberOfUniqueValuesInSubarray(frequencyMembersSubarray);

        long long sumSubarray = initializeSumSubarray(input, sizeSubarray);
        long long maxSumSubarray = numberOfUniqueValuesInSubarray == sizeSubarray ? sumSubarray : NOT_FOUND;

        for (size_t i = 1; i <= input.size() - sizeSubarray; ++i) {
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

private:
    vector<int> initializeFrequencyMembersSubarray(span<const int> input, int sizeSubarray) const {
        int maxValue = *ranges::max_element(input);
        vector<int> frequencyMembersSubarray(maxValue + 1);

        for (size_t i = 0; i < sizeSubarray; ++i) {
            ++frequencyMembersSubarray[input[i]];
        }
        return frequencyMembersSubarray;
    }

    int initializeNumberOfUniqueValuesInSubarray(span<const int> frequencyMembersSubarray) const {
        int numberOfUniqueValuesInSubarray = 0;
        for (size_t i = 0; i < frequencyMembersSubarray.size(); ++i) {
            if (frequencyMembersSubarray[i] > 0) {
                ++numberOfUniqueValuesInSubarray;
            }
        }
        return numberOfUniqueValuesInSubarray;
    }

    long long initializeSumSubarray(span<const int> input, int sizeSubarray) const {
        long long sumSubarray = 0;
        for (size_t i = 0; i < sizeSubarray; ++i) {
            sumSubarray += input[i];
        }
        return sumSubarray;
    }
};
