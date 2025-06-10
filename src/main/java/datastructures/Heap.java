package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
         100
       /     \
      99     75
     /  \   /  \
    58  72 61  18

    The above Heap is represented in an ArrayList as
    [100,99,75,58,72,61,18]
 */

public class Heap {

    private static List<Integer> heap;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    private int leftChild(int index) {
        return 2*index+1; //As ArrayList is zero based
    }

    private int rightChild(int index) {
        return 2*index+2;
    }

    private int parent(int index) {
        return (index-1)/2;
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(int value) {
        heap.add(value);
        int newValueIndex = heap.size()-1;

        //Keep comparing the value inserted with its parent
        //Swap if new value is greater than its parent until
        // value is more than its parent and the index is >0
        while(value>heap.get(parent(newValueIndex)) && newValueIndex>0) {
            int replacedIndex = parent(newValueIndex);
            swap(newValueIndex, replacedIndex);
            newValueIndex = replacedIndex;
        }
    }

    public void printHeap() {
        System.out.println(heap);
    }

    //By default, the top of the heap is removed
    public Integer remove() {
        if(heap.isEmpty()) {
            return null;
        }
        if(heap.size()==1) {
            return heap.removeFirst();
        }

        //Remove the first value
        //Replace it with the last value
        //By default in a max heap, first element is the max element
        //Adjust the new node to its appropriate place
        int maxValue = heap.getFirst();
        heap.set(0, heap.removeLast());
        sinkDown(0);

        return maxValue;
    }

    private void sinkDown(int index) {
        // Start at the provided index (this is typically the root)
        int maxIndex = index;

        // Continue sinking down until the element at maxIndex is correctly positioned
        while (true) {
            // Get indices of left and right children
            int leftIndex = leftChild(index);
            int rightIndex = rightChild(index);

            // If left child exists and is greater than the current max,
            // then update maxIndex to left child's index
            if (leftIndex < heap.size() && heap.get(leftIndex) > heap.get(maxIndex)) {
                maxIndex = leftIndex;
            }

            // If right child exists and is greater than the current max,
            // then update maxIndex to right child's index
            if (rightIndex < heap.size() && heap.get(rightIndex) > heap.get(maxIndex)) {
                maxIndex = rightIndex;
            }

            // If maxIndex has changed (i.e., one of the children was greater),
            // then swap the current element with the larger child and continue sinking down
            if (maxIndex != index) {
                swap(index, maxIndex);
                index = maxIndex;
            } else {
                // If the element has not been swapped, this means it's in the correct position,
                // so we break the loop and end the method
                return;
            }
        }
    }

    public static int findKthSmallest(int[] nums, int k) {
        // Initialize a new Heap instance.
        Heap maxHeap = new Heap();

        // Iterate over every number in the input array.
        for (int num : nums) {
            // Insert current number into the heap.
            // Heap property is maintained after each insertion.
            maxHeap.insert(num);

            // Check if heap size exceeds 'k'.
            if (maxHeap.getHeap().size() > k) {
                // If size exceeds 'k', remove the largest number.
                // Heap property is maintained after each removal.
                maxHeap.remove();
            }
        }

        // At this point, the heap contains the smallest 'k' numbers.
        // The largest number in the heap is the kth smallest number in the array.
        // Therefore, we remove and return it.
        return maxHeap.remove();
    }

    public static Integer findKthMaximumUsingHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int j : arr) {
            minHeap.add(j);

            //The minimum element gets stored at the top
            //At position K, it would be the kth max element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.remove();
    }

    public static Integer findKthMinimumUsingHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int j : arr) {
            minHeap.add(j);

            //The minimum element gets stored at the top
            //At position arr.length - k + 1, it would be the kth min element
            if (minHeap.size() > arr.length - k + 1) {
                minHeap.poll();
            }
        }
        return minHeap.remove();
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(100);
        heap.insert(99);
        heap.insert(61);
        heap.insert(58);
        heap.insert(72);
        heap.insert(75);
        heap.printHeap();
        System.out.println(heap.remove());
        heap.printHeap();

        int[] nums1 = {7, 10, 4, 3, 20, 15};
        int k1 = 3;
        System.out.println("Test case 1:");
        System.out.println("Expected output: 7");
        System.out.println("Actual output: " + findKthSmallest(nums1, k1));
        System.out.println();

        // Test case 2
        int[] nums2 = {2, 1, 3, 5, 6, 4};
        int k2 = 2;
        System.out.println("Test case 2:");
        System.out.println("Expected output: 2");
        System.out.println("Actual output: " + findKthSmallest(nums2, k2));
        System.out.println();

        int[] nums = {7, 10, 4, 3, 20, 15};
        System.out.println(findKthMaximumUsingHeap(nums, 3));
        System.out.println(findKthMinimumUsingHeap(nums, 3));
    }
}
