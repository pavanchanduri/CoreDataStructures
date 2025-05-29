package org.datastructures;

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
        if(index<0||index>length-1) {
            return null;
        } else {
            Node temp;
            if(index<length/2) {
                temp = head;
                for(int i=0;i<index;i++) {
                    temp = temp.next;
                }
            } else {
                temp = tail;
                for(int i=length-1;i>index;i--) {
                    temp = temp.prev;
                }
            }
            return temp;
        }
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
        if(index<0||index>length) {
            return false;
        } else if(index==0) {
            prepend(value);
        } else if(index==length) {
            append(value);
        } else {
            Node prevNode = get(index-1);
            Node nextNode = prevNode.next;
            Node newNode = new Node(value);
            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = nextNode;
            nextNode.prev = newNode;
            length++;
        }
        return true;
    }

    public Node remove(int index) {
        if(index<0||index>length-1) {
            return null;
        } else if(index==0) {
            return removeFirst();
        } else if(index==length-1) {
            return removeLast();
        } else {
            Node prevNode = get(index-1);
            Node temp = prevNode.next;
            Node nextNode = temp.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            temp.next = null;
            temp.prev = null;
            length--;
            return temp;
        }
    }

    public boolean isPalindrome() {
        if(length==0||length==1) {
            return true;
        } else {
            Node tempHead = head;
            Node tempTail = tail;
            int count = 0;
            while(count<length/2) {
                if(tempHead.value!=tempTail.value) {
                    return false;
                }
                tempHead = tempHead.next;
                tempTail = tempTail.prev;
                count++;
            }
            return true;
        }
    }

    public void partitionList(int x) {
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);
        Node prev1 = dummy1;
        Node prev2 = dummy2;
        Node current = head;
        while(current!=null) {
            if(current.value<x) {
                prev1.next = current;
                current.prev = prev1;
                prev1 = current;
            } else {
                prev2.next = current;
                current.prev = prev2;
                prev2=current;
            }
            current = current.next;
        }
        if(prev1!=dummy1) {
            head = dummy1.next;
            dummy1.next = null;
            head.prev = null;
            prev1.next = dummy2.next;
        } else {
            head = dummy2.next;
        }
        if(prev2!=dummy2) {
            dummy2.next.prev = prev1;
            dummy2 = null;
        }
        prev2.next = null;
    }

    public void reverseBetween(int startIndex, int endIndex) {

        if (head == null || startIndex == endIndex) {
            return;
        }
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        head.prev = dummyNode;
        Node previousNode = dummyNode; //track of the node before the segment to be reversed
        for(int i=0;i<startIndex;i++) {
            previousNode = previousNode.next; //Position this just before the start node of the segment
        }
        Node currentNode = previousNode.next;
        for(int i=0;i<endIndex-startIndex;i++) {
            Node nodeToMove = currentNode.next; //Node to cut from the segment and place at the front
            currentNode.next = nodeToMove.next;
            if(nodeToMove.next!=null) {
                nodeToMove.next.prev = currentNode;
            }
            // Move nodeToMove to the front of the sublist
            nodeToMove.next = previousNode.next;
            previousNode.next.prev = nodeToMove;
            previousNode.next = nodeToMove;
            nodeToMove.prev = previousNode;
        }
        head = dummyNode.next;
        head.prev = null;
    }

    public void swapPairs() {
        if(head==null) return;
        Node dummy = new Node(0);
        dummy.next = head;
        head.prev = dummy;
        Node first = head;
        while(first!=null && first.next!=null) {
            Node second = first.next;
            first.next = second.next;
            if(second.next!=null) {
                second.next.prev = first;
            }
            second.next = first;
            second.prev = first.prev;
            first.prev = second;
            second.prev.next = second;
            first = first.next;
        }
        head = dummy.next;
        head.prev = null;
    }

    public void reverse() {
        Node temp = getHead();
        head = tail;
        tail = temp;
        Node after;
        Node before = null;
        for(int i=0;i<length;i++) {
            after = temp.next;
            temp.next = before;
            temp.prev = after;
            before = temp;
            temp = after;
        }
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