package datastructures;

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }

    public DoublyLinkedList(int value) {
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
            System.out.print(temp.value+" <=> ");
            temp = temp.next;
        }
        System.out.print("null");
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
        System.out.println("\nDoubly Linked List:");
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

    public DoublyLinkedList append (int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
        return this;
    }

    public Node removeLast() {
        if(length == 0) return null;
        Node temp = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    public DoublyLinkedList prepend(int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
        return this;
    }

    public Node removeFirst() {
        if(length == 0) return null;
        Node temp = head;
        if(length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        length--;
        return temp;
    }

    public Node get(int index) {
        // check if the index is out of bounds, return null if true
        if (index < 0 || index >= length) return null;
        // initialize a temporary node with the value of the head
        Node temp = head;
        // if the index is in the first half of the list
        if (index < length/2) {
            // iterate through the list from the head to the index
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            // if the index is in the second half of the list
            // initialize the temporary node with the value of the tail
            temp = tail;
            // iterate through the list from the tail to the index
            for (int i = length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        // return the node at the given index
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if(temp!=null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {

        // Return false if the index is out of range
        if(index < 0 || index > length) return false;

        // If index is 0, prepend the new value to the list
        if(index == 0) {
            prepend(value);
            return true;
        }

        // If index is equal to length, append the new value to the list
        if(index == length) {
            append(value);
            return true;
        }

        // Otherwise, create a new node with the given value
        Node newNode = new Node(value);

        // Get the node before the specified index
        Node before = get(index - 1);

        // Get the node after the specified index
        Node after = before.next;

        // Set the new node's previous and next pointers to the before and after nodes
        newNode.prev = before;
        newNode.next = after;

        // Update the next pointer of the before node and the previous pointer of the after node to point to the new node
        before.next = newNode;
        after.prev = newNode;

        // Increment the length of the linked list
        length++;

        // Return true to indicate that the value was successfully inserted
        return true;
    }

    public Node remove(int index) {
        // Check if index is out of range.
        if(index < 0 || index >= length) return null;

        // If index is the first element, remove it.
        if(index == 0) return removeFirst();

        // If index is the last element, remove it.
        if(index == length - 1) return removeLast();

        // Get the node to remove.
        Node temp = get(index);

        // Update the prev and next pointers to remove the node from the linked list.
        temp.next.prev = temp.prev;
        temp.prev.next = temp.next;

        // Set the next and prev pointers of the removed node to null.
        temp.next = null;
        temp.prev = null;

        // Decrement the length of the linked list.
        length--;

        // Return the removed node.
        return temp;
    }

    public boolean isPalindrome() {
        // if the list has only 1 or 0 nodes, it's automatically a palindrome
        if (length <= 1) return true;

        // set up two pointers starting from opposite ends of the list
        Node forwardNode = head;
        Node backwardNode = tail;

        // iterate through the list until the two pointers meet in the middle
        for (int i = 0; i < length / 2; i++) {
            // if the values at the two pointers don't match, the list is not a palindrome
            if (forwardNode.value != backwardNode.value) return false;

            // move the pointers toward the center of the list
            forwardNode = forwardNode.next;
            backwardNode = backwardNode.prev;
        }

        // if the loop completes without returning false, the list is a palindrome
        return true;
    }

    public void reverse() {
        // 'current' starts at the head of the list. This is the starting point
        // for the reversal process.
        Node current = head;

        // 'temp' is a temporary variable used for swapping nodes. It is initially
        // set to null since we haven't started the swapping process yet.
        Node temp;

        // We enter a loop that will continue as long as 'current' is not null.
        // This loop goes through each node in the list.
        while (current != null) {
            // Store the previous node of 'current' in 'temp'.
            // This is needed because we will be overwriting 'current.prev' next,
            // and we don't want to lose this reference.
            temp = current.prev;

            // The next two lines are where we swap the 'next' and 'prev' references
            // of the 'current' node. This effectively reverses the direction of the
            // links for 'current'.
            current.prev = current.next; // 'prev' now points to what used to be 'next'
            current.next = temp;         // 'next' now points to what used to be 'prev'

            // Move to the next node in the original list. After the swap, the original
            // 'next' node is now in 'current.prev', so we update 'current' to this node.
            current = current.prev;
        }

        // After the while loop, the list is reversed, but our 'head' and 'tail' pointers
        // are still pointing to the original head and tail. So we need to swap them.
        temp = head;   // Store the original head in 'temp'
        head = tail;   // Update head to be the original tail
        tail = temp;   // Update tail to be what was originally the head
    }

    /* 1. We first create two dummy nodes: dummy1 for values < x and dummy2 for values >= x.
       2. We traverse the entire list, attaching nodes to one of the two dummy lists based on their value.
       3. After the loop, we terminate the greater list by setting prev2.next = null.
       4. Then we connect the two lists by linking the end of the first to the start of the second.
       5. Finally, we update head to point to the start of the new rearranged list. */
    public void partitionList(int x) {
        // If the list is empty, nothing to do
        if (head == null) return;

        // Create two dummy nodes to help build two new lists
        Node dummy1 = new Node(0); // List for nodes < x
        Node dummy2 = new Node(0); // List for nodes >= x

        // Use these pointers to build the two lists
        Node prev1 = dummy1;
        Node prev2 = dummy2;
        Node current = head;

        // Traverse the original list
        while (current != null) {
            if (current.value < x) {
                // Attach node to dummy1 list
                prev1.next = current;
                current.prev = prev1;
                prev1 = current;
            } else {
                // Attach node to dummy2 list
                prev2.next = current;
                current.prev = prev2;
                prev2 = current;
            }
            current = current.next;
        }

        // End the second list to avoid any trailing connections
        prev2.next = null;

        // Connect the two lists
        prev1.next = dummy2.next;

        // If dummy2 list has nodes, fix their prev pointer
        if (dummy2.next != null) {
            dummy2.next.prev = prev1;
        }

        // Update head pointer of the main list
        head = dummy1.next;

        // Ensure new head has no previous pointer i.e.,dummy1 is removed
        if (head != null) {
            head.prev = null;
        }
    }

    public void reverseBetween(int startIndex, int endIndex) {
        if (head == null || startIndex == endIndex) {
            return;
        }

        // Create a dummy node to simplify edge cases
        Node dummy = new Node(0);
        dummy.next = head;
        head.prev = dummy;

        // Step 1: Move 'prev' to the node before startIndex
        Node prev = dummy;
        for (int i = 0; i < startIndex; i++) {
            prev = prev.next;
        }

        // Step 2: Start reversing from 'current' = prev.next
        Node current = prev.next;

        // Step 3: Reverse by relocating nodes one at a time
        for (int i = 0; i < endIndex - startIndex; i++) {
            Node nodeToMove = current.next;

            // Remove nodeToMove from its place
            current.next = nodeToMove.next;
            if (nodeToMove.next != null) {
                nodeToMove.next.prev = current;
            }

            // Move nodeToMove to the front of the sublist
            nodeToMove.next = prev.next;
            prev.next.prev = nodeToMove;

            prev.next = nodeToMove;
            nodeToMove.prev = prev;
        }

        // Step 4: Update head in case it changed
        head = dummy.next;
        head.prev = null;
    }

    public void swapNodePairs() {
        // Create a placeholder (dummyNode) node to simplify swapping.
        Node dummyNode = new Node(0);

        // Link the dummyNode node to the start of our list.
        dummyNode.next = head;

        // Initialize 'previousNode' to 'dummyNode' to remember the node
        // before each pair we're swapping.
        Node previousNode = dummyNode;

        // Continue as long as there's a pair of nodes to swap.
        while (head != null && head.next != null) {

            // Identify the first node of the pair to swap.
            Node firstNode = head;

            // Identify the second node of the pair to swap.
            Node secondNode = head.next;

            // Connect the previousNode's 'next' pointer to secondNode,
            // essentially skipping over firstNode.
            previousNode.next = secondNode;

            // Connect firstNode to the node after secondNode.
            // This ensures we don't lose the rest of the list.
            firstNode.next = secondNode.next;

            // Connect secondNode back to firstNode,
            // completing the swap.
            secondNode.next = firstNode;

            // Adjust 'prev' pointers for our doubly linked list.
            // Set secondNode's 'prev' to the node before current pair.
            secondNode.prev = previousNode;

            // Set firstNode's 'prev' to secondNode as they've been swapped.
            firstNode.prev = secondNode;

            // If there's a node after our current pair, set its 'prev' to firstNode.
            if (firstNode.next != null) {
                firstNode.next.prev = firstNode;
            }

            // Move the head pointer to the node after the current pair.
            head = firstNode.next;

            // Update 'previousNode' for the next pair of nodes.
            previousNode = firstNode;
        }

        // After swapping all pairs, update our list's head to
        // start at the node after dummyNode.
        head = dummyNode.next;

        // Ensure the new head's 'prev' is null, indicating the start of list.
        if (head != null) head.prev = null;
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList(3);
        dll.append(8).append(5).append(10).append(2).append(1);
        dll.printList();
        dll.set(2,5);
        dll.printList();
        System.out.println(dll.isPalindrome());
        dll.partitionList(5);
        dll.printList();
    }
}