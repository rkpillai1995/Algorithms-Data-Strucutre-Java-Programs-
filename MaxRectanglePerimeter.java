import java.util.Scanner;

/**
 * This program computes the  maximum perimeter of a rectangle that fits insider of the given coordinates
 *
 * @author Rajkumar Lenin Pillai
 * @author Kunal Nayyar
 */


class MyStack {
    /*
    A simple stack implementation of Array
     */
    private int top;
    private int[] storage;

    MyStack(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException(
                    "Stack's capacity must be positive");
        storage = new int[capacity];
        top = -1;
    }

    void push(int value) {
        if (top == storage.length)
            throw new StackException("Stack's underlying storage is overflow");
        top++;
        storage[top] = value;
    }

    int peek() {
        if (top == -1)
            throw new StackException("Stack is empty");
        return storage[top];
    }

    int pop() {
        if (top == -1)
            throw new StackException("Stack is empty");
        return storage[top--];
    }

    int length() {
        if (top == -1)
            return 0;
        else return top - (-1);
    }

    boolean isEmpty() {
        return (top == -1);
    }

    public class StackException extends RuntimeException {
        public StackException(String message) {
            super(message);
        }
    }
}

public class MaxRectanglePerimeter {


    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int cords;
        int input;
        cords = sc.nextInt() * 2;

        // input stored in cordArray
        int cordArray[] = new int[cords];
        for (int i = 0; i < cords; i++) {
            input = sc.nextInt();
            cordArray[i] = input;
        }

        MyStack stackOfxCords = new MyStack(cords/2); // stack for x
        MyStack stackOfyCords = new MyStack(cords/2);   // stack for y
        int maxPerim = 0;
        int curPos = cordArray[0];      // pointer to current x
        int x = 0, y = 0;               // x stores value of the previous x
                                        // y stores value of the previous y

        for (int i = 3; i < cords; i = i + 4) {
            if (stackOfyCords.isEmpty() && stackOfxCords.isEmpty()) {
                stackOfyCords.push(cordArray[i]);
                stackOfxCords.push(cordArray[i - 1]);
                curPos = cordArray[i - 3];

            } else if (stackOfyCords.peek() < cordArray[i]) {
                stackOfyCords.push(cordArray[i]);
                stackOfxCords.push(cordArray[i - 1]);
                curPos = cordArray[i - 3];
            } else if (stackOfyCords.peek() > cordArray[i]) {
                curPos = cordArray[i - 3];
                int tempx = 0;      // keeps track of last popped x
                int tempy = 0;      // keeps track of last popped y


                // loop calculates the perimeter of the local rectangle and pops values if necessary
                for (int k = stackOfyCords.peek(); k > cordArray[i]; k--) {

                    if (stackOfyCords.isEmpty() & stackOfyCords.isEmpty()) {
                        break;
                    }

                    k = stackOfyCords.peek();
                    x = stackOfxCords.peek();
                    if (maxPerim == 0) {
                        maxPerim = 2 * (curPos - x + k);
                    } else if (2 * (curPos - x + k) > maxPerim) {
                        maxPerim = 2 * (curPos - x + k);
                    }
                    if (stackOfxCords.isEmpty() && stackOfyCords.isEmpty()) {
                        stackOfxCords.push(x);
                        stackOfyCords.push(cordArray[i]);
                    } else if (stackOfyCords.peek() > cordArray[i]) {
                        tempy = stackOfyCords.pop();
                        tempx = stackOfxCords.pop();
                    }

                }
                stackOfxCords.push(tempx);
                stackOfyCords.push(cordArray[i]);
            }
        }
        System.out.println(maxPerim);
    }
}