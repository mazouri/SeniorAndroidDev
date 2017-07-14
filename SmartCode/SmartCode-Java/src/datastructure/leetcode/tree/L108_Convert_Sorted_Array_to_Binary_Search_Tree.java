package datastructure.leetcode.tree;

import datastructure.datastructureforleetcode.TreeNode;

/**
 * Created by wangdongdong on 17/7/5.
 * <p>
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * 给定一个升序数组，将它转换为平衡的二叉搜索树
 * <p>
 * 思路：
 * 给定一个区间[left, right]，取其中值mid=(left+right)/2对应的元素作为二叉树的根。
 * 二叉树的左子树根的值为对[left, mid-1]继续操作的结果，右子树类似。
 * <p>
 * 这道题是二分查找树的题目，要把一个有序数组转换成一颗二分查找树。其实从本质来看，
 * 如果把一个数组看成一棵树（也就是以中点为根，左右为左右子树，依次下去）数组就等价于一个二分查找树。
 * 所以如果要构造这棵树，那就＊＊把中间元素转化为根，然后递归构造左右子树＊＊。所以我们还是用二叉树递归的方法来实现，
 * 以根作为返回值，每层递归函数取中间元素，作为当前根和赋上结点值，然后左右结点接上左右区间的递归函数返回值。
 * 时间复杂度还是一次树遍历O(n)，总的空间复杂度是栈空间O(logn)加上结果的空间O(n)，额外空间是O(logn)，总体是O(n)
 * <p>
 *
 * <p>
 *     二叉搜索树:一个节点的左子节点的关键字值小于这个节点，右子节点的关键字值大于或等于这个父节点
 * </p>
 * <p>
 * link:https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/#/description
 */
public class L108_Convert_Sorted_Array_to_Binary_Search_Tree {

//    public static class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//
//        TreeNode(int x) {
//            val = x;
//        }
//    }

    public static TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode head = helper(num, 0, num.length - 1);
        return head;
    }

    public static TreeNode helper(int[] num, int low, int high) {
        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);
        return node;
    }


    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9};
        TreeNode treeNode = sortedArrayToBST(array);

//                   5
//                /    \
//              2        7
//            /   \     /  \
//          1      3   6    8
//                  \        \
//                   4        9

        System.out.println("root:" + treeNode.val);
        System.out.println("root left:" + treeNode.left.val);
        System.out.println("root right:" + treeNode.right.val);
    }
}
