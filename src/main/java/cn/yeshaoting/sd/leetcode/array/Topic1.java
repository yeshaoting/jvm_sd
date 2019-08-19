package cn.yeshaoting.sd.leetcode.array;

import java.util.Arrays;

//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
//
// 示例:
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//
//
class Topic1 extends BaseArray {

    public static void main(String[] args) throws Exception {
        String[] arr = new String[]{
                "[-1, 0, 2, -4]",
        };

        for (String item : arr) {
            int[] nums = stringToIntegerArray(item);
            System.out.printf("item=%s\toutput=%s\n", item, Arrays.toString(new Topic1().twoSum(nums, 2)));
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = 0;
        a:
        for (i = 0; i < nums.length - 1; i++) {
            int current = nums[i];
            for (j = i + 1; j < nums.length; j++) {
                int post = nums[j];
                if (current + post == target) {
                    break a;
                }
            }
        }

        return new int[]{i, j};
    }

}