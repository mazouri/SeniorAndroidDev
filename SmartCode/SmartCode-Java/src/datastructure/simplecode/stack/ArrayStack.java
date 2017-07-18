package datastructure.simplecode.stack;

import java.util.Arrays;

/**
 * 使用数组实现栈
 *
 * 属性：默认容量、元素的个数(栈顶取size-1)、容量、元素数组
 *
 * Created by wangdongdong on 17/7/17.
 */
public class ArrayStack<E> implements IStack<E> {
    private final int DEFAULT_SIZE = 5; //默认容量
    private int size = 0; //元素个数
    private int capacity = 0; //栈的容量

    private Object[] array;//元素数组

    public ArrayStack() {
        this.capacity = DEFAULT_SIZE;
        this.array = new Object[this.capacity];
        this.size = 0;
    }

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.array = new Object[this.capacity];
        this.size = 0;
    }

    @Override
    public void push(E element) {
        if (isFull()) { //如果栈满则扩容
            enlarge();
        }

        size++;
        array[size - 1] = element;
    }

    private void enlarge() {
        this.capacity = this.capacity * 2; //扩容2倍
        Object[] newArray = new Object[this.capacity]; //定义新数组
        System.arraycopy(array, 0, newArray, 0, array.length); //拷贝原数组到新数组
        Arrays.fill(array, null); //原数组内容置空
        this.array = newArray; //赋值新数组
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        E element = (E) this.array[size - 1];
        this.array[size -1] = null;
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        return (E) this.array[size -1];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        this.size = 0;
        this.capacity = DEFAULT_SIZE;
        array = new Object[this.capacity];
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println(stack.isEmpty()); //false
        System.out.println(stack.peek()); //6
        System.out.println(stack.getSize()); //6
        System.out.println(stack.pop()); //6
        System.out.println("after pop :" + stack.getSize()); //after pop :5
        stack.clear();
        System.out.println("after clear :" + stack.getSize()); //after clear :0
    }

}
