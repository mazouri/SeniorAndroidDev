package datastructure.simplecode.stack;

/**
 * 定义栈的功能，包括入栈、出栈、获取栈顶元素、栈中元素个数、判断是否为空、判断是否栈满、清空栈
 *
 * Created by wangdongdong on 17/7/17.
 */
interface IStack<E> {

    public void push(E element);

    public E pop();

    public E peek();

    public int getSize();

    public boolean isEmpty();

    public boolean isFull();

    public void clear();
}