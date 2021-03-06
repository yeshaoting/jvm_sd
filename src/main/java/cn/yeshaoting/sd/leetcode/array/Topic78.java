package cn.yeshaoting.sd.leetcode.array;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
// 说明：解集不能包含重复的子集。
//
// 示例:
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//]
//
public class Topic78 extends BaseArray {

    public static void main(String[] args) throws IOException {
        int[] nums = stringToIntegerArray("[1,2,3]");
        List<List<Integer>> ret = new Solution78_B().subsets(nums);
        String out = int2dListToString(ret);
        System.out.printf("size=%s\nout=%s", ret.size(), ret);
    }

}

class Solution78_A {

    // 迭代法
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> all = new ArrayList<List<Integer>>();
        all.add(new ArrayList<Integer>());
        for (int i = nums.length - 1; i >= 0; i--) {
            List<List<Integer>> subsets = new ArrayList<List<Integer>>();
            for (List<Integer> item : all) {
                List<Integer> subset = new ArrayList<Integer>();
                subset.add(nums[i]);
                subset.addAll(item);
                subsets.add(subset);
            }

            all.addAll(subsets);
        }

        return all;
    }
}

class Solution78_B {

    List<List<Integer>> all = new ArrayList<List<Integer>>();

    // 递归法
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, new ArrayList<>(), new ArrayList<>());
        return all;
    }

    private void backtrack(int[] nums, ArrayList<Integer> result, ArrayList<Boolean> used) {
        if (nums.length == used.size()) {
            all.add((List<Integer>) result.clone());
            return;
        }

        for (int i = 0; i <= 1; i++) {
            boolean choose = i == 0;
            used.add(choose);
            if (choose) {
                result.add(nums[used.size() - 1]);
            }

            backtrack(nums, result, used);
            used.remove(used.size() - 1);
            if (choose) {
                result.remove(result.size() - 1);
            }
        }
    }
}


