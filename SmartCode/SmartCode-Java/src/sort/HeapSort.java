package sort;

/**
 * 堆是特殊的树，这个树的父节点均大于（或小于）子节点
 * 1.大顶堆：堆顶为最大项
 * 2.小顶堆：堆顶为最小项
 *
 * 若以一维数组存储一个堆，则堆对应一棵完全二叉树，且所有非叶结点（有子女的结点）的值均不大于(或不小于)其子女的值，
 * 根结点（堆顶元素）的值是最小(或最大)的。
 *
 *
 *
 * Created by wangdongdong on 17/7/7.
 */
public class HeapSort {

    //通过一个节点在数组中的索引怎么计算出它的父节点及左右孩子节点的索引？

    /**
     * i位置的左孩子
     * @param i
     * @return
     */
    public static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * i位置的右孩子
     * @param i
     * @return
     */
    public static int right(int i) {
        return 2 * i + 2;
    }

    /**
     * i位置的父节点
     * @param i
     * @return
     */
    public static int parent(int i) {
        // i为根结点
        if (i == 0) {
            return -1;
        }
        return (i - 1) / 2;
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void heapSort(int[] array) {
        //初始建堆，array[0]为第一趟值最大的元素
        array = buildMaxHeap(array);

        //每次adjustDownToUp后，第0个位置都是最大的，因此循环的开始会交换堆顶元素和堆低元素
        for (int i = array.length - 1; i >1; i--) {
            swap(array, 0, i);//将堆顶元素和堆低元素交换，即得到当前最大元素正确的排序位置
            adjustDownToUp(array, 0 ,i);
        }


    }

    /**
     * 构建大根堆：将array看成完全二叉树的顺序存储结构
     * @param array
     * @return
     */
    private static int[] buildMaxHeap(int[] array) {
        //从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点为0，反复调整堆
        for (int i = parent(array.length - 1);
                i >= 0; i--) {
            adjustDownToUp(array, i, array.length);
        }

        return array;
    }

    /**
     * 将元素array[k]自下往上逐步调整树形结构
     * @param array
     * @param k 父节点
     */
    private static void adjustDownToUp(int[] array, int k, int length) {
        int temp = array[k]; //记录当前节点， k也用来记录该节点最终的位置
        for (int i = left(k); i < length - 1; i = left(i)) {//i为初始化为节点k的左孩子，沿节点较大的子节点向下调整

            //比较当前节点的子节点的大小，取大的
            if (i < length && array[i] < array[i+1]) {
                i++; //默认的i为左节点，i++表示右节点
            }

            if (temp > array[i]) {//根节点 >=左右子女中关键字较大者，调整结束
                break;
            } else {
                array[k] = array[i];  //将左右子结点中较大值array[i]调整到双亲节点上
                k = i; //【关键】修改k值，以便继续向下调整
            }
        }
        array[k] = temp;//被调整的结点的值放人最终位置
    }

    public static void main(String[] args) {

    }
}
