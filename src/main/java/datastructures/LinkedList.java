package datastructures;

import java.util.HashSet;

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

    public LinkedList append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
        return this;
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

    public LinkedList prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
        return this;
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
        // Set temp to the current head of the linked list
        Node temp = head;
        // Set the new head to be the current tail
        head = tail;
        // Set the new tail to be the previous head (stored in temp)
        tail = temp;

        // Set after to be the next node after the current head
        Node after;
        // Initialize before to null, as the first node in the reversed list will not have a previous node
        Node before = null;

        // Loop through the linked list, reversing the order of the nodes
        for (int i = 0; i < length; i++) {
            // Set after to be the next node after the current node
            after = temp.next;
            // Set the current node's next pointer to the previous node
            temp.next = before;
            // Set before to be the current node, as it will be the previous node in the next iteration of the loop
            before = temp;
            // Set temp to be the next node in the linked list
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

    public void reverseBetween(int startIndex, int endIndex) {
        if(head==null) return;
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node previousNode = dummyNode; //track of the node before the segment to be reversed
        for(int i=0;i<startIndex;i++) {
            previousNode = previousNode.next; //Position this just before the start node of the segment
        }
        Node currentNode = previousNode.next;
        for(int i=0;i<endIndex-startIndex;i++) {
            Node nodeToMove = currentNode.next; //Node to cut from the segment and place at the front
            currentNode.next = nodeToMove.next;
            nodeToMove.next = previousNode.next;
            previousNode.next = nodeToMove;
        }
        head = dummyNode.next;
    }

    public void swapPairs() {
        //   +===================================================+
        //   |                                                   |
        //   | Description:                                      |
        //   | - Swaps every two adjacent nodes in the linked    |
        //   |   list.                                           |
        //   | - The method modifies the list in place.          |
        //   |                                                   |
        //   | Behavior:                                         |
        //   | - A dummy node is used to simplify swapping the   |
        //   |   first pair.                                     |
        //   | - In each iteration, two nodes (`first` and       |
        //   |   `second`) are swapped by adjusting pointers.    |
        //   | - The `previous` pointer helps reconnect the      |
        //   |   swapped pairs to the rest of the list.          |
        //   | - The `first` pointer then moves forward two      |
        //   |   nodes at a time.                                |
        //   | - At the end, `head` is updated to point to the   |
        //   |   new first node.                                 |
        //   +===================================================+

        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node previous = dummyNode;
        Node first = head;
        while(first!=null&&first.next!=null) {
            Node second = first.next;
            //Swap the nodes
            first.next = second.next;
            second.next = first;
            previous.next = second;
            //Move pointers
            previous = first;
            first = first.next;
        }
        head=dummyNode.next;
    }

    public void insertionSort() {
        // If the list has less than 2 elements, it is already sorted
        if (length < 2) {
            return;
        }

        // Start with a sorted list containing only the first element
        Node sortedListHead = head;

        // Start with the second element in the unsorted list
        Node unsortedListHead = head.next;

        // Mark the end of the sorted list
        sortedListHead.next = null;

        // Iterate over the unsorted list
        while (unsortedListHead != null) {

            // Take the first element in the unsorted list
            Node current = unsortedListHead;

            // Move to the next element in the unsorted list
            unsortedListHead = unsortedListHead.next;

            // If the current element is smaller than the first element of the sorted list
            if (current.value < sortedListHead.value) {
                // Insert the current element at the beginning of the sorted list
                current.next = sortedListHead;
                // Update the sorted list head
                sortedListHead = current;
            } else {
                // Start at the beginning of the sorted list
                Node searchPointer = sortedListHead;

                // Search for the correct insertion point
                while (searchPointer.next != null && current.value > searchPointer.next.value) {
                    // Move to the next element in the sorted list
                    searchPointer = searchPointer.next;
                }

                // Insert the current element after searchPointer
                current.next = searchPointer.next;
                searchPointer.next = current;
            }
        }

        // Update the head of the sorted list
        head = sortedListHead;

        // Update the tail of the sorted list
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        tail = temp;
    }

    public static void main(String[] args) throws Exception {
        LinkedList ll = new LinkedList(1);
        ll
                .append(2).append(3).append(3).append(1)
                .append(3).append(4).append(5).append(6)
                .append(7).append(6).append(8).append(9)
                .append(10).prepend(0);
        ll.printList();
        ll.reverse();
        ll.printList();
        ll.reverse();
        ll.removeDuplicates();
        ll.printList();
        ll.reverseBetween(2,7);
        ll.printList();
        ll.swapPairs();
        ll.printList();
        ll.partitionList(8);
        ll.printList();
    }
}