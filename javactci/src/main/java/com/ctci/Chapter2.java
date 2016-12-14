package com.ctci;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tonyxu on 12/7/16.
 */

class Chapter2 {

    /*
    * Remove Dupes: Write code to remove duplicates from an unsorted linked list.
    * FOLLOW UP
    * How would you solve this problem if a temporary buffer is not allowed?
     */

    // If a temp buffer was not allowed, for each node, I would have to iterated until the end
    // of the linked list, and if i see a dupe, then remove it.
    private static LinkedListNode removeDupes(LinkedListNode head) {
        Set<Integer> seenValues = new HashSet<Integer>();
        LinkedListNode curr = head;

        if (head == null) {
            return null;
        }

        seenValues.add(curr.value);

        while (curr.next != null) {
            if (seenValues.contains(curr.next.value)) {
                curr.next = curr.next.next;
            } else {
                seenValues.add(curr.next.value);
            }
            curr = curr.next;
        }

        return head;
    }

    static void testRemoveDupes() {
        LinkedListNode nodeA = new LinkedListNode(1);
        LinkedListNode nodeB = new LinkedListNode(2);
        LinkedListNode nodeC = new LinkedListNode(3);
        LinkedListNode nodeD = new LinkedListNode(4);
        LinkedListNode nodeE = new LinkedListNode(4);
        LinkedListNode nodeF = new LinkedListNode(5);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = nodeF;
        nodeF.next = null;

        printLinkedList(nodeA);

        removeDupes(nodeA);

        printLinkedList(nodeA);

        System.out.println("RemoveDupes Test Complete");
        System.out.println();
    }

    private static void printLinkedList(LinkedListNode head) {
        while (head != null) {
            System.out.print(head.value);

            // don't print out -> for last value
            if (head.next != null) {
                System.out.print("->");
            }
            head = head.next;
        }

        System.out.println();
    }

    /*
    * Return kths to Last: Implement an algorithm to find the kth to last element of a singly linked list.
    *
    * 1->2->3->4->5
    * k = 2
    *
    * fp: null
    * sp: 4
     */

    private static LinkedListNode findKthToLast(LinkedListNode head, int k) {
        LinkedListNode fastPointer = head;
        LinkedListNode slowPointer = head;

        for (int i = 0; i < k; i++) {
            // if we are don't have k elements, bail out, return null
            if (fastPointer == null) {
                return null;
            }

            fastPointer = fastPointer.next;
        }

        while (fastPointer != null) {
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }

        return slowPointer;
    }

    static void testKthToLast() {
        LinkedListNode nodeA = new LinkedListNode(1);
        LinkedListNode nodeB = new LinkedListNode(2);
        LinkedListNode nodeC = new LinkedListNode(3);
        LinkedListNode nodeD = new LinkedListNode(4);
        LinkedListNode nodeE = new LinkedListNode(5);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = null;

        printLinkedList(nodeA);

        LinkedListNode result = findKthToLast(nodeA, 2);
        assert (result.value == 4);

        System.out.println("TestKthToLast complete");
        System.out.println();
    }

    /*
    * Delete Middle Node: Implement an algorithm to delete a node in the middle,
    * (i.e. any node but the first and last node, not necessarily the exact middle)
    * of a singly linked list, given only access to that node.
    *
    * EXAMPLE:
    * Input: The node c from the linked list a->b->c->d->e->f
    * Result: Nothing is returned, but the new linked list looks like a->b->d->e->f
    *
     */

    private static void deleteMiddleNode(LinkedListNode middle) {
        // if thsi is the last node, or its null, can't do anything
        if (middle == null || middle.next == null) {
            return;
        }

        // copy the next node's value, and then remove that node
        middle.value = middle.next.value;
        middle.next = middle.next.next;
    }

    static void testDeleteMiddleNode() {
        LinkedListNode nodeA = new LinkedListNode(1);
        LinkedListNode nodeB = new LinkedListNode(2);
        LinkedListNode nodeC = new LinkedListNode(3);
        LinkedListNode nodeD = new LinkedListNode(4);
        LinkedListNode nodeE = new LinkedListNode(5);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = null;

        printLinkedList(nodeA);

        deleteMiddleNode(nodeC);

        printLinkedList(nodeA);

        System.out.println("Test Delete Middle Node passed");
        System.out.println();
    }

    /*
    *
    * Partition: Write code to partition a linked list around a value x, such that all nodes
    * less than x come before all nodes great than or equal to x. If x is contained within
    * the list, the value x only need to be after the elements less than x(see below). The partition
    * element x can appear anywhere in the "right partition". It does not need to appear between the
    * left and right partitions.
    *
    * Example
    * Input: 3 - >5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition 5]
    * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
    *
     */

