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
            if(charCounts[c] == 0){
                return false;
            }
            charCounts[c]--;
        }

        return true;
    }

    static void testIsPermutation(){
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
    string has suf cient space at the end to hold the additional characters,and that you are given
    the "true" length of the string. (Note: If implementing in Java,please use a character array
    so that you can perform this operation in place.)
    EXAMPLE
    Input: "Mr John Smith ", 13 Output: "Mr%20John%20Smith"
     */

    /*
    Palindrome Permutation: Given a string, write a function to check if it is a permutation
    of a palin­ drome. A palindrome is a word or phrase that is the same  forwards and backwards.
    A permutation is a rearrangement of letters. The palindrome does not need to be limited
    to just dictionary words.

    EXAMPLE
    Input: Tact Coa
    Output: True (permutations: "taco cat", "atco eta", etc.)
     */

    /*
    One Away: There are three types of edits that can be performed on strings: insert a character,
    remove a character, or replace a character. Given two strings, write a function to check if
    they are one edit (or zero edits) away.
    EXAMPLE
    pale, ple -> true pales, pale -> true pale, bale -> true pale, bake -> false
     */

}
