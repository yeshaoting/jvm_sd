package cn.yeshaoting.sd.leetcode.link;

//给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。 
//
// 请你返回该链表所表示数字的 十进制值 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [1,0,1]
//输出：5
//解释：二进制数 (101) 转化为十进制数 (5)
// 
//
// 示例 2： 
//
// 输入：head = [0]
//输出：0
// 
//
// 示例 3： 
//
// 输入：head = [1]
//输出：1
// 
//
// 示例 4： 
//
// 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
//输出：18880
// 
//
// 示例 5： 
//
// 输入：head = [0,0]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 链表不为空。 
// 链表的结点总数不超过 30。 
// 每个结点的值不是 0 就是 1。 
// 
// Related Topics 位运算 链表

/*
    description:
    author: yeshaoting
*/
public class Topic1290 extends BaseLink {

    public static void main(String[] args) {
//        Solution_A solution = new Solution_A();
        Solution_B solution = new Solution_B();

//        ListNode head = stringToListNode("[1,0,1]");
        ListNode head = stringToListNode("[1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]");
        int result = solution.getDecimalValue(head);
        System.out.println(result);
    }

}

class Solution_A {

    public int getDecimalValue(ListNode head) {
        int result = 0;
        ListNode current = head;
        while (current != null) {
            result = (result << 1) + current.val;
            current = current.next;
        }

        return result;
    }
}

class Solution_B {

    public int getDecimalValue(ListNode head) {
        String result = "";
        ListNode current = head;
        while (current != null) {
            result = result + current.val;
            current = current.next;
        }

        return Integer.valueOf(result, 2);
    }
}