    /*
    * Input: 3 - >5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition 5]
    * LH : 3
    * RH : 5
    * LC : 3 -> null
    * RC : 5 -> 8 -> null
     */

    private static LinkedListNode partitionLinkedList(LinkedListNode head, int x) {
        LinkedListNode leftHead = null;
        LinkedListNode rightHead = null;
        LinkedListNode leftCurr = null;
        LinkedListNode rightCurr = null;

        while (head != null) {
            if (head.value < x) {
                if (leftCurr == null) {
                    leftCurr = head;
                    leftHead = leftCurr;
                } else {
                    leftCurr.next = head;
                    leftCurr = leftCurr.next;
                }
            } else {
                if (rightCurr == null) {
                    rightCurr = head;
                    rightHead = rightCurr;
                } else {
                    rightCurr.next = head;
                    rightCurr = rightCurr.next;
                }
            }
            head = head.next;
        }

        leftCurr.next = rightHead;
        rightCurr.next = null;
        return leftHead;
    }

    static void testPartitionLinkedList() {
        LinkedListNode nodeA = new LinkedListNode(3);
        LinkedListNode nodeB = new LinkedListNode(5);
        LinkedListNode nodeC = new LinkedListNode(8);
        LinkedListNode nodeD = new LinkedListNode(5);
        LinkedListNode nodeE = new LinkedListNode(10);
        LinkedListNode nodeF = new LinkedListNode(2);
        LinkedListNode nodeG = new LinkedListNode(1);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = nodeF;
        nodeF.next = nodeG;
        nodeG.next = null;

        printLinkedList(nodeA);

        LinkedListNode partitionedList = partitionLinkedList(nodeA, 5);

        printLinkedList(partitionedList);
    }


    /*
    * Reverse a linked list
    * Input: 1->2->3->4->5
    * Output: 5->4->3->2->1
    * Curr: null
    * Prev: 5->4->3->2->1->null
    * Next: null
    *
     */

    private static LinkedListNode reverseLinkedList(LinkedListNode head) {
        LinkedListNode curr = head;
        LinkedListNode prev = null;
        LinkedListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;

    }

    static void testReverseLinkedList() {
        LinkedListNode nodeA = new LinkedListNode(1);
        LinkedListNode nodeB = new LinkedListNode(2);
        LinkedListNode nodeC = new LinkedListNode(3);
        LinkedListNode nodeD = new LinkedListNode(4);
        LinkedListNode nodeE = new LinkedListNode(5);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = null;

        printLinkedList(nodeA);

        LinkedListNode result = reverseLinkedList(nodeA);

        printLinkedList(result);
    }

    /*
    * Sum Lists: You have two numbers represented by a linked list, where each node contains a
    * single digit. The digits are stored in reverse order, such that the 1's digit is at the
    * head of the list. Write a function that adds the two numbers and returns the sum as a linked
    * list.
    *
    * EXAMPLE:
    * Input: (7->1->6) + (5->9->2). That is 617 + 295.
    * Output: 2->1->9. That is 912.
    *
    * Follow up:
    * Supports the digits are stored in forward order. Repeat the problem:
    *
    * EXAMPLE:
    * Input: (6->1->7) + (2->9->5). That is 617 + 295.
    * Output: 9->1->2. That is 912.
    *
     */

    private static LinkedListNode sumLists(LinkedListNode n1, LinkedListNode n2) {
        LinkedListNode resultHead = null;
        LinkedListNode resultCurr = null;

        boolean carry = false;

        while (n1 != null && n2 != null) {
            int sum = n1.value + n2.value;
            if (carry) {
                sum++;
            }

            LinkedListNode node;
            if (sum >= 10) {
                carry = true;
                node = new LinkedListNode(sum - 10);
            } else {
                carry = false;
                node = new LinkedListNode(sum);
            }

            if (resultCurr == null) {
                resultCurr = node;
                resultHead = resultCurr;
            } else {
                resultCurr.next = node;
                resultCurr = resultCurr.next;
            }

            n1 = n1.next;
            n2 = n2.next;
        }

        // in case n1 is longer
        while (n1 != null) {
            int sum = n1.value;
            if (carry) {
                sum++;
            }

            LinkedListNode node;
            if (sum >= 10) {
                carry = true;
                node = new LinkedListNode(sum - 10);
            } else {
                carry = false;
                node = new LinkedListNode(sum);
            }

            if (resultCurr == null) {
                resultCurr = node;
                resultHead = resultCurr;
            } else {
                resultCurr.next = node;
                resultCurr = resultCurr.next;
            }

            n1 = n1.next;
        }

        // in case n2 is longer
        while (n2 != null) {
            int sum = n2.value;
            if (carry) {
                sum++;
            }

            LinkedListNode node;
            if (sum >= 10) {
                carry = true;
                node = new LinkedListNode(sum - 10);
            } else {
                carry = false;
                node = new LinkedListNode(sum);
            }

            if (resultCurr == null) {
                resultCurr = node;
                resultHead = resultCurr;
            } else {
                resultCurr.next = node;
                resultCurr = resultCurr.next;
            }

            n2 = n2.next;
        }

        if (carry) {
            LinkedListNode node = new LinkedListNode(1);
            resultCurr.next = node;
            resultCurr = resultCurr.next;
        }

        resultCurr.next = null;

        return resultHead;
    }

