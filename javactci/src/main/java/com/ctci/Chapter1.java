package com.ctci;

/**
 * Created by tonyxu on 12/1/16.
 * Solutions and tests for chapter 1 of the book
 */

class Chapter1 {

    /*
    Is Unique: Implement an algorithm to determine if a string has all unique characters.
    What if you cannot use additional data structures?
     */

    // Assume ascii
    // If we can't use additional data structures, just loop through, will take N^2
    private static boolean isUnique(String s) {
        boolean[] seenChars = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seenChars[c]) {
                return false;
            } else {
                seenChars[c] = true;
            }
        }
        return true;
    }

    static void testIsUnique() {
        String s = "abcde";
        assert (isUnique(s));

        s = "abb";
        assert (!isUnique(s));

        s = "a";
        assert (isUnique(s));

        s = "";
        assert (isUnique(s));

        s = "aa";
        assert (!isUnique(s));

        System.out.println("Chapter 1.1 IsUnique Passed");
    }

    /*
    Check Permutation: Given two strings,write a method to decide if one is a permutation of the
    other.
     */

    // solutions: Can sort strings, and then compare, will take O(n log(n))
    // Can iterate through first string, build a map (array) of how many chars there are, then go
    // through the second string and make sure we have the same char counts O(n)
    private static boolean isPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int[] charCounts = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            charCounts[c]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            if (charCounts[c] == 0) {
                return false;
            }
            charCounts[c]--;
        }

        return true;
    }

    static void testIsPermutation() {
        String s1 = "abc";
        String s2 = "cba";

        assert (isPermutation(s1, s2));

        s2 = "ccba";
        assert (!isPermutation(s1, s2));

        s1 = "abcd";
        s2 = "dcbb";

        assert (!isPermutation(s1, s2));

        System.out.println("TestIsPermutation success");
    }


    /*
    URLi : Write a method to replace all spaces in a string with '%20  You may assume that the
    string has sufficient space at the end to hold the additional characters,and that you are given
    the "true" length of the string. (Note: If implementing in Java,please use a character array
    so that you can perform this operation in place.)
    EXAMPLE
    Input: "Mr John Smith ", 13 Output: "Mr%20John%20Smith"
     */
    private static void replaceSpaces(char[] input, int length) {
        int currPos = 0;

        for (int i = 0; i < length; i++) {
            char c = input[i];
            if (c == ' ') {
                input[currPos++] = '%';
                input[currPos++] = '2';
                input[currPos++] = '0';

            } else {
                input[currPos++] = c;
            }
        }
    }

    static void testReplaceSpaces() {
        String s = "Mr John Smith    ";
        char[] c = s.toCharArray();

        replaceSpaces(c, 13);

        String result = c.toString();
        assert (result == "Mr%20John%20Smith");

        System.out.println("TestReplaceSpaces Success");
    }

    /*
    Palindrome Permutation: Given a string, write a function to check if it is a permutation
    of a palin­ drome. A palindrome is a word or phrase that is the same  forwards and backwards.
    A permutation is a rearrangement of letters. The palindrome does not need to be limited
    to just dictionary words.

    EXAMPLE
    Input: Tact Coa
    Output: True (permutations: "taco cat", "atco eta", etc.)
     */

    // lets assume case doesn't matter, so calling toLower on everything
    // lets assume we don't care about spaces
    // lets assume its just letters, so 26 letters
    // can only be a palindrome is every letter is a multiple of 2 except for 1
    // assume string can only contain a - z, and space
    private static boolean isPalindromePermutation(String s) {
        s = s.toLowerCase();
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //ignore spaces
            if (c != ' ') {
                charCount[c - 'a']++;
            }
        }

        boolean hasSeenOdd = false;
        for (int i = 0; i < 26; i++) {
            // if odd
            if ((charCount[i] % 2) == 1) {
                if (hasSeenOdd) {
                    return false;
                } else {
                    hasSeenOdd = true;
                }
            }
        }

        return true;
    }

    static void testIsPalindromePermutation() {
        String s = "Tact Coa";
        assert (isPalindromePermutation(s));

        s = "Tact Coc";
        assert (!isPalindromePermutation(s));

        System.out.println("testIsPalindromePermutation success");

    }

    /*
    One Away: There are three types of edits that can be performed on strings: insert a character,
    remove a character, or replace a character. Given two strings, write a function to check if
    they are one edit (or zero edits) away.
    EXAMPLE
    pale, ple -> true pales, pale -> true pale, bale -> true pale, bake -> false
    pg 209
     */

    private static boolean isOneAway(String s1, String s2) {
        int lengthDifference = s1.length() - s2.length();
        if (lengthDifference < -1 || lengthDifference > 1) {
            return false;
        }

        switch (lengthDifference) {
            case -1: // s1 is shorter than s1 by 1, must be an insert
                return isOneAwayInsert(s1, s2);
            case 0: // same length, must be replace
                return isOneAwayReplace(s1, s2);
            case 1: // s1 is 1 longer, must be a remove.
                // Remove is the same as an insert in the other direction
                return isOneAwayInsert(s2, s1);
        }

        // should never get here
        return false;
    }

    private static boolean isOneAwayInsert(String s1, String s2) {
        if (s1.length() - s2.length() != -1) {
            return false;
        }

        int currS2Index = 0;
        boolean hasSeenDifference = false;
        for (int i = 0; i < s1.length(); i++, currS2Index++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(currS2Index);

            // If not equal
            if (c1 != c2) {
                // If there already has been a difference, can only be 1
                if (hasSeenDifference) {
                    return false;
                } else {
                    hasSeenDifference = true;
                    currS2Index++;
                }
            }
        }

        return true;
    }

    private static boolean isOneAwayReplace(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        boolean hasSeenDifference = false;
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            // If not equal
            if (c1 != c2) {
                // If there already has been a difference, can only be 1
                if (hasSeenDifference) {
                    return false;
                } else {
                    hasSeenDifference = true;
                }
            }
        }
        return true;
    }

    static void testIsOneAway() {
//        pale, ple -> true pales, pale -> true pale, bale -> true pale, bake -> false
        String s1 = "pale";
        String s2 = "ple";

        assert (isOneAway(s1, s2));
        assert (isOneAway(s2, s1));

        s1 = "pales";
        s2 = "pale";

        assert (isOneAway(s1, s2));

        s1 = "pale";
        s2 = "bale";

        assert (isOneAway(s1, s2));

        s2 = "bake";
        assert (!isOneAway(s1, s2));

        System.out.println("testIsOneAway passed");
    }


    /*
    String Compression: Implement a method to perform basic string compression using the counts of
    repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the "compressed"
    string would not become smaller than the original string, your method should return
    the original string. You can assume the string has only uppercase and lowercase letters (a - z).
     */
    public static String compressString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        char lastChar = s.charAt(0);
        sb.append(lastChar);
        int currCount = 1;

        //aabcccccaaa

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == lastChar) {
                currCount++;
            } else {
                // If we have a compression
                sb.append(currCount);
                sb.append(c);
                lastChar = c;
                currCount = 1;
            }
        }

        String s2 = sb.toString();

        if (s2.length() < s.length()) {
            return s2;
        } else {
            return s;
        }
    }

    static void testStringCompression() {
        String s = "aabcccccaaa";
        assert (compressString(s).equals("a2blc5a3"));

        System.out.println("testStringCompression Passed ");
    }


    /*
    Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
    bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
     */

    /*
    [1][2][3]             [1][2][3][4]
    [4][5][6]             [5][6][7][8]
    [7][8][9]             [9][10][11][12]
                          [13][14][15][16]
    [7][4][1]
    [8][5][2]             [13][9][5][1]
    [9][6][3]             [14][10][6][2]
                          [15][11][7][3]
    n = 3                 [16][12][8][4]

     */

    // rotate layer by layer, 1 element at a time
    private static void rotateImage(int[][] matrix, int n) {
        for (int layer = 0; layer < n / 2; layer++) {
            for (int i = 0; i < (n - 1 - (layer * 2)); i++) {
                int offset = i + layer;

                //swap corners an then move in
                int temp = matrix[layer][offset]; //[1][1] -> temp

                matrix[layer][offset] = matrix[n - 1 - offset][layer]; //[3][1] -> [1][1]
                matrix[n - 1 - offset][layer] = matrix[n - 1 - layer][n - 1 - offset]; // [2][2] -> [2][1]
                matrix[n - 1 - layer][n - 1 - offset] = matrix[offset][n - 1 - layer]; // [1][2] -> [2][2]
                matrix[offset][n - 1 - layer] = temp; // temp -> [1][2]
            }
        }
    }

    static void testRotateImage() {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };

        rotateImage(matrix, 4);

        printMatrix(matrix, 4);

        System.out.println();

        int[][] matrix2 = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };

        rotateImage(matrix2, 5);
        printMatrix(matrix2, 5);

    }

    private static void printMatrix(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    /*
    Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row
    and column are set to 0.
     */

    /*
    String Rotation:Assumeyou have a method isSubstringwhich checks if one word is a substring
    of another. Given two strings, sl and s2, write code to check if s2 is a rotation of sl
    using only one call to isSubstring (e.g.,"waterbottle" is a rotation of"erbottlewat").
     */
}
