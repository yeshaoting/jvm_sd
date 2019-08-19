package cn.yeshaoting.sd.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
// 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
//
//
class Topic15 extends BaseArray {

    public static void main(String[] args) throws Exception {
        String[] arr = new String[]{
//                "[-1, 0, 1, 2, -1, -4]",
//                "[0, 0, 0, 0]",
//                "[-2, 0, 0, 2, 2]"
                "[0,0,0]"
        };

        for (String item : arr) {
            int[] nums = stringToIntegerArray(item);
            System.out.printf("item=%s\toutput=%s\n", item, new Topic15().threeSum(nums));
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> all = new ArrayList<>();
        for (int idx = 0; idx < nums.length; idx++) {
            // skip same val
            if (idx > 0 && nums[idx] == nums[idx - 1]) {
                continue;
            }

            addThreeSum(all, idx, nums);
        }

        return all;
    }

    private void addThreeSum(List<List<Integer>> all, int idx, int[] nums) {
        int head = idx + 1;
        int tail = nums.length - 1;
        while (head < tail) {
            int sum = nums[idx] + nums[head] + nums[tail];
            if (sum > 0) {
                tail--;
                continue;
            }

            if (sum < 0) {
                head++;
                continue;
            }

            // occur the triple
            all.add(Arrays.asList(nums[idx], nums[head], nums[tail]));
            // skip same tail val
            while (head < tail && nums[tail] == nums[--tail]) {
            }

            // skip same head val
            while (head < tail && nums[head] == nums[++head]) {
            }
        }

    }

}