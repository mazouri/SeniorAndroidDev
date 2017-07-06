package sort;

/**
 *　插入排序，就是把当前待排序的元素插入到一个已经排好序的列表里面
 *
 * Created by wangdong on 17-7-6.
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] array = {};
        insertSort(array);
    }

    /**
     * 从第二个位置开始与前面的元素比较，如果小，则向前一一比较，并把前面的值向后移动
     * 直到找到更小的值。
     *
     * 时间复杂度：O(n^2)
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        int i, j;
        int insertNode; //要插入的元素的位置
        for (i = 1; i < array.length; i++) {
            insertNode = array[i]; //第i趟时，原数组的第i个位置的数据就是要插入的值
            j = i - 1; //默认插入到前一个位置
            //insertNode和它之前的元素一个个比较，如果前一个元素大于它，
            // 则前一个元素向后移动，直到前一个元素小于它时，此轮比较结束
            while (j >= 0 && insertNode < array[j]) {
                array[j+1] = array[j]; //如果要插入的元素小于第j个元素,就将第j个元素向后移动
                j--;
            }

            //此时j+1的位置和j+1+1的位置的内容相同（原j+1移动到j+1+1了），j+1的位置就是要插入的位置
            array[j+1] = insertNode;
        }
    }

}
