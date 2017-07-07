package sort;

/**
 * 归并排序（Merge）是将两个（或两个以上）有序表合并成一个新的有序表，
 * 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
 *
 * 时间复杂度为O(nlogn)
 *
 * Created by wangdongdong on 17/7/7.
 */
public class MergeSort {


    public static void mergeSort(int[] array) {
        sort(array, 0, array.length-1);
    }

    /**
     *
     * 对数组中[left...right]的子序列进行排序
     *
     * @param array 待排序序列
     * @param left 该序列最左位置
     * @param right 该序列最右位置
     */
    private static void sort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(array, left, mid); //左边排序好
            sort(array, mid + 1, right); //右边排序好

            merge(array, left, mid, right); //合并
        }
    }

    /**
     * 前一个序列：[left...mid]
     * 后一个序列：[mid+1...right]
     *
     * merge就是将这两个序列合并了
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; //创建新数组
        int k = 0; //k为新数组的指针

        //创建两个指针i,j
        int i = left; //左边序列的第一个位置
        int j = mid+1; //右边序列的第一个位置
        while(i <= mid && j <= right) {
            if (array[i] < array[j]) {
//                temp[k] = temp[i];
//                k++;i++;
                temp[k++] = array[i++];
            } else {
//                temp[k] = temp[j];
//                k++;j++;
                temp[k++] = array[j++];
            }
        }
        //上面while循环结束后，会有一个序列已经遍历完了，下面判断哪一个序列还有值，直接添加到新数组的后面
        while (i < mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }

        //使用新数组覆盖原数组
        for (int l = 0; l < temp.length; l++) {
            array[left + l] = temp[l];
        }
    }

    public static void main(String[] args) {

    }
}
