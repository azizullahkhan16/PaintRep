package uicomponents.MyLinkedList;

import uicomponents.MyButtons.ButtonLayer;
import uicomponents.MyToolbar.MyLayerBar;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates linked list for layers button
 */

public class MyLinkedList implements Serializable {

    public class Node implements Serializable{
        public ButtonLayer buttonLayer;
        public Node next;

        public Node(ButtonLayer buttonLayer) {
            this.buttonLayer = buttonLayer;
            next = null;
        }

        public String toString(){
            return String.valueOf(buttonLayer.getLayerNum());
        }
    }

    public Node head;
    private int size;
    private int count;

    public MyLinkedList(int size) {
        this.size = size;
        count = 0;
    }

    public void add(ButtonLayer buttonLayer){
        if(count < size) {
            Node newNode = new Node(buttonLayer);
            if (head == null) head = newNode;
            else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }

            count++;
        }
    }

    public boolean isEmpty(){
        if(head == null) return true;
        return false;
    }

    public void remove(ButtonLayer buttonLayer){
        if(head == null) return;
        if(head.buttonLayer.equals(buttonLayer)) head = head.next;
        else {

            Node current = head.next;
            Node previous = head;

            while (current != null) {
                if (current.buttonLayer.equals(buttonLayer)) {
                    previous.next = current.next;
                    return;
                }
                previous = current;
                current = current.next;

            }
        }

        count--;
    }

    public boolean isLastElement(ButtonLayer buttonLayer){
        if(head == null) return false;

        Node current = head;
        while (current.next != null){
            current = current.next;
        }
        if(current.buttonLayer.equals(buttonLayer)) return true;
        return false;


    }

    public String toString(){
        String text = "";
        Node current = head;
        while (current != null){
            text = text + current.buttonLayer.getText() + "\n";
            current = current.next;
        }

        return text;
    }

    public boolean isFirstElement(ButtonLayer buttonLayer){
        if(head == null) return false;
        if(head.buttonLayer.equals(buttonLayer)) return true;
        return false;
    }

    public void moveDown(ButtonLayer buttonLayer){
        if(head == null || head.next == null) return;
        if(head.buttonLayer.equals(buttonLayer)){
            Node current = head.next;
            head.next = current.next;
            current.next = head;
            head = current;
            return;
        }

        Node previous = head;
        Node current = head.next;
        Node next = current.next;

        while (current.next != null){
            if(current.buttonLayer.equals(buttonLayer)){
                current.next = next.next;
                next.next = current;
                previous.next = next;
                return;
            }
            previous = current;
            current = current.next;
            next = current.next;
        }
    }

    public void moveUp(ButtonLayer buttonLayer){
        if(head == null) return;
        if(head.buttonLayer.equals(buttonLayer)) return;
        if(head.next.buttonLayer.equals(buttonLayer)) {
            Node current = head.next;
            head.next = current.next;
            current.next = head;
            head = current;
            return;
        }
        Node current = head.next.next;
        Node previous = head.next;
        Node ePrevious = head;

        while (current != null){
            if(current.buttonLayer.equals(buttonLayer)){
                previous.next = current.next;
                current.next = previous;
                ePrevious.next = current;
                return;
            }
            ePrevious = previous;
            previous = current;
            current = current.next;
        }
    }

    public ButtonLayer getNext(ButtonLayer buttonLayer){
        if(head == null) return null;
        if (head.next == null) return null;
        if (head.buttonLayer.equals(buttonLayer)) return head.next.buttonLayer;

        Node current = head;
        Node next = head.next;

        while (current.next != null){
            if(current.buttonLayer.equals(buttonLayer)) return next.buttonLayer;
            current = current.next;
            next = current.next;
        }

        return null;


    }

    public ButtonLayer getPrevious(ButtonLayer buttonLayer){
        if(head == null || head.buttonLayer.equals(buttonLayer)) return null;
        Node current = head.next;
        Node previous = head;
        while (current != null){
            if(current.buttonLayer.equals(buttonLayer)) return previous.buttonLayer;
            previous = current;
            current = current.next;
        }

        return null;
    }

    public ButtonLayer peek(){
        return head.buttonLayer;
    }

    public ButtonLayer getIndex(int i){
        if(i==0) return head.buttonLayer;
        Node current = head;
        int counter = 0;
        while (current != null){
            if(i == counter) return current.buttonLayer;
            counter++;
            current = current.next;
        }
        return null;
    }

    public int getCount(){
        return count;

    }

    public ArrayList<Node> getLayersReverse(){
        ArrayList<Node> layers = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            layers.add(temp);
            temp = temp.next;
        }

        return layers;
    }

    public boolean isFull(){
        if(count < size) return false;
        return true;
    }
}