    /*
    * Actual solution: Pad the shorter list so that they are the same length.
    * Recursively add, gotta get to end and pass carry back
     */
    private static LinkedListNode sumListsForward(LinkedListNode n1, LinkedListNode n2) {
        LinkedListNode r1 = reverseLinkedList(n1);
        LinkedListNode r2 = reverseLinkedList(n2);
        LinkedListNode rSum = sumLists(r1, r2);
        return reverseLinkedList(rSum);
    }

    /*
     * Input: (7->1->6) + (5->9->2). That is 617 + 295.
     * Output: 2->1->9. That is 912.
     */
    static void testSumLists() {
        LinkedListNode nodeA = new LinkedListNode(7);
        LinkedListNode nodeB = new LinkedListNode(1);
        LinkedListNode nodeC = new LinkedListNode(7);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = null;

        LinkedListNode nodeD = new LinkedListNode(5);
        LinkedListNode nodeE = new LinkedListNode(9);
        LinkedListNode nodeF = new LinkedListNode(2);

        nodeD.next = nodeE;
        nodeE.next = nodeF;
        nodeF.next = null;

        printLinkedList(nodeA);
        printLinkedList(nodeD);

        LinkedListNode sum = sumLists(nodeA, nodeD);
        printLinkedList(sum);

        LinkedListNode nodeG = new LinkedListNode(1);
        nodeF.next = nodeG;
        nodeG.next = null;

        sum = sumLists(nodeA, nodeD);
        printLinkedList(sum);


    }

    /*
    * Palindrome: Implement a function to check if a linked list is a palindrome.
    * Palindrome: 0 -> 1 -> 2 -> 1 -> 0
     */

