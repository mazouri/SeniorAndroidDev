package datastructure.leetcode.linkedList;

import datastructure.datastructureforleetcode.ListNode;

/**
 * Created by wangdongdong on 17/7/2.
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * 输入为两个非空链表代表两个非负的整数。这些整数以逆序的形式存储，并且每个元素包含一个一位数。将这两个整数相加得到一个新的链表并返回。
 * 可以假定两个整数没有前导0，除了整数0本身。
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 * 用链表运算来表示加法，即342+465=807
 *
 * link:https://leetcode.com/articles/add-two-numbers/
 */
public class L002_Add_Two_Numbers {

    /**
     * 1.当前节点为null时，数值用0代替
     * 2.当2个节点都计算完后，要看是否还有进位
     *
     * 边界：1.长度不一样：123和45
     *      2.最后一个相加后，是否还有进位：比如123和678
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //先创建一个Head节点，也是新链表的最前的节点
        ListNode head = new ListNode(0);
        ListNode preNode = head;
        //进位
        int carry = 0;

        //两个链表有可能长度不一样，因此需要考虑一个链表走到头时，需继续计算；
        //另外在两个链表都走到头后，还要注意是否还有进位
        while (l1 != null || l2 != null || carry != 0) {
            int sum = (l1 == null ? 0 : l1.value)  //数值1
                    + (l2 == null ? 0 : l2.value)  //数值2
                    + carry;                       //进位（0或1）
            preNode.next = new ListNode(sum % 10); //取余得到新节点的值
            preNode = preNode.next;
            carry = sum / 10; //是否有进位，有则为1；无则为0

            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        //创建342
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        //创建465
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(addTwoNumbers(l1, l2));
    }

//    static class ListNode {
//        int value;
//        ListNode next;
//
//        public ListNode(int value) {
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            //print value range from current node to end.
//            StringBuilder sb = new StringBuilder("(");
//            ListNode node = this;
//            do {
//                if (node == this) {
//                    sb.append("->");
//                }
//                sb.append(node.value);
//                node = node.next;
//            } while (node != null);
//
//            sb.append(")");
//            return sb.toString();
//
//
//        }
//    }
}
