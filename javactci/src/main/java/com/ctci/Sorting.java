package com.ctci;

/**
 * Created by tonyxu on 12/22/16.
 */

public class Sorting {
    // Common Sorting Algorithms

    /*
    * Bubble Sort | O(n^2), average and worst case. Memory: O(1)
    * - Start at the beginning of the array and swap the first two elements if the
    * first is greater than the second. Then we go to the next pair, and so on, making
    * sweeps of the array until it is sorted.
     */

    /*
    * Selection Sort | Runtime O(n^s) average and worst case: Memory: O(1)
    *
    * Find the smallest element using a linear scan and move it to the front. Then find the
    * second smallest and move it, etc. Do it until all elements are in place
    *
     */

    /*
    * Merge Sort | Runtime: O(n log(n)) average and worst case: Memory: O(n)
    *
    * Merge sort divides the array in half, sorts each of those halves, and then merges
    * them back together. Each of those halves has the same sorting algorithm applied to it.
    * Eventually you are just merging just two single element arrays. It is the merge part
    * that does the heavy lifting.
    *
    * The merge method operates by copying all the elements from the target array segment into a
    * helper array, keeping track of where the start of the left and right halves should be.
    * (helperLeft and helperRight). We then iterate through helper, copying the smaller element from
    * each half into the array. At the end, copy any remaining elements in to the target array.
    *
    *
     */

    public static void mergeSort(int[] numbers) {
        int[] helper = new int[numbers.length];
        mergeSort(numbers, helper, 0, numbers.length - 1);
    }

    private static void mergeSort(int[] numbers, int[] helper, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(numbers, helper, left, middle);
            mergeSort(numbers, helper, middle + 1, right);
            merge(numbers, helper, left, middle, right);
        }
    }

    private static void merge(int[] numbers, int[] helper, int left, int middle, int right) {
        for (int i = left; i <= right; i++) {
            helper[i] = numbers[i];
        }

        int helperLeft = left;
        int helperRight = middle + 1;
        int current = left;

        while (helperLeft <= middle && helperRight <= right) {
            // If left side is smaller, copy the left side
            if (helper[helperLeft] <= helper[helperRight]) {
                numbers[current] = helper[helperLeft];
                helperLeft++;
            } else {
                numbers[current] = helper[helperRight];
                helperRight++;
            }

            current++;
        }

        while(helperLeft <= middle) {
            numbers[current] = helper[helperLeft];
            helperLeft++;
        }

    }


    /*
    * Quick Sort | Runtime O(n log(n)) average, O(n^2) worst case. Memory: O(log(n))
    *
    * In quick sort, we pick a random element and partition the array, such that all small numbers
    * that are less than the partitioning element come before all elements that are greater than it.
    * The partitioning can be performed efficient through a series of swaps.
    *
    * If we repeatedly partition the array(and it's sub-arrays) around an element, the array will eventually
    * become sorted. However, as the partitioned element is not guaranteed to the be median, our sorting
     * could be very slow. O(n^2) worst case.
    *
     */

    public static void quickSort(int[] numbers, int left, int right) {
        int index = partition(numbers, left, right);
        if (left < index - 1) {
            quickSort(numbers, left, index - 1);
        }
        if (index < right) {
            quickSort(numbers, index, right);
        }
    }

    private static int partition(int[] numbers, int left, int right) {
        int pivot = numbers[(left + right) / 2];

        while (left <= right) {
            while (numbers[left] < pivot) {
                left++;
            }

            while (numbers[right] > pivot) {
                right--;
            }

            if (left <= right) {
                swap(numbers, left, right);
                left++;
                right--;
            }
        }

        return left;
    }

    private static void swap(int[] numbers, int left, int right) {
        int temp = numbers[left];
        numbers[left] = numbers[right];
        numbers[right] = temp;
    }

    /*
    * Radix Sort | Runtime O(kn)
    *
    * Radix sort is a sorting algorithm for integers, (and some other data types) that take advantage
    * of the fact that integers have a finite number of bits. In radix sort, we iterate through each
    * digit of the number, grouping numbers by each digit. for Example, if we have an array of integers,
    * we might first sort by the first digit, so that the 0s are grouped together. Then we sort
    * each of these groupings by the next digit. We repeat this process sorting by each subsequent digit,
    * until finally the whole array is sorted.
    *
    * Unlike comparison sorting algorithms, which cannot perform beter than O(n Log(n)) in the average case,
    * radix sort has a runtime of O(kn) where n is the number of elements and k is the number of
    * passes of the sorting algorithm.
    *
    *
     */

    /*
    * Searching Algorithms:
    *
    * We look for an element x in a sorted array by first comparing x to the midpoint of the array.
    * If X is less than the midpoint, we search the left half, etc etc.
    *
    * We do this until we find x or the subarray has size 0.
    *
     */

    public static int binarySearchRecursive(int[] a, int value, int min, int max) {
        if (min > max) {
            return -1;
        }

        int mid = (min + max) / 2;
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return binarySearchRecursive(a, value, mid + 1, max);
        } else {
            return binarySearchRecursive(a, value, min, max - 1);
        }
    }

    public static int binarySearchIterative(int[] a, int value) {
        int min = 0;
        int max = a.length;

        if (max == 0) {
            return -1;
        }

        while (min <= max) {
            int mid = (min + max) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return -1;
    }

}
