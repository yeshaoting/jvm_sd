package cn.yeshaoting.sd.leetcode.link;

import cn.yeshaoting.sd.leetcode.BaseCommon;

/*
    description:
    author: yeshaoting
    time: 2019-08-15 12:59
*/
public class BaseLink extends BaseCommon {

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = BaseCommon.stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

}
