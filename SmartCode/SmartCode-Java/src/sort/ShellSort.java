package sort;

/**
 * Shell排序，即希尔排序，是分组的插入排序，又叫缩小增量排序
 *
 * 因为直接插入排序在元素基本有序的情况下（接近最好情况），效率是很高的，因此希尔排序在时间效率上比前直接插入排序有较大提高。
 *
 * 时间复杂度：最好情况：O(n)；平均：O(n^1.5)；最坏：O(n^1.5或n^2)
 *
 * 分组时设置一个步长：gap来进行分组
 *
 * 对于原始数组int[] array = {49, 38, 65, 97, 26,  13, 27, 49, 55, 4}
 * 1.array.length = 10
 *   gap = 10/2 //gap取长度的一半
 *   则分组为{49, 13}{38, 27}{65, 49}{97, 55}{26, 4}
 *
 *   对每组进行排序，变为(13, 49)  (27, 38)  (49, 65)  (55, 97)  (4, 26)
 *
 *   那么经过这趟排序后，原来的数组就变成了{13, 27, 49, 55, 4,  49, 38, 65, 97, 26}
 *
 * 2.此时gap再取 gap/2 ＝ 2
 *
 *   分组变为{13, 49, 4, 38, 97}
 *        {27, 55, 49, 65, 26}
 *
 *   分别在对这两个数组进行插入排序
 *        {4, 13, 38, 49, 97}
 *        {26, 27, 49, 55, 65}
 *
 *   那么经过这趟排序后，原来的数组就变成了{4, 26, 13, 27, 38,  49, 49, 55, 97, 65}
 *
 *  3.此时步长再取一半，gap/2 = 1 ,这时候就是整个数组了，对整个数组进行插入排序
 *
 *  4.gap/2 = 0，此时不再进行排序，也就是当gap=0时跳出循环
 *
 *
 * blog link:http://blog.csdn.net/morewindows/article/details/6668714
 *
 *
 * 步长选择：
 *
         步长序列	    最坏情况下复杂度
         n/2^i	    {O}(n^2)
         2^k - 1	{O}(n^{3/2})
         2^i 3^i	{O}( n\log^2 n )

    最好步长序列由设计（1，4，10，23，57，132，301，701，1750，…）
 用这样步长序列的希尔排序比插入排序和堆排序都要快，甚至在小数组中比快速排序还快，但是在涉及大量数据时希尔排序还是比快速排序慢。
 *
 * Created by wangdongdong on 17/7/7.
 */
public class ShellSort {

    public static void shellSort(int[] array) {

        //经过几趟查找是由gap是否到0决定的
        //gap初始为数组长度，每次倍减，当为0时，排序结束（为什么？因为当为1时是对整个数组排序）
        for (int gap = array.length; gap > 0; gap /= 2) {
            for (int i = 0; i < gap; i++) {       //直接插入排序
                for (int j = i + gap; j < array.length; j += gap) {
                    if (array[j] < array[j - gap]) {
                        int temp = array[j];
                        int k = j - gap;
                        while (k >= 0 && array[k] > temp) {
                            array[k + gap] = array[k];
                            k -= gap;
                        }
                        array[k + gap] = temp;
                    }
                }
            }
        }
    }

    public static void shellSort2(int[] array) {

        //经过几趟查找是由gap是否到0决定的
        //gap初始为数组长度一半，每次倍减，当为0时，排序结束（为什么？因为当为1时是对整个数组排序）
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int j = gap; j < array.length; j++)//从数组第gap个元素开始，此轮对j位置的值插入到该组合适的位置
                if (array[j] < array[j - gap])//每个元素与自己组内的数据进行直接插入排序
                {
                    int temp = array[j]; //记录当前值
                    int k = j - gap; //默认为插入该组前一个位置
                    //当存在前一个位置，且值大于当前值时，前一个值往后移动；再往前比较
                    while (k >= 0 && array[k] > temp)
                    {
                        array[k + gap] = array[k];
                        k -= gap;
                    }
                    //先减了，因此需再加上
                    array[k + gap] = temp;
                }
        }
    }

    public static void main(String[] args) {

    }
}
