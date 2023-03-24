import java.util.*;

interface IQueue {
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item);
    /*** Removes the object at the queue rear and returnsit.*/
    public Object dequeue();
    /*** Tests if this queue is empty.*/
    public boolean isEmpty();
    /*** Returns the number of elements in the queue*/
    public int size();
}

public class LinkedListQueue implements IQueue {

    int count=0;
    static Node head;
    static Node tail;
    static int size=0;

    public static class Node {
        int element;
        Node next;

        public Node(int element) {
            this.element = element;
            this.next = null;
        }

    }

    public int get() {
        Node After = head;
        Node current = head;
        int x = 0;
        if (size()-1 != 0) {
            for (int i = 0; i <= size()-1; i++) {
                After = After.next;
                if (i == size()-1 - 1) {

                    x= After.element;
                }
                if(current!=null)
                    current = current.next;
            }
        }
        return x;
    }

    public void addFirst (  int element){
        Node newNode = new Node(element);
        try{
            if (head != null) {
                newNode.next = head;

            }
            head = newNode;


        }
        catch(Exception e){
            System.out.println("Error");
        }
        size=size+1;
    }

    public void removeLast (){
        Node After = head;
        Node current = head;
        if (head == null&& size()-1!=0) {
            System.out.println("Error");
        }
        if (size()-1 != 0) {
            for (int i = 0; i <= size()-1; i++) {
                After = After.next;
                if (i == size()-1 - 1) {

                    current.next = After.next;
                    After.next = null;
                }
                if(current!=null)
                    current = current.next;
            }
        } else {
            head = current.next;
            current.next = null;
        }
    }




    public static void main(String[] args) {
        LinkedListQueue queue1 = new LinkedListQueue();
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];

        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for (int i = 0; i < s.length; ++i)
                arr[s.length-1-i] = Integer.parseInt(s[i]);
        }
        if(!s[0].isEmpty()) {
            for (int i = 0; i < s.length; ++i)
                queue1.enqueue(arr[i]);
        }

        String op = sc.nextLine();
        int item = 0;

        switch (op) {
            case "enqueue":
                item = sc.nextInt();
                queue1.enqueue(item);
                queue1.printQueue();
                break;
            case "dequeue":
                try {
                    Object x = queue1.dequeue();
                    if(x != "Error")
                        queue1.printQueue();
                }catch (Exception e){ break;}

                break;
            case "isEmpty":
                if (queue1.isEmpty()) System.out.println("True");
                else System.out.println("False");
                break;
            case "size":
                System.out.println(queue1.size());
                break;
        }
    }
    @Override
    public Object dequeue() {
        Object temp=get();
        removeLast();       ////////////
        if(size()==0)
            return "Error";
        return temp;
    }

    @Override
    public void enqueue(Object item) {
        try {
            addFirst((int)item);
            count++;
        }catch (Exception e){
            System.out.println("Error");
        }

    }
    @Override
    public boolean isEmpty(){
        if (head==null) return true;
        else  return false;
    }
    @Override
    public int size(){

        return count;
    }
    public void printQueue() {
        Node current = head;

        System.out.printf("[");
        while (current != null) {
            System.out.print(current.element);
            current = current.next;
            if (current != null) System.out.printf(", ");
        }
        System.out.println("]");

    }
}