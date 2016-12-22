package com.ctci;

/**
 * Created by tonyxu on 12/8/16.
 */

public class LinkedList {

    public static LinkedListNode appendToTail(LinkedListNode head, int value) {
        LinkedListNode curr = head;
        LinkedListNode newNode = new LinkedListNode(value);
        newNode.next = null;

        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = newNode;
        return head;
    }

    public static LinkedListNode removeNode(LinkedListNode head, int value) {
        if (head == null) {
            return head;
        }

        if (head.value == value) {
            return head.next;
        }

        LinkedListNode curr = head;

        while (curr.next != null) {
            if (curr.next.value == value) {
                curr.next = curr.next.next;
                return head;
            }
            curr = curr.next;
        }

        return head;
    }

    public static LinkedListNode reverseLinkedList(LinkedListNode head) {
        if (head == null) {
            return null;
        }

        LinkedListNode prev = null;
        LinkedListNode curr = head;

        while(curr != null) {
            LinkedListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    // Runner Technique: Have 2 nodes, 1 going faster than the other one by some amount, could be
    // used to find end of list, middle of list, etc
}
