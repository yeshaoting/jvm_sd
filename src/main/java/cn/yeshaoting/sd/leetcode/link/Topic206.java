package cn.yeshaoting.sd.leetcode.link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    description:
    author: yeshaoting
    time: 2019-08-15 13:01
*/
public class Topic206 {

    public static void main(String[] args) throws IOException {
        String line = "[1,2,3,4,5]";
        ListNode head = BaseLink.stringToListNode(line);

        ListNode ret = new Solution206_A().reverseList(head);
        String out = BaseLink.listNodeToString(ret);
        System.out.print(out);
    }

}

class Solution206_A {

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

class Solution206_B {

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