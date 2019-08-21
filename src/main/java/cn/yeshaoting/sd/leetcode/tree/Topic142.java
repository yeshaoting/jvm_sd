package cn.yeshaoting.sd.leetcode.tree;//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。 
//
// 说明：不允许修改给定的链表。 
//
// 
//
// 示例 1： 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：tail connects to node index 1
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 
//
// 示例 2： 
//
// 输入：head = [1,2], pos = 0
//输出：tail connects to node index 0
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 
//
// 示例 3： 
//
// 输入：head = [1], pos = -1
//输出：no cycle
//解释：链表中没有环。
// 
//
// 
//
// 
//
// 进阶： 
//你是否可以不用额外空间解决此题？ 
//

import cn.yeshaoting.sd.leetcode.link.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Topic142 {

    // 需要额外空间；依赖一个set空间做重复判断；由于val可能重复，因此set需要是node的内存地址。
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        int idx = 0;
        Set<ListNode> all = new HashSet<ListNode>();
        ListNode current = head;
        while (current != null) {
            if (all.contains(current)) {
                return current;
            }

            all.add(current);
            current = current.next;
        }

        return null;
    }

    // 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
//    原理：首先初始化快指针 fast = head.next.next 和 slow = head.next，
//    此时快指针走的路长为2, m慢指针走的路长为1，之后令快指针每次走两步，
//    慢指针每次走一步，这样快指针走的路长始终是慢指针走的路长的两倍，
//    若不存在环，直接返回None，
//    若存在环，则 fast 与 slow 肯定会在若干步之后相遇；
//
//    性质1：
//    设从head需要走 a 步到达环的入口，如果环存在的话，
//    再走 b 步可以再次到达该入口（即环的长度为b），
//    如果存在环的话，上述描述的 快指针 fast 和
//    慢指针slow 必然会相遇，且此时slow走的路长
//    小于 a + b(可以自行证明)，设其为 a + x，
//    当快慢指针相遇时，快指针已经至少走完一圈环了，
//    不妨设相遇时走了完整的m圈(m >= 1)，有：
//
//    快指针走的路长为 a + mb + x
//    慢指针走的路长为 a + x
//
//    由于快指针fast 走的路长始终是慢指针的 2倍，所以：
//
//    a + mb + x = 2(a + x)
//
//    化简可得：
//
//    a = mb - x  -------------  (*)
//
//    当快指针与慢指针相遇时，由于 <性质1> 的存在，
//    可以在链表的开头初始化一个新的指针，
//    称其为 detection, 此时 detection 距离环的入口的距离为 a，
//
//    此时令 detection 和 fast 每次走一步，
//    会发现当各自走了 a 步之后，两个指针同时到达了环的入口，理由分别如下：
//
//    detection不用说了，走了a步肯定到刚好到入口
//    fast已经走过的距离为 a + mb + x，当再走 a 步之后，
//    fast走过的总距离为 2a + mb + x，带入性质1的(*)式可得：
//            2a + mb + x = a + 2mb，会发现，fast此时刚好走完了
//    整整 2m 圈环，正好处于入口的位置。
//
//    基于此，我们可以进行循环，直到 detection 和
//    fast 指向同一个对象，此时指向的对象恰好为环的入口。
    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

}

