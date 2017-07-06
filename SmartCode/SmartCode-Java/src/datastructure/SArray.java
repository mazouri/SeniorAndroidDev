package datastructure;

/**
 * 数组
 * Created by wangdongdong on 17/7/1.
 */
public class SArray<T> {

    private Object array[];
    private int elementsSize;
    private int maxSize;

    public SArray(int maxSize) {
        this.maxSize = maxSize;
        array = new Object[maxSize];
    }

    public void add(T value) {
        if (elementsSize > maxSize) {
            throw new ArrayIndexOutOfBoundsException();
        }

        array[elementsSize] = value;
        elementsSize++;
    }

    public T delete(int index) {
        if (index >= elementsSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T oldValue = (T) array[index];

        for (int i = index; i < elementsSize; i++) {
            array[i] = array[i + 1];
        }
        elementsSize--;
        return oldValue;
    }

    public T update(int index, T value) {
        if (index > maxSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldValue = (T) array[index];
        array[index] = value;
        return oldValue;
    }

    public T get(int index) {
        if (index > maxSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return (T) array[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < maxSize; i++) {
            sb.append(" ").append(array[i]);
        }
        sb.append(" ]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SArray sArray = new SArray<Integer>(10);
        sArray.add("s");
        sArray.add("s");
        sArray.add("ss");

        System.out.println(sArray.toString());
        System.out.println(sArray.get(1));

    }
}
