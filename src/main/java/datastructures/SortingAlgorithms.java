package datastructures;

import java.util.Arrays;

public class SortingAlgorithms {

    public static void bubbleSort(int[] arr) {

        //This algorithm brings the least number to the front of the array
        // with each iteration of the inner loop
        for(int i=0;i<arr.length;i++) {
            for(int j=i+1;j<arr.length;j++) {
                if(arr[i]>arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        // Outer loop: Iterate through all elements in the array
        for (int i = 0; i < array.length; i++) {

            // Initialize minIndex to store the index of the element at index i
            int minIndex = i;

            // Inner loop: Iterate through the remaining unsorted elements
            for (int j = i + 1; j < array.length; j++) {

                // Check if the current element is smaller than the element at minIndex
                if (array[j] < array[minIndex]) {

                    // Update minIndex with the index of the new smallest element found
                    minIndex = j;
                }
            }

            // Check if the smallest element found is not already in its correct position
            if (i != minIndex) {

                // Swap the smallest element found with the element at the current boundary
                // Store the element at the current boundary in a temporary variable
                int temp = array[i];

                // Move the smallest element found to its correct position in the sorted part
                array[i] = array[minIndex];

                // Move the element previously at the boundary to the position of the smallest element
                array[minIndex] = temp;
            }
        }
    }

    public static void insertionSort(int[] array) {
        // Outer loop: Iterate through all elements in the array, starting from the second element
        for (int i = 1; i < array.length; i++) {

            // Store the first element in the unsorted part of the array in a temporary variable
            int temp = array[i];

            // Initialize j to store the index of the last element in the sorted part of the array
            int j = i - 1;

            // Inner loop: Check whether the temporary value should be inserted in the sorted part
            while (j > -1 && temp < array[j]) {

                // Shift the element at index j one position to the right in the sorted part
                array[j+1] = array[j];

                // Assign the temporary value to the element at the index j
                array[j] = temp;

                // Decrement j to move towards the beginning of the sorted part of the array
                j--;
            }
        }
    }

    public static void main(String[] args) {

        int[] myArray = {4,2,6,5,1,3};
        bubbleSort(myArray);
        System.out.println(Arrays.toString(myArray));
        myArray = new int[]{5,7,9,10,1,3,4,2};
        selectionSort(myArray);
        System.out.println(Arrays.toString(myArray));
        myArray = new int[]{0,5,7,9,8,1,3,4,2,6,10};
        insertionSort(myArray);
        System.out.println(Arrays.toString(myArray));
    }
}
