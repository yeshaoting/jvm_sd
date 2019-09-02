package cn.yeshaoting.sd.leetcode.array;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//给定一个没有重复数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
//

/*
    description:
    author: yeshaoting
    time: 2019-09-02 17:26
*/
public class Topic46 extends BaseArray {

    public static void main(String[] args) throws Exception {
        int[] nums = stringToIntegerArray("[1,2,3,4]");
        List<List<Integer>> ret = new Solution46_A().permute(nums);
        String out = int2dListToString(ret);
        System.out.printf("size=%s\nout=%s", ret.size(), out);
    }

}

class Solution46_A {
    List<List<Integer>> all = new ArrayList<List<Integer>>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return all;
        }

        backtrack(nums, new ArrayList<Integer>());
        return all;
    }

    private void backtrack(int[] nums, ArrayList<Integer> used) {
        for (int num : nums) {
            if (used.size() == nums.length) {
                all.add((ArrayList<Integer>) used.clone());
                break;
            }

            if (used.contains(num)) {
                continue;
            }

            used.add(num);
            backtrack(nums, used);
            used.remove(used.size() - 1);
        }

    }
}