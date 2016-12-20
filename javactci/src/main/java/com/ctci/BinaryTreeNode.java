package com.ctci;

/**
 * Created by tonyxu on 12/15/16.
 */

/*

// Notes:

Trees vs Binary Trees: Binary trees have up to 2 children, Trees can have up to n children.

Binary Tree vs Binary Search Tree.

BST is a special tree where for every node, left children <= node < right children
The definition could be different, some trees won't allow for dupe values, sometimes the dupe
values will be on the right.

Balanced vs Unbalanced:
Not all binary trees are balanced. A balanced tree means the height between the left and right subtree
differs by at most 1. Enough to ensure O(Log n) times for insert and find.

Red Black Trees and AVL trees are examples of balanced trees.

Complete Binary Tree:
A binary tree is complete if it is filled on all levels, except possibly the last level, and that
level is filled from left to right.

Full Binary Tree:
A full binary tree is when every node either has 0 or 2 children. That is, no node has one child.

Perfect Binary Tree:
A perfect binary tree is is both full and complete. All leaf nodes will be at the same level, and
this level has the max number of nodes. Very rare, must have 2^k - 1 nodes, where k is # of levels.


Binary Heap (Min Heap, max is just opposite):
A min heap is a complete binary tree (filled all levels except maybe last) where each node is smaller
than it's children. The root, therefore, is the minimum value of the heap.

Insert: We always insert the element at the bottom, and then we bubble up. We swap the element with
it's parents until its in the correct spot. Takes O(log n)

Extract min element: This is easy, the element is the root. However, we have to remove it.
So we remove it and swap it with the last element. Then we bubble down, swapping it with the min of
it's 2 children, until the heap property is restored. Takes O(log n)

Can easily store heap in an array. https://en.wikipedia.org/wiki/Binary_heap#Heap_implementation


Tries: (Prefix Tree)
An N-ary tree where each node stores a character. Each path down may represent a word.
For example, the fact that there is a * after nodes represents that it is a word. Look at page
116 in the book.

A Trie can tell us if a word is a prefix of another word very quickly. It takes O(K) time where k
is the length of the word.

The basic form is that of a linked set of nodes, where each node contains an array of child pointers,
one for each symbol in the alphabet (so for the English alphabet, one would store 26 child pointers
and for the alphabet of bytes, 256 pointers)

*/

public class BinaryTreeNode {
    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public static void inOrderTraversal(BinaryTreeNode root){
        if(root == null){
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.value);
        System.out.print(" ");
        inOrderTraversal(root.right);
    }

    public static void preOrderTraversal(BinaryTreeNode root){
        if(root == null){
            return;
        }

        System.out.print(root.value);
        System.out.print(" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public static void postOrderTraversal(BinaryTreeNode root){
        if(root == null){
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value);
        System.out.print(" ");
    }
}