    // Can reverse the linked list and see if its the same O(n) takes O(n) space
    // Can move a ptr to the half way point, going 2x as fast, and remember what we saw, and compare
    // Can go through once, push it all on stack, then pop and compare
    private static boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reverse = reverseAndClone(head);
        return isEqual(head, reverse);
    }

    /*
     * 1-2->3
     * next: null
     * curr: (3)->(2)->(1)->null
     * prev: (3)->(2)->(1)-> null
     * head: 3
     */
    private static LinkedListNode reverseAndClone(LinkedListNode head) {
        LinkedListNode prev = null;

        while (head != null) {
            LinkedListNode next = head.next;
            LinkedListNode curr = new LinkedListNode(head.value);
            curr.next = prev;
            prev = curr;
            head = next;
        }

        return prev;
    }

    static void testIsPalindrome() {
        LinkedListNode nodeA = new LinkedListNode(0);
        LinkedListNode nodeB = new LinkedListNode(1);
        LinkedListNode nodeC = new LinkedListNode(2);
        LinkedListNode nodeD = new LinkedListNode(1);
        LinkedListNode nodeE = new LinkedListNode(50);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = null;

        printLinkedList(nodeA);
        assert (isPalindrome(nodeA));

        System.out.println("IsPalindrome Test Passed");
        System.out.println();
    }

    private static boolean isEqual(LinkedListNode n1, LinkedListNode n2) {
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        if (n1 != null || n2 != null) {
            return false;
        }

        return true;
    }

    /*
    * Intersection: Given two (singly) linked lists, determine if the two lists intersect.
    * Return the intersecting node. Note that the intersection is defined based on reference, not
    * value. That is if the kth node of the first linked list is the exact same node (by reference)
    * as the jth node of the second linked list, then they are intersecting.
     */


    /*
    * Logic: For 2 nodes to intersect, they have to end at the same node. so if we reverse the
    * linked lists and just go backwards until we either hit the end of one list or if we hit a
    * point where they diverge and return the previous node.
    *
    * 1->2->3->4->5
    * 6->7->3->4->5
    *
    * 5->4->3->2->1
    * 5->4->3->7->6
    *
    * prev: 3
    * hasIntersection = true
    *
    * r1:
    * r2:
    *
    *
     */

    /*
     * Suggested solution: Same basic idea, they must have the same end node. But instead of
     * reversing the lists, we chop off the longer list's beginning so that both are the same length.
     *
     * Then we just go from the start to the end until we find an intersection.
     *
     * Ex.
     * 1->2->3->4->5
     * 6->4->5
     *
     * 1->2->3->4->5 becomes 3->4->5
     *
     * we see that 4 is the same, so we return 4.
     */
    private static LinkedListNode doesIntersect(LinkedListNode n1, LinkedListNode n2) {
        LinkedListNode r1 = reverseLinkedList(n1);
        LinkedListNode r2 = reverseLinkedList(n2);

        LinkedListNode prev = null;
        boolean hasSeenIntersection = false;

        while (r1 != null && r2 != null) {
            if (r1 == r2) {
                hasSeenIntersection = true;
                prev = r1;
            } else {
                if (hasSeenIntersection) {
                    return prev;
                } else {
                    // if we haven't seen an intersection yet and both aren't equal, it means
                    // they don't end at the same note, so no intersection
                    return null;
                }
            }

            r1 = r1.next;
            r2 = r2.next;
        }

        // if we get here with having seen an intersection, they must have intersected on the
        // first node, so just return it.
        if (hasSeenIntersection) {
            return prev;
        }

        // If we get here, it must mean one of the lists is null.
        return null;
    }


    static void testDoesIntersect() {
        LinkedListNode nodeA = new LinkedListNode(1);
        LinkedListNode nodeB = new LinkedListNode(2);
        LinkedListNode nodeC = new LinkedListNode(3);
        LinkedListNode nodeD = new LinkedListNode(4);
        LinkedListNode nodeE = new LinkedListNode(5);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = null;

        printLinkedList(nodeA);

        LinkedListNode nodeF = new LinkedListNode(6);
        LinkedListNode nodeG = new LinkedListNode(7);
        nodeF.next = nodeG;
        nodeG.next = nodeC;

        printLinkedList(nodeF);

        LinkedListNode intersectNode = doesIntersect(nodeA, nodeF);

        assert (intersectNode == nodeC);

        LinkedListNode intersectNode2 = doesIntersect(nodeA, nodeC);

        assert (intersectNode2 == nodeC);

        System.out.println("testDoesIntersect success");
        System.out.println();
    }


    /*
    * Loop Detection: Given a circular linked list, implement an algorithm that returns the node at
    * the beginning of the loop.
    *
    * DEFINITION
    *
    * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an
    * earlier node, so as to make a loop in the linked list.
    *
    * Example:
    * Input: A->B->C->D->E->C [the same C as earlier]
    * Output: C
    *
     */

    /*
     * Solution:
     * p1: 1 slow runner going 1 at a time
     * p2: 1 fast runner going 2 at a time
     * lets call the non intersection part of the loop k
     *
     * when p1 enters the loop, p2 will be at 2k.
     * p2 is k ahead of p1.
     * which means p2 is (loop_size - k) behind p1.
     * p2 will catch p1 1 step at a time. So it will take (loop_size - k) steps to catch p1.
     *
     * Now when they collide, p1 will be at k + loop_size - k; p2 will be at k + (loop_size-k)*2
     * p1 at loop size:
     * p2: loop_size - k
     *
     * now if you have another pointer start at the beginning, when you go k steps, and you move
     * p2 now 1 step at a time. they will meet at the start of the loop.
     *
     */


    // I'm assuing there is a loop, so i'm not checking for the condition where this is no loop
    // and i hit a dead end
    private static LinkedListNode findCircularLinkedListStart(LinkedListNode head){
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next.next;
        }

        while(head != p2){
            head = head.next;
            p2 = p2.next;
        }

        return head;
    }

    static void testFindCircularLinkedListStart() {
        LinkedListNode nodeA = new LinkedListNode(1);
        LinkedListNode nodeB = new LinkedListNode(2);
        LinkedListNode nodeC = new LinkedListNode(3);
        LinkedListNode nodeD = new LinkedListNode(4);
        LinkedListNode nodeE = new LinkedListNode(5);

        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = nodeC;

        LinkedListNode result = findCircularLinkedListStart(nodeA);

        assert (result == nodeC);

        System.out.print("TestFindCircularLinkedListStart success");
        System.out.println();
    }

}
