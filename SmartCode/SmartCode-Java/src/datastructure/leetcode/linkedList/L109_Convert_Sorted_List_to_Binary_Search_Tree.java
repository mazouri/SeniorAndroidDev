package datastructure.leetcode.linkedList;

import datastructure.datastructureforleetcode.ListNode;
import datastructure.datastructureforleetcode.TreeNode;

/**
 *
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * 给定一个升序排列的单链表，转换为二叉搜索树
 *
 * 思路：两种方式：
 *  1.自上而下
 *  2.自下而上
 *
 * Created by wangdongdong on 17/7/6.
 */
public class L109_Convert_Sorted_List_to_Binary_Search_Tree {

    /*** 方式一：自上而下***/

    public static TreeNode sortedListToBST(ListNode head) {
        //如果单链表为null，则返回一个null的子树
        if (head == null) {
            return null;
        }

        //如果是单链表尾节点，返回一个只有一个节点的子树
        if (head.next == null) {
            return new TreeNode(head.value);
        }

        //找到具有多个节点的单链表的中间节点
        ListNode midNode = findAndCutMidNode(head);
        //调用findAndCutMidNode方法后，head链表仅剩前一半，见main()

        //中间的节点作为树的根节点
        TreeNode root = new TreeNode(midNode.value);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(midNode.next);
        return root;
    }

    /**
     * "快慢指针"找单链表的中间节点
     * @param listNode
     * @return
     */
    private static ListNode findAndCutMidNode(ListNode listNode) {
        //如果节点为null，当然就没有中间节点
        if (listNode == null) {
            return null;
        }

        // 变量pslow记录截断了的链表
        ListNode slow = listNode, fast = listNode, pslow = listNode;

        //单链表长度为奇数时，fast==null跳出循环，为偶数时，fast.next==null跳出循环
        while (fast != null && fast.next != null) {
            pslow = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        //截断listNode链表
        pslow.next = null;
        return slow;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next = new ListNode(2);
        head.next = new ListNode(3);
        head.next = new ListNode(4);
        head.next = new ListNode(5);


    }
}
