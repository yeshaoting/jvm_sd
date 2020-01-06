package cn.yeshaoting.sd.leetcode.link;

//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表

import cn.yeshaoting.sd.leetcode.ListNode;
import com.google.common.collect.Lists;

import java.util.List;

/*
    description:
    author: yeshaoting
*/
public class Topic83 {

    public static void main(String[] args) {
        List<String> all = Lists.newArrayList("[]", "[1,1,2]", "[1]", "[1,1]", "[1,2,2,3,4,4]", "[1,2,3]");
        for (String value : all) {
            ListNode head = BaseLink.stringToListNode(value);
            ListNode result = new Solution().deleteDuplicates(head);
            System.out.printf("result=%s\tvalue=%s\n", BaseLink.listNodeToString(result), value);
        }
    }

    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode current = head;
            ListNode next = current.next;
            while (next != null) {
                if (current.val == next.val) {
                    current.next = next.next;
                    next = next.next;
                } else {
                    current = current.next;
                }
            }

            return head;
        }
    }

}

