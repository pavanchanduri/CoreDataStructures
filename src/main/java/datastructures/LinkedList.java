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

    public Node findMiddleNode() {
        // Initialize slow pointer to the head of the linked list
        Node slow = head;

        // Initialize fast pointer to the head of the linked list
        Node fast = head;

        // Traverse the linked list with two pointers: slow and fast
        // slow moves one node at a time, while fast moves two nodes at a time
        while (fast != null && fast.next != null) {
            // Move slow pointer to the next node
            slow = slow.next;

            // Move fast pointer to the next two nodes
            fast = fast.next.next;
        }

        // Return the Node object representing the middle node of the linked list
        return slow;
    }

    public boolean hasLoop() {
        // Initialize slow pointer to the head of the linked list
        Node slow = head;

        // Initialize fast pointer to the head of the linked list
        Node fast = head;

        // Traverse the linked list with two pointers: slow and fast
        // slow moves one node at a time, while fast moves two nodes at a time
        while (fast != null && fast.next != null) {
            // Move slow pointer to the next node
            slow = slow.next;

            // Move fast pointer to the next two nodes
            fast = fast.next.next;

            // If slow pointer meets fast pointer, then there is a loop in the linked list
            if (slow == fast) {
                return true;
            }
        }

        // If the loop has not been detected after the traversal, then there is no loop in the linked list
        return false;
    }

    public Node findKthFromEnd(int k) {
        Node slow = head; // Initialize slow pointer at head
        Node fast = head; // Initialize fast pointer at head

        // Move fast pointer k steps ahead
        for (int i = 0; i < k; i++) {
            if (fast == null) { // If k is out of bounds, return null
                return null;
            }
            fast = fast.next; // Move the fast pointer to the next node
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            slow = slow.next; // Move the slow pointer to the next node
            fast = fast.next; // Move the fast pointer to the next node
        }

        return slow; // Return the kth node from the end (slow pointer)
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

    public void merge(LinkedList otherList) {
        // get the head node of the other linked list
        Node otherHead = otherList.getHead();
        // create a dummy node to serve as the head of the merged linked list
        Node dummy = new Node(0);
        // create a current node to keep track of the last node in the merged list
        Node current = dummy;

        // iterate through both input linked lists as long as they are not null
        while (head != null && otherHead != null) {
            // compare the values of the head nodes of the two lists
            if (head.value < otherHead.value) {
                // append the smaller node to the merged list and
                //update the head of that list to its next node
                current.next = head;
                head = head.next;
            } else {
                // append the smaller node to the merged list and
                //update the head of that list to its next node
                current.next = otherHead;
                otherHead = otherHead.next;
            }
            // update the "current" node to be the last node in the merged list
            current = current.next;
        }

        // if either of the input lists still has nodes,
        // append them to the end of the merged list
        if (head != null) {
            current.next = head;
        } else {
            current.next = otherHead;
            // If current list is empty, update tail to last node of other list
            // Otherwise, tail remains the last node of the current list
            tail = otherList.getTail();
        }

        // update the head of the current list to be the second node
        // in the merged list (since the first node is the dummy node)
        head = dummy.next;
        // update the length of the current list to reflect the merged list
        length += otherList.getLength();
    }

    /*
    Linked List  3 -> 8 -> 5 -> 10 -> 2 -> 1
    First Partition  0 -> 3 -> 2 -> 1
    Second Partition 0 -> 8 -> 5 -> 10
    Combined 3 -> 2 -> 1 -> 8 -> 5 -> 10
     */
    public void partitionList(int x) {
        // Step 1: Check for an empty list.
        // If the list is empty, there is nothing
        // to partition, so we exit the method.
        if (head == null) return;

        // Step 2: Create two dummy nodes.
        // These dummy nodes act as placeholders
        // to simplify list manipulation.
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);

        // Step 3: Initialize pointers for new lists.
        // 'prev1' and 'prev2' will track the end nodes of
        // the two lists that are being created.
        Node prev1 = dummy1;
        Node prev2 = dummy2;

        // Step 4: Start with the head of the original list.
        Node current = head;

        // Step 5: Iterate through the original list.
        while (current != null) {

            // Step 6: Compare current node value with 'x'.
            // Nodes are partitioned based on their value
            // being less than or greater than/equal to 'x'.

            // Step 6.1: If value is less than 'x',
            // add node to the first list.
            if (current.value < x) {
                prev1.next = current;  // Link node to the end of the first list.
                prev1 = current;       // Update the end pointer of the first list.
            } else {
                // Step 6.2: If value is greater or equal,
                // add node to the second list.
                prev2.next = current;  // Link node to the end of the second list.
                prev2 = current;       // Update the end pointer of the second list.
            }

            // Move to the next node in the original list.
            current = current.next;
        }

        // Step 7: Terminate the second list.
        // This prevents cycles in the list.
        prev2.next = null;

        // Step 8: Connect the two lists.
        // The first list is followed by the second list.
        prev1.next = dummy2.next;

        // Step 9: Update the head of the original list.
        // The head now points to the start of the first list.
        head = dummy1.next;
    }

    public void reverseBetween(int startIndex, int endIndex) {
        // Check: If linked list is empty, nothing to reverse.
        // Exit the method.
        if (head == null) return;

        // Create a 'dummyNode' that precedes the head.
        // Simplifies handling edge cases.
        Node dummyNode = new Node(0);
        dummyNode.next = head;

        // 'previousNode' is used to navigate to the node
        // right before our sublist begins.
        Node previousNode = dummyNode;

        // Move 'previousNode' to node just before sublist.
        for (int i = 0; i < startIndex; i++) {
            previousNode = previousNode.next;
        }

        // 'currentNode' marks the first node of sublist.
        Node currentNode = previousNode.next;

        // Loop reverses the section from startIndex to endIndex.
        for (int i = 0; i < endIndex - startIndex; i++) {

            // 'nodeToMove' is the node we'll move to sublist start.
            Node nodeToMove = currentNode.next;

            // Detach 'nodeToMove' from its current position.
            currentNode.next = nodeToMove.next;

            // Attach 'nodeToMove' at the beginning of the sublist.
            nodeToMove.next = previousNode.next;

            // Move 'nodeToMove' to the start of our sublist.
            previousNode.next = nodeToMove;
        }

        // Adjust 'head' if the first node was part of sublist.
        head = dummyNode.next;
    }

    public void swapPairs() {

        // Create a dummy node pointing to the head
        // This simplifies edge cases, like swapping the first pair
        Node dummy = new Node(0);
        dummy.next = head;

        // previous tracks the node before the current pair
        Node previous = dummy;

        // first is the first node in the pair to be swapped
        Node first = head;

        // Loop while there are at least two nodes to swap
        while (first != null && first.next != null) {

            // second is the second node in the pair
            Node second = first.next;

            // Point previous to second, starting the swap
            previous.next = second;

            // Point first to the node after the second
            first.next = second.next;

            // Point second to first, completing the swap
            second.next = first;

            // Move previous to first (end of swapped pair)
            previous = first;

            // Move first to the next pair's first node
            first = first.next;
        }

        // Reset head to point to the new start of the list
        head = dummy.next;
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

        LinkedList l1 = new LinkedList(1);
        l1.append(3);
        l1.append(5);
        l1.append(7);

        LinkedList l2 = new LinkedList(2);
        l2.append(4);
        l2.append(6);
        l2.append(8);

        l1.merge(l2);

        l1.printAll();
    }
}