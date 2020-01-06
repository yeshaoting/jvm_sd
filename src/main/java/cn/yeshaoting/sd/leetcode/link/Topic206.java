package cn.yeshaoting.sd.leetcode.link;

import com.google.common.collect.Lists;

import java.util.List;

/*
    description:
    author: yeshaoting
    time: 2019-08-15 13:01
*/
public class Topic206 {

    public static void main(String[] args) {
        List<String> all = Lists.newArrayList("[1,2,3,4,5]", "[1]", "[1,2,3,4,5,6]", "[1,2]", "[1,2,3]");
        for (String value : all) {
            ListNode head = BaseLink.stringToListNode(value);
            ListNode result = new Solution().reverseList(head);
            System.out.printf("%s\t%s\n", result.val, value);
        }
    }

    static class Solution {

        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return null;
            }

            return reverseNode(head, head.next);
        }

        public ListNode reverseNode(ListNode pre, ListNode cur) {
            if (cur == null) {
                return pre;
            }

            ListNode tail = reverseNode(cur, cur.next);
            cur.next = pre;
            pre.next = null;
            return tail;
        }
    }

    static class Solution2 {

        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            return pre;
        }

    }
}