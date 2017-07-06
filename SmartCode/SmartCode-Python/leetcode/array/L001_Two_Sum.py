class TwoSum:
    @staticmethod
    def twoSum(nums, target):
        """
         * 题目：
         * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
         * You may assume that each input would have exactly one solution, and you may not use the same element twice.
         * 给定一个数组和一个目标数，从数组中找到两个数，使得这两个数之和等于这个目标数，返回两个数的编号。
         *
         * Example:
         * Given nums = [2, 7, 11, 15], target = 9,
         * Because nums[0] + nums[1] = 2 + 7 = 9,
         * return [0, 1].
         *
         * 给定一个数组 nums = [2, 7, 11, 15], target = 9,
         * 因为 nums[0] + nums[1] = 2 + 7 = 9,
         * return [0, 1].

         link:https://discuss.leetcode.com/topic/23004/here-is-a-python-solution-in-o-n-time

        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        # judge boundary
        if len(nums) <= 1:
            return False
        buff_dict = {}
        for i in range(len(nums)):
            if nums[i] in buff_dict:
                return [buff_dict[nums[i]], i]
            else:
                buff_dict[target - nums[i]] = i


if __name__ == '__main__':
    nums = [1, 2, 3, 4, 6]
    print(TwoSum.twoSum(nums, 8))
