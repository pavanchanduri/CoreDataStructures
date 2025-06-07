package datastructures;

import java.util.Arrays;


public class ArrayProblems {

    public static int[] removeElements(int[] a, int k)
    {
        // Move all other elements to beginning
        int ind = 0;

        for (int i=0; i<a.length; i++)
            if (a[i] != k)
                a[ind++] = a[i];

        // Create a copy of arr[]
        return Arrays.copyOf(a, ind);
    }

    public static int[] removeDuplicates(int[] nums) {
        if(nums.length==0) return null;
        int idx = 1;
        for(int i=1;i<nums.length;i++) {
            if(nums[i]!=nums[idx-1]) {
                nums[idx++] = nums[i];
            }
        }
        return Arrays.copyOf(nums,idx);
    }

    public static void rotate(int[] nums, int k) {
        k = k%nums.length; //cases where k>arr.length
        int n = nums.length;
        rotate(nums, 0, n-k-1);
        rotate(nums, n-k, n-1);
        rotate(nums, 0, n-1);
    }

    private static void rotate(int[] nums, int start, int end) {
        while(start<end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        int[] a = { 3, 9, 2, 3, 1, 7, 2, 3, 5 };
        int k = 3;
        a = removeElements(a, k);
        System.out.println(Arrays.toString(a));

        int[] dupArray = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        a = removeDuplicates(dupArray);
        System.out.println(Arrays.toString(a));

        int[] array = {1,2,3,4,5,6,7,8,9};
        rotate(array, k);
        System.out.println(Arrays.toString(array));

    }
}
