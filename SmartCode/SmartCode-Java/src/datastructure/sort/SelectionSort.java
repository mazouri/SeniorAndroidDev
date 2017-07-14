package datastructure.sort;

/**
 * Java中的经典算法之选择排序（SelectionSort）
 * 参考：http://www.cnblogs.com/shen-hua/p/5424059.html
 *　
 * 第i趟排序，将此趟的最小值位置与i位置交换
 *
 * 第１趟将最小值交换到第１个位置
 * 第２趟将最小值交换到第２个位置
 * 第３趟将最小值交换到第３个位置
 * 以此类推
 *
 * Created by wangdong on 17-7-6.
 */
public class SelectionSort {

    /**
     *　思路：给定数组：int[] arr={里面n个数据}；
     *      第1趟排序，在待排序数据arr[1]~arr[n]中选出最小的数据，将它与arr[1]交换；
     *      第2趟，在待排序数据arr[2]~arr[n]中选出最小的数据，将它与r[2]交换；
     *      以此类推，第i趟在待排序数据arr[i]~arr[n]中选出最小的数据，将它与r[i]交换，直到全部排序完成。
     * @param array
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length -1; i++) {
            int k = i; //假设本轮最小数在第一个位置，用k记录一轮比较中最小的位置
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[k]) {
                    k = j; //记下目前找到的最小值所在的位置
                }
            }

            //一轮比较之后，交换i位置和k位置的值(如果k就是i，则说明i位置就是最小值，不用替换)
            if (i != k) {
                int temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {};
        selectionSort(array);
    }

}
