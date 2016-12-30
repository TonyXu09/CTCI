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
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /*
    * 10.1 Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough
    * buffer at the end to hold B. Write a method to merge B in to A in sorted order.
     */

    private static void mergeSortedArrays(int[] a, int[] b) {
        if (a.length == 0 || b.length == 0) {
            return;
        }

        int currA = a.length - 1;
        int currB = b.length - 1;

        int aEnd = a.length - 1;

        // Lets assume 0 is the empty int
        while (a[currA] == 0) {
            currA--;
        }

        while (currB >= 0) {
            // If a is bigger, then put it at the end
            if (currA >= 0 && a[currA] >= b[currB]) {
                a[aEnd] = a[currA];
                currA--;
            } else {
                a[aEnd] = b[currB];
                currB--;
            }

            aEnd--;
        }
    }

    static void testMergeSortedArrays() {
        int[] a = new int[]{3, 5, 7, 0, 0};
        int[] b = new int[]{2, 4};

        mergeSortedArrays(a, b);

        printArray(a);
        System.out.println("Test Merge Sorted Array Passed");

    }

    /*
    * 10.2: Group Anagrams: Write a method to sort an array of strings so that all the anagrams are
    * next to each other.
     */


    private static void swapStrings(String[] strings, int a, int b) {
        String temp = strings[a];
        strings[a] = strings[b];
        strings[b] = temp;
    }

    private static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int[] seenChars = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            seenChars[c]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            if (seenChars[c] == 0) {
                return false;
            }
            seenChars[c]--;
        }

        return true;
    }

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

    private static int searchRotated(int[] a, int value, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            // If it is properly sorted on the right side, must be on the right side
            if (a[right] > a[mid]) {
                searchRotated(a, value, mid + 1, right);
            } else {
                searchRotated(a, value, mid + 1, right);
            }
        } else {
            // It is properly sorted on the left side, so must be there
            if (a[left] < a[mid]) {
                searchRotated(a, value, left, mid - 1);
            } else {
                searchRotated(a, value, mid + 1, right);
            }
        }

        return -1;
    }

    static void testSearchRotated() {
        int[] a = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        assert (searchRotated(a, 5, 0, 11) == 8);

        assert (searchRotated(a, 16, 0, 11) == 1);

        assert (searchRotated(a, 17, 0, 11) == -1);

        System.out.println();
        System.out.println("Test Search Rotated Passed");
        System.out.println();
    }

    /*
    * 10.4 Sorted Search, No Size: You are given an array-like data structure Listy which lacks a
    * size method. It does, however, have an elementAt(i) method that returns the element at index i
    * in O(1) time. If i is beyond the bounds of the data structure, it returns -1. (For this reason,
     * the data structure only supports positive integers) Given a Listy which contains sorted,
     * positive integers, find the index at which an element x occurs. If x occurs multiple times,
     * you may return any index
     */

    // solution, exponentially increase the list by power of 2, until we hit -1, that will give us
    // approx list size; Then do binary search. This will take O(log n). However, if we hit -1,
    // then we have to search on left side.

    private static int listySearch(int[] a, int value) {
        int right = getSize(a);
        int left = 0;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (middle > a.length) { // this line should be, if (a.elementAt(middle) == -1)
                // must be on the left side;
                right = middle - 1;
            } else if (a[middle] == value) {
                return middle;
            } else if (a[middle] < value) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }

    private static int getSize(int[] a) {
        int size = 0;
        while (size < a.length) { //This line should be, while(a.elementAt(size) != -1)
            size = size * 2;
        }

        return size;
    }

    static void testSearchListy() {
        int[] a = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        assert (listySearch(a, 5) == 8);
        assert (listySearch(a, 16) == 1);
        assert (listySearch(a, 17) == -1);
        assert (listySearch(a, 6) == -1);

        System.out.println();
        System.out.println("Test Search Listy Passed");
        System.out.println();
    }
    /*
    * 10.5 Sparse Search: Given a sorted array of strings that is interspersed with empty strings,
    * write a method to find the location of a given string.
    *
    * Input: ball, {"at, "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}
    * Output: 4
    *
     */

    private static int sparseSearch(String[] strings, String s) {
        if (strings.length == 0) {
            return -1;
        }

        int left = 0;
        int right = strings.length;

        while (left <= right) {
            int mid = (left + right) / 2;
            int oldMid = mid;

            // Can optimize by checking both directions at the same time.
            // go right until we either hit the end or a valid string
            while(strings[mid].equals("") && mid < strings.length){
                mid++;
            }

            // If the right is nothing but empty strings, lets go left
            if(mid == strings.length){
                mid = oldMid;
                while(strings[mid].equals("") && mid >= 0){
                    mid--;
                }
            }

            // If nothing but empty strings, so couldn't find a non empty string on the left as well
            // Then just return -1
            if(mid < 0){
                return -1;
            }

            if (!strings[mid].equals("")) {
                int compareValue = strings[mid].compareTo(s);
                if (compareValue == 0) {
                    return mid;
                } else if (compareValue < 0) { // so current mid less than s, search right
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    static void testSparseSearch(){
        String[] strings = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        assert (sparseSearch(strings, "ball") == 4);
        assert (sparseSearch(strings, "balls") == -1);
        assert (sparseSearch(strings, "car") == 7);
        assert (sparseSearch(strings, "at") == 0);

        System.out.println("Test Sparse Search Complete");
    }

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

