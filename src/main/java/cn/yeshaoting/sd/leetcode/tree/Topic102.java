package cn.yeshaoting.sd.leetcode.tree;

//给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
//

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Topic102 {

    // 迭代法：借助队列，进行二叉树广度遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> all = new ArrayList<List<Integer>>();
        if (root == null) {
            return all;
        }

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.add(root);

        List<Integer> subset = new ArrayList<Integer>();
        int num = 1; // 当前层元素个数
        int next = 0; // 下一层元素个数
        while (!stack.isEmpty()) {
            TreeNode node = stack.remove();
            subset.add(node.val);

            if (node.left != null) {
                stack.add(node.left);
                next++;
            }

            if (node.right != null) {
                stack.add(node.right);
                next++;
            }

            // 当前层遍历完成，更新下一层num数并重置subset。
            if (--num == 0) {
                all.add(subset);
                subset = new ArrayList<Integer>();
                num = next;
                next = 0;
            }
        }

        return all;
    }

}