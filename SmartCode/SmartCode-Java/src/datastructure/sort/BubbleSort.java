package datastructure.sort;

import java.util.Arrays;

/**
 * 冒泡排序：
 * <p>
 * 原理：比较两个相邻的元素，将值大的元素交换至右端。
 * <p>
 * <p>
 * N个数字要排序完成，总共进行N-1趟排序，每i趟的排序次数为(N-i)次，所以可以用双重循环语句，外层控制循环多少趟，内层控制每一趟的循环次数
 *
 * 每一趟排序好后，比较的最大值会被排到最右端，因此在第i趟时，只需要排0到arr.length - 1 - i之间的数即可.
 * <p>
 *
 *
 * 时间复杂度计算：
 * （1）最好时间复杂度：如果原始数组是正序的，那么只需走一趟就好，那么比较次数为n-1次，即O(n)
 * （2）最坏时间复杂度：如果原始数组正好是反序的，那么就需要n-1趟，每趟需比较(n-1-i)次
 *         那么最大比较次数：(n-1)*(n-1-i)次， 0<i<n-1
 *                 --> (n-1)^2/2
 *         即O(n^2).
 *
 *
 *
 * Created by wangdongdong on 17/7/6.
 */
public class BubbleSort {

    /**
     * 一般的冒泡
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//外层循环控制排序趟数
            for (int j = 0; j < arr.length - 1 - i; j++) {//内层循环控制每一趟排序多少次
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 优化后的冒泡
     *
     * 定义一个标记位flag,表示原始数组是否已经排好序；
     * 如果排好序，那么第一趟比较的时候，不会发生交换，否则会发生交换
     * 在未发生交换时，退出循环
     *
     * @param arr
     */
    public static void bubbleSort2(int[] arr) {
        boolean flag = true; //默认认为原始数据已经排好序
        for (int i = 0; i < arr.length - 1; i++) {//外层循环控制排序趟数
            for (int j = 0; j < arr.length - 1 - i; j++) {//内层循环控制每一趟排序多少次
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;  //flag发生改变，则原始数组未排好序
                }
            }

            if (flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {6,3,8,2,9,1, 11, 24, 12, 17, 18, 16, 15};
        long start = System.currentTimeMillis();
        bubbleSort(array);
//        bubbleSort2(array);
        System.out.println("delta time:" + (System.currentTimeMillis() - start));
        System.out.println(Arrays.toString(array));
    }
}
