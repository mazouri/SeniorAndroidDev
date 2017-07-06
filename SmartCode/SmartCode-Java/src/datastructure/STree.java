package datastructure;

/**
 * 树
 *
 * Created by wangdongdong on 17/7/5.
 */
public class STree {

    private STreeNode root;

    /**
     * 树的节点类
     */
    static class STreeNode {
        int data; // data used as key value
        double fdata; // other data
        STreeNode leftChild;
        STreeNode rightChild;

        public void displayNode() {}
    }

    /**
     * 查找节点
     *
     * 查找的时间复杂度 O(log n)
     *
     * @param key
     * @return 如果返回null，表示没有找到节点
     */
    public STreeNode find(int key) {
        STreeNode current = root; //current 正在查找的节点；从根开始
        while (current != null && current.data != key) {
            if(key < current.data) { //向左查
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return current;
    }

    public void insert(int key, double data) {
        STreeNode newNode = new STreeNode();
        newNode.data = key;
        newNode.fdata = data;
        if (root == null) {
            root = newNode;
        } else {
            STreeNode current = this.root;
            STreeNode parent;
            while (true) { //一直查找直到找到null节点为止
                parent = current; // 记录遇到的最后一个不是null的节点作为父节点
                if (key < current.data) { //向左查
                    current = current.leftChild;
                    if (current == null) { //找到null为止
                        parent.leftChild = newNode;
                        return;
                    }
                } else {  //向右查
                    current = current.rightChild;
                    if (current == null) { //找到null为止
                        parent.rightChild = newNode;
                        return;
                    }
                }

            }
        }
    }
    public void delete(int key) {}
    // other methods.

    /*遍历*/

    /**
     * 中序遍历（左－根－右）：对于每个节点，都是先遍历它的左子树，再访问自己，然后遍历它的右子树
     */
    public void inOrder() {
        inOrderInternal(root);
    }

    private void inOrderInternal(STreeNode node) {
        if (node != null) {  //遍历一个路径到null时结束
            inOrderInternal(node.leftChild); //当node.leftChild为null时什么也不做
            //TODO do what you want.below is print current node key
            System.out.println("inOrderInternal:" + node.data);
            inOrderInternal(node.rightChild);
        }
    }

    /**
     * 前序遍历（根－左－右）：对于每个节点，都是先访问自己，再遍历它的左子树，然后遍历它的右子树
     */
    public void preOrder() {
        preOrderInternal(root);
    }

    private void preOrderInternal(STreeNode node) {
        if (node != null) {  //遍历一个路径到null时结束
            //TODO do what you want.below is print current node key
            System.out.println("inOrderInternal:" + node.data);
            inOrderInternal(node.leftChild); //当node.leftChild为null时什么也不做
            inOrderInternal(node.rightChild);
        }
    }

    /**
     * 前序遍历（左－右-根）：对于每个节点，都是先访问自己，再遍历它的左子树，然后遍历它的右子树
     */
    public void postOrder() {
        postOrderInternal(root);
    }

    private void postOrderInternal(STreeNode node) {
        if (node != null) {  //遍历一个路径到null时结束
            inOrderInternal(node.leftChild); //当node.leftChild为null时什么也不做
            inOrderInternal(node.rightChild);
            //TODO do what you want.below is print current node key
            System.out.println("inOrderInternal:" + node.data);
        }
    }

    /**
     * 查找二叉搜索树的最小值
     */
    public STreeNode minimum() {
        STreeNode current = root;
        STreeNode parent = null;
        while (current != null) {
            parent = current;
            current = current.leftChild;
        }
        return parent;
    }

    /**
     * 查找二叉搜索树的最大值
     */
    public STreeNode maximum() {
        STreeNode current = root;
        STreeNode parent = null;
        while (current != null) {
            parent = current;
            current = current.rightChild;
        }
        return parent;
    }

}
