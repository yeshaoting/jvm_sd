package cn.yeshaoting.sd.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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