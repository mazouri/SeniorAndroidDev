package datastructure.simplecode;

import java.util.HashMap;
import java.util.Stack;

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

    //5.求单链表的中间节点

    /**
     * 求单链表的中间节点
     *
     * 思路：快慢指针，一个每次走2步一个每次走1步，
     *     若链表长度为奇数返回中间值，为偶数返回中间2者的前一者
     * @param head
     * @return
     */
    public static ListNode getMidNode(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null) {
                break;
            }
        }
        return slow;
    }

    //5.1 求单链表的中间节点的值
    public static int getMidNodeValue(ListNode head) {
        ListNode midNode = getMidNode(head);
        return midNode == null ? -1 : midNode.value;
    }

    //6.判断链表是否有环

    /**
     * 判断链表是否有环
     *
     * 可以用一个更生动的例子来形容：在一个环形跑道上，两个运动员在同一地点起跑，
     * 一个运动员速度快，一个运动员速度慢。当两人跑了一段时间，速度快的运动员必然
     * 会从速度慢的运动员身后再次追上并超过，原因很简单，因为跑道是环形的。
     * @param head
     * @return
     */
    public static boolean hasLoop(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    //6.1 使用HashMap
    /**
     * 判断链表是否有环
     *
     * 将每次走过的节点保存到hash表中，如果节点在hash表中，则表示存在环
     * @param head
     * @return
     */
    public static boolean hasLoop2(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode node = head;
        HashMap<ListNode, ListNode> map = new HashMap<>();
        while (node != null) {
            if (map.get(node) == null) {
                map.put(node, node);
            } else {
                return true;
            }

            node = node.next;
            if (node == null) {
                return false;
            }
        }
        return false;
    }

    //7.找出有环链表的环的入口

    /**
     * 找出有环链表的环的入口
     *
     * 定义两个指针stepNode, resetNode。
     * stepNode每走一个结点(即一步)，resetNode则从头一直向后走，
     * 直到resetNode走到NULL或stepNode, resetNode走到同一个结点但走过的步数不相同为止。
     * 此时resetNode的步数就是环入口在结点中的位置。如果走到NULL则说明链表不存在环。
     *
     * 为什么stepNode, resetNode走到同一个结点但步数不相等时就说明有环呢？
     * 因为如果stepNode, resetNode步数相同，说明它们走过的结点是一样的，
     * 如果stepNode, resetNode步数不同了，则说明stepNode是从环里走了一圈又回到了环的入口，
     * 此时resetNode到达该结点时还没有走过环，因此步数不相等，而且此时resetNode的步数就是环的入口。
     *
     * link:http://blog.csdn.net/neosmith/article/details/47185593
     * @param head
     * @return
     */
    public static ListNode getLoopEntry(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode stepNode = head;// 步进的节点
        ListNode resetNode = head;//stepNode每走一个节点，resetNode从头重新走
        int stepIndex = 0; //stepNode的索引：即从head到当前节点一共走了几次
        int resetIndex = 0; //resetNode的节点索引

        //stepNode开始走
        while (stepNode.next != null) {
            stepNode = stepNode.next; // stepNode走一步
            stepIndex++;

            while (resetNode.next != null) {
                resetNode = resetNode.next;
                resetIndex++;

                //当走到同一个节点
                if (resetNode == stepNode) {
                    if (resetIndex != stepIndex) {
                        return resetNode;
                    } else {
                        break; //步数相同，不是入口
                    }
                }
            }

            //一个节点判断完了，走下一个节点
            resetNode = head;
            resetIndex = 0;
        }

        // stepNode走到了null, 其中有一个指针走到了头，说明没有环
        //如果有环，stepNode不会走到null，会一直在while中
        return null;
    }

    //8.判断两个单链表是否相交

    /**
     * 判断两个单链表是否相交
     *
     * 思路：若两链表相交，则两链表的尾节点肯定是同一节点。
     *      则两个链表都走到最后，判断最后的节点是否相同即可判断是否相交
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isJoin(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            //如果有一个链表为空，则不相交
            return false;
        }

        ListNode node1 = head1;
        //链表1的最后一个节点
        while (node1.next != null) {
            node1 = node1.next;
        }

        ListNode node2 = head1;
        //链表2的最后一个节点
        while (node2.next != null) {
            node2 = node2.next;
        }
        return node1 == node2;
    }

    //9.找出两个相交链表的第一个交点

    /**
     * 找出两个相交链表的第一个交点
     *
     * 思路: 相交的两个链表的长度差即为相交前的长度差
     *     先让长的链表的指针先走长度差的距离，然后两个指针一起走，相遇的地方便是交点的开始处。
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode getFirstJoinNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null || head1 == head2) { //相同也没有交点
            return null;
        }

        ListNode node1 = head1;
        int len1 = 1; //有head节点，因此至少有一个
        while (node1.next != null) {
            len1++;
            node1 = node1.next;
        }

        ListNode node2 = head2;
        int len2 = 1;
        while (node2.next != null) {
            len2++;
            node2 = node2.next;
        }

        //判断哪个链表长，让长的先走"长度差"的步数
        if (len1 > len2) {
            int delta = len1 -len2;
            while (delta-- > 0) {
                node1 = node1.next;
            }
        } else {
            int delta = len2 - len1;
            while (delta-- > 0) {
                node2 = node2.next;
            }
        }

        //此时两个链表走到距离交点相同距离的位置
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }

        //此时node1 == node2
        return node1;
    }

    //10.从尾到头打印单链表

    /**
     * 从尾到头打印单链表
     *
     * 思路：对于"逆序"的问题：
     *      首先想到 栈 ：先入后出 LIFO 用到了Stack的push,pop 和 empty方法
     *      要么就用递归，也是入栈出栈的思想，入到最深层后，一个个出栈打印
     *
     * @param head
     */
    public static void printReverseList(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode node = head;
        Stack<ListNode> nodeStack = new Stack<>();

        //入栈
        while (node != null) {
            nodeStack.push(node);
            node = node.next;
        }

        //出栈
        while (!nodeStack.empty()) {
            ListNode popNode = nodeStack.pop();
            //打印
            System.out.println(popNode.value);
        }
    }

    //10.1 利用递归（递进归来兮） 从尾到头打印单链表

    public static void printReverseList2(ListNode head) {
        //节点为null时开始出栈（归来）
        if (head == null) {
            return;
        }
        //先解决子问题（递进）
        printReverseList2(head.next);

        //每个问题所需处理的内容
        //打印当前节点的值
        System.out.println(head.value);
    }

    //11.单链表的逆序

    /**
     * 单链表的逆序
     *
     * 关键在新node读取节点后，旧链表要先移动
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        //如果链表为null或者只有一个节点，直接返回，不用逆序
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pointer = head;
        System.out.println(pointer + "\n" + head);

        //创建一个节点，作为新链表目前的head;
        //之后会依次创建新链表的前一个节点，来实现逆序
        ListNode newHead = null;
        while (pointer != null) {
            ListNode preNode = pointer; //读取旧链表，从旧链表读的节点作为新链表的前一个节点
            pointer = pointer.next; //旧链表移动，一定要先指向下一个

            preNode.next = newHead; //关联新链表
            newHead = preNode; //新链表移动
        }

        return newHead;
    }

    //11.1 使用递归

    /**
     * 对于递归，要有 递进，归来，处理子问题，返回 这四步.
     *
     * link:http://blog.csdn.net/guyuealian/article/details/51119499
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        //2.归来
        if (head == null || head.next == null) {
            return head;
        }

        //1.递进：递进到最后一个节点，作为反序后的新head
        ListNode reHead = reverseList2(head.next);

        //3.处理子问题，此时head是当前节点，当前节点的下一个节点需要指向自己形成反序，
        // 而当前节点需要置空，表示为新链表的尾部
        head.next.next = head;
        head.next = null;

        //4.返回新链表
        return reHead;
    }

    //12.合并两个有序链表，使合并后的链表依然有序

    /**
     * 合并两个有序链表，使合并后的链表依然有序
     *
     * 归并排序
     *
     * 有迭代和递归两种思路
     *
     * 思路：先确定合并后的头节点，
     *      然后对比两个链表的每一个节点值，若有个链表没有合并完就直接加在后面就可以了
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode mergeSortedList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        //1.先确定合并后链表的头节点
        ListNode mergeHead = null;
        if (head1.value < head2.value) {
            mergeHead = head1;
            head1 = head1.next; //head1已经合并，跳过

            mergeHead.next = null; // 目前仅确定了头节点，断开
        } else {
            mergeHead = head2;
            head2 = head2.next; //head1已经合并，跳过

            mergeHead.next = null; // 目前仅确定了头节点，断开
        }

        ListNode mergeCurNode = mergeHead;
        //循环内部需要比较当前节点的值， 因此循环条件是当前节点是否为空
        while (head1 != null && head2 != null) {
            //循环内，两两比较，将小的值添加到新链表上跳过合并的节点
            if (head1.value < head2.value) {
                mergeCurNode.next = head1; //添加到新链表

                head1 = head1.next;//旧链表跳过
                mergeCurNode = mergeCurNode.next; //新链表后移
                mergeCurNode.next = null;
            } else {
                mergeCurNode.next = head2;

                head2 = head2.next;
                mergeCurNode = mergeCurNode.next;
                mergeCurNode.next = null;
            }
        }

        //到这个位置时，至少有一个链表已经遍历完了
        //需判断是否存在剩余
        if (head1 != null) {
            mergeCurNode.next = head1;
        } else if (head2 != null) {
            mergeCurNode.next = head2;
        }
        return mergeHead;
    }

    /**
     * 使用递归
     *
     * 迭代是从前往后排
     * 递归是从后往前排，即确定了下一个节点后，再确定当前节点
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode mergeSortedList2(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        ListNode mergeHead; //新链表的当前节点
        if (head1.value < head2.value) {
            mergeHead = head1; //当前节点赋值
            mergeHead.next = mergeSortedList2(head1.next, head2); //下一个节点的赋值
        } else {
            mergeHead = head2;
            mergeHead.next = mergeSortedList2(head1, head2.next);
        }
        return mergeHead;
    }

    //13.在O(1)的时间复杂度删除单链表中指定的某一节点

    /**
     * 对于删除节点，我们普通的思路就是让该节点的前一个节点指向该节点的下一个节点
     * ，这种情况需要遍历找到该节点的前一个节点，时间复杂度为O(n)。
     *
     * 对于链表，
     * 链表中的每个节点结构都是一样的，所以我们可以把该节点的下一个节点的数据复制到该节点
     * ，然后删除下一个节点即可。
     *
     * 需要考虑到的一点是，如果要删除的节点是链表的尾节点的话，那还是需要从头结点按照顺序遍历到尾节点的前一节点，
     * 然后删除尾节点，总的平均时间复杂度就是[(n-1)*1+O(n)]/n,结果还是O(1)
     *
     * link:http://www.cnblogs.com/gl-developer/p/6532643.html
     * @param head
     * @param toDelNode
     */
    public static void deleteNode(ListNode head, ListNode toDelNode) {
        if (toDelNode == null) {
            return;
        }

        //toDelNode是尾节点时，需要顺序遍历尾节点的前一个节点
        if (toDelNode.next == null) {
            //找到倒数第二个节点
            while (head.next != toDelNode){
                head = head.next;
            }
            head.next = null;//如果下一个节点是toDelNode，next置空，即删除了toDelNode节点
        } else if (head == toDelNode) { //是头节点
            head = null;
        } else { //是非头尾的节点,复制后一个节点
            toDelNode.value = toDelNode.next.value;
            toDelNode.next = toDelNode.next.next;
        }
    }

    //14.无序链表排序

    /**
     * 无序链表排序
     *
     * 归并排序
     *
     * 先分成两段（找到中间节点），分别排序，在合并两段
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        //没有节点，或者当前节点是最后一个
        if (head == null || head.next == null) {
            return head;
        }

        // 快慢指针找到中间节点
        // 然后截断
        ListNode fast = head;
        ListNode slow = head;
        ListNode preSlow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            preSlow = slow;  //先取slow，slow再向后移动
            slow = slow.next;
        }
        preSlow.next = null;//此时原链表(head)只剩前一半，而slow代表后一半

        //分段后递进到子问题，也就是两个已排序的链表进行排序
        ListNode first = sortList(head); //first得到排序好的前半段
        ListNode second = sortList(slow); //second得到排序好的后半段

        //得到两个排序好的链表后，再对有序列表进行排序-> 见12.mergeSortedList
        ListNode mergeHead = new ListNode(-1); //创建一个假的头节点，真正合并的头节点是mergeHead.next
        ListNode mergeCurNode = mergeHead;
        //链表都不为null时进行比较
        while (first != null && second != null) {
            if (first.value < second.value) {
                mergeCurNode.next = first;
                first = first.next;
            } else {
                mergeCurNode.next = second;
                second = second.next;
            }

            mergeCurNode = mergeCurNode.next; // 当节点赋值好后，移动
        }

        //是否还有剩余
        if (first != null) {
            mergeCurNode.next = first;
        }
        if (second != null) {
            mergeCurNode.next = second;
        }

        return mergeHead.next;
    }

    //15.链表首尾交叉排序

    /**
     * 链表首尾交叉排序
     *
     * 思路：链表断为两端，后段逆序，再将两段交叉排序
     *      断为两段：快慢指针
     *
     * @param head
     * @return
     */
    /*public static ListNode reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //找到中间节点，断开
        ListNode fast = head;
        ListNode slow = head;
        ListNode preSlow = head;
        while (fast != null && fast.next != null) {
            preSlow = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        preSlow.next = null;

        //现在就有前半段head和后半段slow
        //逆序后半段 11.单链表的逆序 reverseList
        //此处用迭代的方式
        ListNode preNode = null;
        while (slow != null) {
            ListNode newHead = slow;
            slow = slow.next;

            newHead.next = preNode;
            preNode = newHead;
        }
        //slow遍历完后，得到一个preNode的链表
        //即为slow的逆序链表

        //此时得到前半段的head和后半段逆序的preNode
//        System.out.println("len(head):" + len(head));
//        System.out.println("len(preNode):" + len(preNode));
//        System.out.println("head.toString:" + head.toString());
//        System.out.println("preNode.toString:" + preNode.toString());
        //将这两个交叉合并即可
        ListNode curNode = head;
        ListNode mergeNode = curNode;
        while (curNode != null && preNode != null) {
            ListNode tmp = curNode.next;
            curNode.next = preNode;
            preNode = preNode.next;
            curNode.next.next = tmp;
            curNode = tmp;
            tmp = curNode.next;
        }

        return mergeNode;
    }*/

    public static ListNode reorderList2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 找到中间节点
        while(fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode preReverse = slow; // preReverse不用翻转，因为它永远在最后一个
        if(preReverse == null)
            return head;

        // 翻转后半段:preReverse不用翻转，因为它永远在最后一个,因此它后面的作为后半段
        ListNode reHead = preReverse.next;
        if(reHead == null)
            return head;

        ListNode preCur = reHead.next;
        ListNode cur = reHead.next;
        reHead.next = null;
        while(cur != null) {
            cur = cur.next;
            preCur.next = reHead;
            reHead = preCur;
            preCur = cur;
        }
        preReverse.next = reHead;

        // 交叉合并两个链表
        preReverse.next = null;     // 断开前半段和翻转后的后半段元素
        cur = head;
        while(reHead != null && cur!=null) {
            ListNode tmp = cur.next;
            cur.next = reHead;
            reHead = reHead.next;
            cur.next.next = tmp;
            cur = tmp;
//            tmp = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(2);
        head2.next.next.next = new ListNode(3);
        head2.next.next.next.next = new ListNode(4);
        head2.next.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next.next = new ListNode(6);
        //[0->1->2->3->4->5->6]
//        System.out.println(getLastKValue(head, 7));
//        reverseList(head);

//        ListNode temp = head;
//        System.out.println(temp+"\n"+head);
//        head = head.next;
//        temp.next = new ListNode(5);
//        System.out.println(temp.next.value+"\n" + head.next.value);

//        System.out.println(mergeSortedList(head, head2).value);

        ListNode reorderList = reorderList2(head);
//        System.out.println("reorderList.toString:" + reorderList.toString());
        System.out.println(reorderList.value);
        System.out.println(reorderList.next.value);
        System.out.println(reorderList.next.next.value);
        System.out.println(reorderList.next.next.next.value);
        System.out.println(reorderList.next.next.next.next.value);
        System.out.println(reorderList.next.next.next.next.next.value);
        System.out.println(reorderList.next.next.next.next.next.next.value);
    }

}
