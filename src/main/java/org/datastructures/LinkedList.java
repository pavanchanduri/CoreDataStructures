package org.datastructures;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value+" -> ");
            temp = temp.next;
        }
        System.out.print(" null ");
        System.out.println();
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nLinked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node temp = head;
        Node pre = head;
        while(temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) {
            tail = null;
        }
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value)  {
        if (index < 0 || index > length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node prev = get(index - 1);
        Node temp = prev.next;

        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp;
    }

    public void reverse() {
        Node temp = getHead();
        head = getTail();
        tail = temp;
        Node after = temp.next;
        Node before = null;
        for(int i=0;i<length;i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

    public Node findKthNodeFromLast(int k) {

        Node p1 = head;
        Node p2 = head;

        for(int i=0;i<k-1;i++) {
            if(p2.next==null) {
                return null;
            }
            p2=p2.next;
        }

        while(p2.next!=null) {
            p1=p1.next;
            p2=p2.next;
        }
        return p1;
    }

    public void removeDuplicates() {
        HashSet<Integer> hashSet = new HashSet<>();
        Node curr = head;
        Node prev = null;

        while (curr != null) {

            // Check if the element is already in the hash table
            if (hashSet.contains(curr.value)) {

                // Element is present, remove it
                prev.next = curr.next;
            } else {
                // Element is not present, add it to hash table
                hashSet.add(curr.value);
                prev = curr;
            }
            curr = curr.next;
        }
    }

    /*
    Linked List  3 -> 8 -> 5 -> 10 -> 2 -> 1
    First Partition  0 -> 3 -> 2 -> 1
    Second Partition 0 -> 8 -> 5 -> 10
    Combined 3 -> 2 -> 1 -> 8 -> 5 -> 10
     */
    public void partitionList(int x) {
        if (head == null) return;

        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);
        Node prev1 = dummy1;
        Node prev2 = dummy2;
        Node current = head;

        while (current != null) {
            if (current.value < x) {
                prev1.next = current;
                prev1 = current;
            } else {
                prev2.next = current;
                prev2 = current;
            }
            current = current.next;
        }

        prev2.next = null;
        prev1.next = dummy2.next;

        head = dummy1.next;
    }

    public static void main(String[] args) throws Exception {

        LinkedList ll = new LinkedList(1);
        ll.append(2);
        ll.append(3);
        ll.append(1);
        ll.append(4);
        ll.append(2);
        ll.append(5);
        ll.printList();
//        System.out.println(ll.get(3));
//        ll.reverse();
//        ll.printList();
//        Node k = ll.findKthNodeFromLast(3);
//        System.out.println(k.value);
        ll.removeDuplicates();
        ll.printList();
    }
}