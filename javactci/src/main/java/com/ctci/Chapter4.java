package com.ctci;

import java.util.ArrayList;

/**
 * Created by tonyxu on 12/13/16.
 */

public class Chapter4 {

/*
* 4.1 Route Between Nodes: Given a directed graph, design an algorithm to find out
* whether there is a route between two nodes.
 */

    /*
    * 4.2 Minimal Tree: Given a sorted (increasing order) array with unique integer elements,
    * write an algorithm to create a binary search tree with minimal height.
     */

    private static BinaryTreeNode buildMinimalTree(int[] a, int min, int max) {
        if (min > max) {
            return null;
        }

        int mid = (min + max) / 2;
        BinaryTreeNode node = new BinaryTreeNode();
        node.value = a[mid];
        node.left = buildMinimalTree(a, min, mid - 1);
        node.right = buildMinimalTree(a, mid + 1, max);

        return node;
    }

    static void TestBuildMinimalTree() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};

        BinaryTreeNode tree = buildMinimalTree(a, 0, 6);
        BinaryTreeNode.inOrderTraversal(tree);
        System.out.println();
        BinaryTreeNode.preOrderTraversal(tree);

        System.out.println("Test BuildMinimalTree complete \n");
    }

    /*
    * 4.3 List of Depths; Given a binary tree, design an algorithm which creates a linked list
    * of all the nodes at each depth (e.g. if you have a tree with depth D, you'll have D linked
    * lists.
     */

    private static ArrayList<LinkedListNode> depthList = new ArrayList<LinkedListNode>();

    private static void listOfDepths(BinaryTreeNode node, int depth) {
        if (node == null) {
            return;
        }

        LinkedListNode lln = new LinkedListNode(node.value);
        lln.next = null;

        if (depthList.size() <= depth) {
            depthList.add(lln);
        } else {
            LinkedList.appendToTail(depthList.get(depth), node.value);
        }

        listOfDepths(node.left, depth + 1);
        listOfDepths(node.right, depth + 1);
    }

    static void testListOfDepths() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};

        BinaryTreeNode tree = buildMinimalTree(a, 0, 6);
        listOfDepths(tree, 0);

        assert (depthList.size() == 3);

        Chapter2.printLinkedList(depthList.get(0));
        Chapter2.printLinkedList(depthList.get(1));
        Chapter2.printLinkedList(depthList.get(2));

        System.out.println("Test ListOfDepths Complete\n");
    }

    /*
    * 4.4 Check Balanced: Implement a function to check if a binary tree is balanced. For the purpose of
    * this question, a balanced tree is defined to be a tree such that the heights of the two subtrees
    * of any node never differ by more than one.
     */

    // GET BACK TO THIS ONE
    private static boolean isBalanced(BinaryTreeNode root) {
        int leftHeight = GetHeight(root.left);
        int rightHeight = GetHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    private static int GetHeight(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = GetHeight(node.left);
        int rightHeight = GetHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }


    /*
    * 4.5 Validate BST: Implement a function to check if a binary tree is a binary search tree.
    *
     */

    // Do an inorder traversal, and make sure the value is in order increasing
    private static boolean isBST(BinaryTreeNode root) {
        InOrderTraversal(root);
        return returnValue;
    }

    private static int currentValue = Integer.MIN_VALUE;
    private static boolean returnValue = true;

    private static void InOrderTraversal(BinaryTreeNode node) {
        if (node == null) {
            return;
        }

        InOrderTraversal(node.left);
        if (node.value < currentValue) {
            returnValue = false;
        }
        currentValue = node.value;
        InOrderTraversal(node.right);
    }

    /*
    * 4.6 Successor: Write an algorithm to find the "next" node (i.e. in-order successor) of a given
    * node in a binary search tree. You may assume that each node has a link to it's parent.
    *
     */

    // In order traversal does left, node, right.
    // So we need to determine what node we are currently at.
    //
    // If we are currently the left leaf, the next node should always be the parent
    //

//    private static BinaryTreeNode findNextNode(BinaryTreeNode node){
//        return null;
//
//    }


    /*
    * 4.7 Build Order: You are given a list of projects and a list of dependencies (which is a list
    * of pairs of projects, where the second project is dependent on the first project). All of a
    * project's dependencies must be build before the project is. Find a build order that will allow
    * the projects to be built. If there is no valid build order, return an error.
    *
    * EXAMPLE
    * Input:
    * projects: a, b, c, d, e, f
    * dependencies: (a,d), (f,b), (b,d), (f,a), (d,c)
    *
    * output: f, e, a, b, d, c
    *
     */

    // Solution 1:
    /*
    * We first go through and find all the nodes with no dependencies, and add that to the list
    * Then we remove the ones that we added as dependencies, and then now add the rest of the nodes
    * that have no dependencies.
    * We do this until we have no more nodes left, or if there are some that we can't get to, which
    * means it can't be done
     */

    // Solution 2:
    /*
    * We use DFS: everytime we get to the end, it must mean nothing depends on that, so we can
    * put it at the end of the list.
    * When we get done with the children, we add ourselves to the start of the list.
    *
    * We do this until we finish every node.
     */

    // Solution 3:
    // For each node, we recursively call buildOrder.
    // If it has no dependencies, just add it to the list, and mark it as visited.
    // Or else, recursively call in to buildOrder for every dependency. When its done, add the
    // current node to the list, and mark it as visited.
    // We do this for every node.

    /*
    * 4.8 First Common Ancestor: Design an algorithm and write code to find the first common ancestor
    * of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This
    * is not necessarily a binary search tree.
    *
     */

    /*
    * 4.9 BST Sequences. A binary search tree was created by traversing through an array from left
    * to right and inserting each element. Given a binary search tree with distinct elements, print
    * all possible arrays that could have led to this tree.
    *
    * EXAMPLE:
    * Input:   2
    *        1   3
    *
    * Output: {2, 1, 3}, {2, 3, 1}
    *
     */

    /*
    * 4.10 Check Subtree: T1 and T2 are two very large binary trees, with T1 much bigger than T2.
    * Create an algorithm to determine if T2 is a subtree of T1.
    *
    * A Tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is
    * identical to T2. That is, if you cut off the tree at node n, the tree trees would be identical.
    *
     */

    /*
    * 4.11 Random Node: You are implementing a binary tree class from scratch, which, in addition to
    * insert, find, and delete, has a method getRandomNode() which returns a random node from
    * the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm for
    * getRandomNode, and explain how you would implement the rest of the methods.
    *
     */

    /*
    * 4.12 Paths with Sum: You are given a binary tree in which each node contains an integeter value
    * (which might be positive or negative). Design an algorithm to count the nubmer of paths that
    * sum to a given value. The path does not need to start or end at the root or a leaf, but it must
    * go downwards (travelling only from parent nodes to child nodes).
    *
     */
}
