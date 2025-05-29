package org.datastructures;

public class Stack {

    /*
    1. Use Linked List
    2. Push -> add node at the beginning of the list
    3. Pop -> remove node from the beginning of the list
    4. This way LIFO is achieved
    5. Peek -> return the first node
    6. This way all operations are O(1)
     */
    private Node top;
    private int height;

    public Stack(int value) {
        top = new Node(value);
        height = 1;
    }

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public void push(int value) {
        Node newNode = new Node(value);
        if (top != null) {
            newNode.next = top;
        }
        top = newNode;
        height++;
    }

    public boolean search(int value) throws Exception {
        if(top == null) {
            throw new Exception("Stack is Empty");
        } else {
            Node temp = top;
            while(temp!=null) {
                if(temp.value == value) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    public int getTop() {
        return top.value;
    }

    public int getHeight() {
        return height;
    }

    public void pop() throws Exception {
        if(top==null) {
            throw new Exception("Stack is Empty");
        } else if(height==1) {
            top = null;
            height = 0;
        } else {
            Node temp = top.next;
            top.next = null;
            top = temp;
            height++;
        }
    }

    public int peek() throws Exception {

        if(height==0) {
            throw new Exception("Stack is Empty");
        } else {
            return top.value;
        }
    }

    public boolean isEmpty() {
        return height==0;
    }

    public void printStack() {
        if(top==null) {
            System.out.println("Stack is Empty");
        } else {
            Node temp = top;
            System.out.print("top -> ");
            while(temp.next!=null) {
                System.out.print(temp.value+" -> ");
                temp=temp.next;
            }
            System.out.print(temp.value);
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        Stack stack = new Stack(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.push(4);
        boolean isEmpty = stack.isEmpty();
        System.out.println(isEmpty);
        stack.printStack();
        System.out.println(stack.peek());
        System.out.println(stack.search(4));
    }
}
