package cn.yeshaoting.sd.leetcode.value;

class Topic9 {

    public static void main(String[] args) throws Exception {
        int[] arr = {121, -121};
        for (int x : arr) {
            System.out.println(new Topic9().isPalindrome(x));
        }
    }

    public boolean isPalindrome(int x) {
        String origin = String.valueOf(x);
        String newest = new StringBuilder(origin).reverse().toString();
        return origin.equals(newest);
    }
}