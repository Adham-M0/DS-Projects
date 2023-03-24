import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack {

    /*** Removes the element at the top of stack and returnsthat element.
     * @return top of stack element, or through exception if empty
     */

    public Object pop();

    /*** Get the element at the top of stack without removing it from stack.
     * @return top of stack element, or through exception if empty
     */

    public Object peek();

    /*** Pushes an item onto the top of this stack.
     * @param //object to insert*
     */

    public void push(Object element);

    /*** Tests if this stack is empty
     * @return true if stack empty
     */
    public boolean isEmpty();

    public int size();
}


public class MyStack implements IStack {
    class Node {
        int data;
        Node next;

        // constructor to create new node
        Node(int data)
        {
            this.data = data;
            this.next = null;
        }
    }

    // Initially both head and tail are not
    // pointing to any other node
    Node head = null;
    Node tail = null;


    // display linked list
    void displayNodes()
    {

        Node current = head;
        if (head == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        while (current != null) {

            System.out.print(current.data);
            if(current.next != null)
            {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.print("]");
        System.out.println();
    }
    public void add(Object data) {

        Node newNode = new Node((Integer) data);

        // Checks if the list is empty
        if (head == null) {
            // If list is empty, both head and
            // tail will point to new node
            head = newNode;
            tail = newNode;
        }
        else {

            tail.next = newNode;
            // storing newnode in tail
            tail = newNode;
        }

    }

    @Override
    public Object pop() {
        Node current = head;
        try{
                    Object x = current.data;
            if (head == null) {
                System.out.println("Error");
            }
            head = current.next;
            current.next = null;
            return x;
        } catch (Exception e) {
            return "Error";
        }

    }

    @Override
    public Object peek() {
        Node current = head;
        try{
            Object x = current.data;
            if (head == null) {
                System.out.println("Error");
            }
            return x;
        } catch (Exception e) {
            return "Error";
        }
    }

    @Override
    public void push(Object element) {
        int index = 0;
        Node current = head;
        Node newNode = new Node((Integer) element);
        if (head == null) {
            System.out.println("Empty");
            return;
        }
            current = head;
            newNode.next = current;
            newNode.data = (int) element;
            head = newNode;
    }

    @Override
    public boolean isEmpty() {
        if(head == null && tail == null)
            return true;
        else
            return false;
    }

    @Override
    public int size() {
        // Initially zero
        int count = 0;

        Node currentNode = head;
        // iterate until all the nodes are present
        while (currentNode != null) {

            count++;
            currentNode = currentNode.next;
        }
        // return the count
        return count;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        MyStack L1 = new MyStack();
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        //System.out.println(Arrays.toString(s));
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for(int i = 0; i < s.length; ++i)
                arr[i] = Integer.parseInt(s[i]);
            for(int i = 0; i < s.length; ++i){
                L1.add(arr[i]);
            }
        }
        //System.out.println(Arrays.toString(arr));
        //L1.displayNodes();
        String operation = sc.nextLine();
        Boolean ErrFlag = false;
        switch(operation){
            case "push":
                int NumAdd = sc.nextInt();
                L1.push(NumAdd);
                L1.displayNodes();
                break;
            case "pop":
                Node current = L1.head;
                    if (current == null) {
                        System.out.println("Error");
                    }else {
                        L1.pop();
                        L1.displayNodes();
                    }
                break;
            case "peek":
                Object PeekElement = L1.peek();
                System.out.println(PeekElement);
                //L1.displayNodes();
                break;
            case "isEmpty":
                Boolean isEmpty = L1.isEmpty();
                if (isEmpty)
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "size":
                int size = L1.size();
                System.out.println(size);
                break;
        }
    }

}