package cn.yeshaoting.sd.leetcode.link;
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
//

import cn.yeshaoting.sd.leetcode.ListNode;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Topic2 extends BaseLink {

    public static void main(String[] args) {
        List<Pair<String, String>> all = Lists.newArrayList(
                Pair.of("[2,4,3]", "[5,6,4]"),
                Pair.of("[2,4]", "[5,6,4]"),
                Pair.of("[2,4,3]", "[5,6]"),
                Pair.of("[5]", "[5]")
        );

        for (Pair<String, String> pair : all) {
            ListNode l1 = stringToListNode(pair.getKey());
            ListNode l2 = stringToListNode(pair.getValue());
            ListNode ret = new Solution().addTwoNumbers(l1, l2);
            String out = listNodeToString(ret);
            System.out.printf("l1=%s\tl2=%s\tout=%s\n", pair.getKey(), pair.getValue(), out);
        }
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = l1;
            ListNode pre = null;
            int carry = 0;
            while (l1 != null || l2 != null || carry != 0) {
                // 整理node关系
                ListNode l1Node = l1 == null ? new ListNode(0) : l1;
                ListNode l2Node = l2 == null ? new ListNode(0) : l2;
                if (l1 == null) {
                    pre.next = l1Node;
                }

                // 处理当前次数值变化
                int sum = l1Node.val + l2Node.val + carry;
                l1Node.val = sum % 10;
                carry = sum / 10;

                // 下次迭代控制
                pre = l1Node;
                l1 = l1Node.next;
                l2 = l2Node.next;
            }

            return head;
        }
    }

    // FIXME the solution
    static class Solution2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = l1;
            ListNode pre = null;
            int carry = 0;
            while (l1 != null || l2 != null || carry != 0) {
                // 整理node关系
                ListNode l1Node = l1 == null ? new ListNode(0) : l1;
                ListNode l2Node = l2 == null ? new ListNode(0) : l2;
                if (l1 == null) {
                    pre.next = l1Node;
                }

                // 处理当前次数值变化
                int sum = l1Node.val + l2Node.val + carry;
                l1Node.val = sum % 10;
                carry = sum / 10;

                // 下次迭代控制
                pre = l1Node;
                l1 = l1Node.next;
                l2 = l2Node.next;
            }

            return head;
        }
    }

}
