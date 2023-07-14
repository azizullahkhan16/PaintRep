package uicomponents.MyLinkedList;

import uicomponents.MyShapes.MyShape;

import java.io.Serializable;

/**
 * This class creates a stack to store the shapes
 */
public class MyRedoStack implements Serializable {


    public class MyNode implements Serializable{
        public MyShape shape; // shape is the type of node
        public MyNode next; // next node

        /**
         * This is the constructor to initialize the object of node
         * @param shape Shape to be added
         */
        public MyNode(MyShape shape) {
            this.shape = shape;
            next = null;
        }
    }

    MyNode head; // node for the head of stack
    int count = 0; // variable to count the number of shapes present in the stack

    /**
     * This method checks if the stack is empty or not
     * @return It returns true if the stack is empty otherwise false
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * This method inserts the shape in the stack
     * @param shape Shape to be inserted
     */
    public void push(MyShape shape) {
        count++; // Increasing the count by 1

        // If the stack is empty, then create a new node, else add a node before head
        if (head == null) head = new MyNode(shape);
        else {
            MyNode temp = new MyNode(shape);
            temp.next = head;
            head = temp;
        }
    }

    /**
     * This method pops the shape out of the stack
     * @return Shape which got popped.
     */
    public MyShape pop() {
        count--; // decrease the count by 1.

        // if the stack is null, then return null, else return the shape from the top of the stack
        if(head == null) return null;
        else {
            MyShape shape = head.shape;
            head = head.next;
            return shape;
        }
    }

    public void clear(){
        head = null;
    }
}
