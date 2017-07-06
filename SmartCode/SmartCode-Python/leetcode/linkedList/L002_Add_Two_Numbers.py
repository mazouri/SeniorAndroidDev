class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

    def toString(self):
        ret = "->"
        node = self
        while node:
            ret += str(node.val)
            node = node.next
        return ret


class AddTwoNumbers:
    """
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
    """

    @staticmethod
    def addTwoNumbers(l1, l2):
        carry = 0
        head = pre_node = ListNode(0)

        while l1 or l2 or carry:
            # use conditional expressions
            # 1.<expression1> if <condition> else <expression2>
            # 2.if <condition>: <expression1> else: <expression2>
            # v1 = l1.val if l1 else 0
            # v2 = l2.val if l2 else 0
            v1 = v2 = 0
            if l1:
                v1 = l1.val
            if l2:
                v2 = l2.val

            sum_val = v1 + v2 + carry

            # a除以b，然后返回商与余数的元组
            carry, val = divmod(sum_val, 10)
            pre_node.next = ListNode(val)
            pre_node = pre_node.next
            l1 = l1.next if l1 else l1
            l2 = l2.next if l2 else l2
        return head.next

if __name__ == '__main__':
    l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next.next = ListNode(3)
    l2 = ListNode(5)
    l2.next = ListNode(6)
    l2.next.next = ListNode(4)
    print("l1+l2:" + AddTwoNumbers.addTwoNumbers(l1, l2).toString())

