package datastructures;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class ArrayProblems {

    public static int[] removeElements(int[] nums, int k) {
        // Check if the array is empty. If so, return 0.
        if (nums.length == 0) {
            return null;
        }

        // Initialize the write pointer at the first index.
        int writePointer = 0;

        // Loop through the array starting from the first element.
        for (int readPointer=0; readPointer<nums.length;readPointer++)
            // If the current element is not equal to k
            // Write it at the position of the write pointer.
            // Move the writePointer forward
            if (nums[readPointer] != k) {
                nums[writePointer] = nums[readPointer];
                writePointer++;
            }

        // Return a copy of the array until the writePointer.
        // This would be the array without the element 'k'
        return Arrays.copyOfRange(nums,0, writePointer);
    }

    public static int[] removeDuplicates(int[] nums) {
        // Check if the array is empty. If so, return 0.
        if (nums.length == 0) {
            return null;
        }

        // Initialize the write pointer at the first index.
        int writePointer = 1;

        // Loop through the array starting from the second element.
        for (int readPointer = 1; readPointer < nums.length; readPointer++) {
            // If the current element isn't a duplicate of the previous one
            if (nums[readPointer] != nums[readPointer - 1]) {
                // Write it at the position of the write pointer.
                nums[writePointer] = nums[readPointer];
                // Then, move the write pointer one step forward.
                writePointer++;
            }
        }

        // Return a copy of the array until the writePointer.
        return Arrays.copyOfRange(nums,0, writePointer);
    }

    public static void rotate(int[] nums, int k) {
        k = k%nums.length; //cases where k>arr.length
        int n = nums.length;
        // Swap numbers from 0 till n-k-1 iteratively
        rotate(nums, 0, n-k-1);
        // Swap numbers from n-k till end of the array
        rotate(nums, n-k, n-1);
        // Swap numbers from 0 till end of the array
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

    public static int findKthMaximumElement(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int j : arr) {
            minHeap.add(j);

            if(minHeap.size()>k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    public static int findKthMinimumElement(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int j : arr) {
            maxHeap.add(j);

            if(maxHeap.size()>k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    // Driver code
    public static void main(String[] args) {
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

        int[] arr = {10,3,4,9,8,6,11,2,1};
        System.out.println(findKthMaximumElement(arr, 3));
        System.out.println(findKthMinimumElement(arr, 4));

    }
}
