package datastructureforleetcode;

/**
 * Created by wangdongdong on 17/7/6.
 */
public class ListNode {

    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        //print value range from current node to end.
        StringBuilder sb = new StringBuilder("(");
        ListNode node = this;
        do {
            if (node == this) {
                sb.append("->");
            }
            sb.append(node.value);
            node = node.next;
        } while (node != null);

        sb.append(")");
        return sb.toString();


    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);

        System.out.println(head.toString()); //(->012345)

        ListNode pre = head;
        pre.next.next = null;

        System.out.println(head.toString()); //(->01)
        System.out.println(pre.toString());//(->01)



    }
}
