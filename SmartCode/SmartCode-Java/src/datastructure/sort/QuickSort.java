package datastructure.sort;

/**
 *
 * 快速排序
 *
 * 思路：
 * 对于给定的一组记录，选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,
 * 一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分，直到序列中的所有记录均有序为止
 *
 * 时间复杂度：
 * 最坏：O(n ^ 2)
 * 最好：O(n * log n)
 * 平均：O(n * log n)
 *
 *
 * 参考：http://www.jb51.net/article/83519.htm
 *
 * Created by wangdongdong on 17/7/7.
 */
public class QuickSort {

    public static void quickSort(int[] array, int start, int end) {
        sort(array, 0, array.length -1);
    }

    public static void sort(int[] array, int start, int end) {
        int pointer1 = start; //序列起始位置,指针从左往右
        int pointer2 = end; //序列结束位置，指针从右往左

        int base = array[start]; //取起始位置作为基准
        int emptyIndex = start; // 空位的位置索引，默认是被取出的基准元素的位置

        //两指针未相遇前，进行排序，直到相遇(即pointer1 == pointer2)
        while (pointer1 < pointer2) {
            //pointer1 < pointer2表明两指针未相遇
            //从右向左，直到找到小于base的位置
            while (pointer1 < pointer2 && array[pointer2] >= base) {
                pointer2--;
            }

            //相遇前就找到对应元素，则将该元素给pointer1的"空位"，pointer2成了空位
            if (pointer1 < pointer2) {
                array[emptyIndex] = array[pointer2];
                emptyIndex = pointer2;
            }

            // pointer1开始从左向右一个个地查找大于基准元素的元素
            while (pointer1 < pointer2 && array[pointer1] <= base)
                pointer1++;
            if (pointer1 < pointer2) {
                // 如果pointer1在遇到pointer2之前就找到了对应的元素，就将该元素给pointer2的"空位"，i成了空位
                array[emptyIndex] = array[pointer1];
                emptyIndex = pointer1;
            }
        }

        // pointer1和pointer2相遇后会停止循环，将最初取出的基准值给最后的空位
        array[emptyIndex] = base;

        // =====本轮快速排序完成=====

        // 如果分割点i左侧有2个以上的元素，递归调用继续对其进行快速排序
        if (pointer1 - start > 1) {
            quickSort(array, 0, pointer1 - 1);
        }
        // 如果分割点j右侧有2个以上的元素，递归调用继续对其进行快速排序
        if (end - pointer2 > 1) {
            quickSort(array, pointer2 + 1, end);
        }
    }

    public static void main(String[] args) {

    }
}
