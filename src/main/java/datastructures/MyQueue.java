package datastructures;

import java.util.Stack;

public class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /*
    1. Push all contents from stack1 to stack2
    2. Now, push the new value to stack1
    3. Push contents back to stack1 from stack2
    4. Top of the stack1 refers to first element inserted
    5. Maintaining FIFO
     */
    public void enqueue(int value) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(value);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    /**
     * Stack1 top contains the first element inserted
     * Because of the logic implemented in enqueue
     * @return Value of the first element inserted
     */
    public Integer dequeue() {
        if(stack1.isEmpty()) {
            return null;
        }
        return stack1.pop();
    }

    public int peek() {
        return stack1.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }

    public static void main(String[] args) {

    }
}
