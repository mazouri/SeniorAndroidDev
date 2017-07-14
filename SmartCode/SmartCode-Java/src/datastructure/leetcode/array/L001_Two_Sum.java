package datastructure.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangdong on 17-6-28.
 *
 * 题目：
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 给定一个数组和一个目标数，从数组中找到两个数，使得这两个数之和等于这个目标数，返回两个数组的编号。
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 * 给定一个数组 nums = [2, 7, 11, 15], target = 9,
 * 因为 nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
//https://leetcode.com/articles/two-sum/
//Link:http://www.jianshu.com/p/e2e0c0105afd
public class L001_Two_Sum {

    /**
     * 最low的方式，通过两个循环，一个个相加直到得到正确结果
     *
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(1)
     *
     *
     * example:{5, 1, 2, 11, 6, 7, 15}, 9
     *
     * 5+1=6,5+2=7,5+11=16,5+6=11,5+7=12,5+15=20,
     * 1+2=3,1+11=12,1+6=7,1+7=8,1+15=16,
     * 2+11=13,2+6=8,2+7=9,
     * execCount：14
     * result is 2 & 5
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] indexs = new int[2];
        int execCount=0;
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                System.out.print(num1 + "+" + nums[j] + "=" +(num1+nums[j]) + ",");
                execCount++;//执行次数
                if (num1 + nums[j] == target) {
                    indexs[0] = i;
                    indexs[1] = j;
                    System.out.println("\nexecCount："+execCount);
                    return indexs;
                }
            }
            System.out.println("");
        }
        System.out.println("\nexecCount："+execCount);
        return indexs;
    }

    /**
     * 通过2次Hash Table,因为HashMap需要存储key和value两个内容，因此空间翻倍
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 通过一次HashTable,在给HashMap添加数据时，先判断当前数值的"补充"是否存在，存在则找到结果
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                indexs[0]=map.get(target-nums[i]);
                indexs[1]=i;
                return indexs;
            }

            map.put(nums[i], i);
        }

        return indexs;
    }

    public static void main(String[] args) {
        int nums[] = {5, 1, 2, 11, 6, 7, 15};
//        int[] indexs = twoSum1(nums, 9);
        int[] indexs = twoSum(nums, 9);
        System.out.println("result is " + indexs[0] + " & " + indexs[1]);
    }
}
