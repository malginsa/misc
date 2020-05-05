package leetcode;

public class RemoveDuplicatesFromSortedArray {

        public int removeDuplicates(int[] nums) {
            if (nums.length < 2) return nums.length;
            int newLength = 0;
            for (int i=1; i<nums.length; i++)
                if (nums[i] != nums[newLength])
                    nums[++newLength] = nums[i];
            return newLength+1;
        }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicatesFromSortedArray().removeDuplicates(new int[]{1,2,3}));
    }
}
