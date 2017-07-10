package simplecode;

import java.util.List;

/**
 * 关于　链表　的常见问题汇总
 *
 * Created by wangdong on 17-7-10.
 */
public class LinkedListSimpleCode {

    //1.(单)链表(节点)的代码表示
    static class ListNode{
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
            this.next = null;
        }
    }

    //2.链表长度

    /**
     * 链表长度
     * 思路：遍历一边链表
     * @param head
     * @return
     */
    public static int getLength(ListNode head) {
        //边界判断
        if (head == null) {
            return 0;
        }

        int len = 0;
        while (head.next != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    //方法2 递归
    public static int len(ListNode head) {
        if (head == null) {
            return 0;
        }

        return 1 + len(head.next);
    }

    //3.得到链表倒数第k个节点的值

    /**
     * 得到链表倒数第k个节点的值
     *
     * 思路：因为倒数第k个和最后一个相距k-1个节点，故采用前后指针，第一个先走k-1步，即走到第k个,
     * (链表我习惯从1开始计算)然后两个指针在同时走，当前指针p走到末尾时，后指针q的位置刚好是倒数第k个节点
     *
     * 类似于，我和你同时走一个100m的跑到，你先走30m，
     * 然后咱两再一起走，等你到终点的时候，我离终点还有30m
     * 这样也就确定了终点倒数30m的位置了
     *
     * @param head
     * @param k
     * @return
     */
    public static int getLastKValue(ListNode head, int k) {
        if (head == null || k <=0) {
            return -1;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (--k > 0) {
            fast = fast.next;
            if (fast.next == null) {
                break;
            }
        }

        //如果fast.next为null，说明k>=链表长度,
        //在这里，超出的时候我也返回了第一个节点的值
        if (fast.next == null) {
            return head.value;
        } else {
            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow.value;
        }
    }

    //4.删除链表的倒数第k个节点

    /**
     * 删除链表的倒数第k个节点
     *
     * 思路：和上面相比就是要删除倒数第k个，那么就需要记录后指针的前一节点，
     * 因为删除链表的本质就是它的前一节点指向它的后一节点
     * @param head
     * @param k
     * @return
     */
    public static ListNode removeLastKValue(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (--k > 0) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
        }

        if (fast.next == null) {
            return head.next;
        } else {
            //用于记录删除的节点的前一个节点
            ListNode pre = slow;
            while (fast.next != null) {
                pre = slow;
                fast = fast.next;
                slow = slow.next;
            }
            //此时pre在slow的前一个位置，slow在k的位置
            //删除k的位置就是让k前的节点指向k后的节点
            pre.next = slow.next;
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        //[0->1->2->3->4->5->6]
        System.out.println(getLastKValue(head, 7));
    }

}
