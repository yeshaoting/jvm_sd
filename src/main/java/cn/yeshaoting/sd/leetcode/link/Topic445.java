package cn.yeshaoting.sd.leetcode.link;
//给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
//
// 
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 进阶: 
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。 
//
// 示例: 
//
// 
//输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出: 7 -> 8 -> 0 -> 7
// 
//

import cn.yeshaoting.sd.leetcode.ListNode;

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Topic445 {

    // 借助栈实现
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack();
        Stack<ListNode> stack2 = new Stack();

        // 入栈
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        // 循环出栈，结果加和
        int carry = 0;
        ListNode root = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            ListNode tmp1 = stack1.isEmpty() ? new ListNode(0) : stack1.pop();
            ListNode tmp2 = stack2.isEmpty() ? new ListNode(0) : stack2.pop();

            int sum = tmp1.val + tmp2.val + carry;
            ListNode node = new ListNode(sum % 10);
            node.next = root;
            root = node;
            carry = sum / 10;
        }

        return root;
    }
}