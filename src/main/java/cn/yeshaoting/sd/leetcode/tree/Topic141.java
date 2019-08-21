//给定一个链表，判断链表中是否有环。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。 
//
// 
//
// 示例 1： 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 
//
// 示例 2： 
//
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 
//
// 示例 3： 
//
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
// 
//
// 
//
// 
//
// 进阶： 
//
// 你能用 O(1)（即，常量）内存解决此问题吗？ 
//

import cn.yeshaoting.sd.leetcode.link.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Topic141 {

    // 需要额外空间；依赖一个set空间做重复判断；由于val可能重复，因此set需要是node的内存地址。
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        int idx = 0;
        Set<ListNode> all = new HashSet<ListNode>();
        ListNode current = head;
        while (current != null) {
            if (all.contains(current)) {
                return true;
            }

            all.add(current);
            current = current.next;
        }

        return false;
    }

    // 常量空间；快慢指针
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fastNode = head.next;
        ListNode slowNode = head;
        while (true) {
            if (fastNode == slowNode) {
                return true;
            }

            if (fastNode.next == null || fastNode.next.next == null) {
                return false;
            }

            if (slowNode.next == null) {
                return false;
            }

            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }

    }
}