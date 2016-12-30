package com.ctci;

/**
 * Created by tonyxu on 12/21/16.
 */

public class Chapter10 {

    static void testBinarySearch() {
        int[] a = new int[]{1, 3, 5, 7, 9};
        assert (2 == Sorting.binarySearchIterative(a, 5));
        assert (2 == Sorting.binarySearchRecursive(a, 5, 0, 4));

        assert (-1 == Sorting.binarySearchIterative(a, 4));
        assert (-1 == Sorting.binarySearchRecursive(a, 4, 0, 4));

        System.out.println("Binary Search passed");
        System.out.println();
    }

    static void testQuickSort() {
        int[] a = new int[]{3, 7, 4, 2, 8, 1, 5};
        Sorting.quickSort(a, 0, 6);
        printArray(a);

        System.out.println("QuickSort passed");
        System.out.println();
    }

    static void testMergeSort() {
        int[] a = new int[]{3, 7, 4, 2, 8, 1, 5};
        Sorting.mergeSort(a);
        printArray(a);

        System.out.println("MergeSort passed");
        System.out.println();
    }

    private static void printArray(int[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /*
    * 10.1 Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough
    * buffer at the end to hold B. Write a method to merge B in to A in sorted order.
     */


    /*
    * 10.2: Group Anagrams: Write a method to sort an array of strings so that all the anagrams are
    * next to each other.
     */

    /*
    * 10.3: Search in Rotated Array: Given a sorted array of n integers that has been rotated an
    * unknown number of times, write code to find an element in the array. You may assume that the
    * array was originally sorted in increasing order.
    *
    * EXAMPLE:
    * Input: Find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
    * Output: 8 (The index of 5 in the array)
    *
     */

    /*
    * 10.4 Sorted Search, No Size: You are given an array-like data structure Listy which lacks a
    * size method. It does, however, have an elementAt(i) method that returns the element at index i
    * in O91) time. If i is beyond the bounds of the data structure, it returns -1. (For this reason,
     * the data structure only supports positive integers) Given a Listy which contains sorted,
     * positive integers, find the index at which an element x occurs. If x occurs multiple times,
     * you may return any index
     */

    /*
    * 10.5 Sparse Search: Given a sorted array of strings that is interspersed with empty strings,
    * write a method to find the location of a given string.
    *
    * Input: ball, {"at, "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}
    * Output: 4
    *
     */

    /*
    * 10.6 Sort Big File: Imagine you have a 20GB file with one string per line. Explain how
    * you would sort the file.
    *
     */

    /*
    * 10.7 Missing Int: Given an input with four billion non-negative integers, provide an algorithm
    * to generate an integer that is not contained in the file. Assume you have 1 GB of memory available
    * for this task.
    *
    * FOLLOW UP:
    * What if you have only 10 MB of memory? Assume that all the values are distinct and we now have
    * no more than one billion non-negative integers.
    *
     */

    /*
    * 10.8 Find Duplicates: You have an array with all the numbers from 1 to N, where N is at most
    * 32,000. The array may have duplicate entries and you do not know what N is. With only 4 kilobytes of
    * memory available, how would you print all duplicate elemnts in the array?
    *
     */

    /*
    * 10.9 Sorted Matrix Search: Given an M x N Matrix in which each row and each column is sorted in
    * ascending order, write a method to find an element.
    *
     */

    /*
    * 10.10 Rank from Stream: Imagine you are reading in a stream of integers. Periodically, you wish
    * to be able to look up the rank of a number x (The number of values less than or equal to x).
    * Implement the ata structures and algorithms to support these operations. That is, implement the
    * method track(int x), which is called when each number is generated, and the method getRankOfNumber(int x),
    * which returns the number of values less than or equal to x (not including x itself).
    *
    * EXAMPLE
    * Stream (In order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
    * GetRankOfNumber(1) = 0
    * GetRankOfNumber(3) = 1
    * GetRankOfNumber(4) = 3
    *
     */

    /*
    * 10.11 Peaks and Valleys: In an array of integers, a "peak" is an element which is greater than
    * or equal to the adjacent integers and a "valley" is an element which is less than or equal to
    * the adjacent integers. For example, in the array {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and
    * {5, 2} are valleys. Given an array of integers, sort the array into an alternating sequence of peaks
    * and valleys.
    *
    * EXAMPLE:
    * Input: {5, 3, 1, 2, 3}
    * Output: {5, 1, 3, 2, 3}
    *
    *
     */
}

