import java.util.Scanner;
interface ILinkedList {

    public void addToIndex(int index, Object element);

    public void add(Object element);

    public Object get(int index);


    public void set(int index, Object element);

    public void clear();

    public boolean isEmpty();

    public void remove(int index);

    public int size();

    public ILinkedList sublist(int fromIndex, int toIndex);

    public boolean contains(Object o);
}
public class DoubleLinkedList implements ILinkedList {
    public boolean error=false;

    private Node head;
    private Node tail;
    static int size = 0;

    public static class Node {
        private Object element;
        private Node next, previous;

        public Node(Object element) {
            this.element = element;
        }
    }

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }
    public void add(Object element) {
        try{
            Node newNode =new Node(element);
            if (isEmpty()){
                head = newNode;
                tail= newNode;
            }
            else{
                tail.next=newNode;
                newNode.previous=tail;
            }
            tail=newNode;
            size++;
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public void addToIndex(int index, Object element) {
        try{
            Node newNode =new Node(element);
            if (isEmpty() && index==0){
                head = newNode;
                tail= newNode;

            }
            else if (index == 0) {
                newNode.next = head;
                head = newNode;
            }
            else{
                Node current = head;
                int c=1;
                while (c< index) {
                    current = current.next;
                    c++;
                }
                newNode.next=current.next;
                newNode.previous=current;
                current.next = newNode;
            }

            size++;
        }catch (Exception e){
            System.out.println("Error");
        }
    }
    public Object get(int index) {
        DoubleLinkedList.Node current = null;
        try {

            current = head;
            int c = 0;
            while (c < index) {
                current = current.next;
                c++;
            }
            return (current.element);


        } catch (Exception e) {
            return "Error";
        }
    }

    public void set(int index, Object element) {
        if (head == null&&index==0) add(element);
        else {
            try {
                Node current = head;

                int c = 0;
                while (c < index) {
                    current = current.next;
                    c++;
                }
                current.element = element;


            } catch (Exception e) {
                System.out.println("Error");
                error=true;
            }

        }

    }
    public void clear() {
        head = null;
        tail=null;
        size = 0;
    }
    public void remove(int index) {
        try {
            Node current = head;
            Node after=null;
            Node before=null;

            if (index==0){
                head=current.next;
                current=null;
            }
            else {
                int c = 0;
                while (c < index) {
                    current = current.next;
                    c++;
                }
                if(index<size-1){
                    after = current.next;
                    before = current.previous;
                    after.previous = before;
                    before.next = after;
                    current.next = null;
                    current.previous = null;
                } else if (index == size - 1) {
                    before = current.previous;
                    before.next = null;
                    current.next = null;
                    current.previous = null;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
            error=true;
        }
    }

    public boolean contains(Object o) {
        Node current = head;
        Boolean flag = false;
        if (head == null) {

            return false;
        }
        while (current != null) {

            if((int)current.element == (int) o) {
                flag = true;
            }
            current = current.next;
        }
        return flag;
    }



    public ILinkedList sublist(int fromIndex, int toIndex) {
        Node current = head;
        DoubleLinkedList L2 = new DoubleLinkedList();
        L2.head=null;

        if (head == null || fromIndex>toIndex) {
            System.out.println("Error");
        }else {
            for (int i = 0; i <= toIndex; i++) {
                if (i >= fromIndex) {
                    try{
                        Node newNode =new Node(current.element);
                        if (L2.head==null){
                            L2.head = newNode;
                        }
                        else{
                            L2.tail.next=newNode;
                            newNode.previous=tail;
                        }
                        L2.tail=newNode;

                    }catch (Exception e){
                        System.out.println("Error");
                    }
                }
                current = current.next;
            }
            L2.display();
        }

        return L2;
    }


    public boolean isEmpty() {
        return size==0;
    }
    public int size() { return  size;   }
    public void display() {
        Node current = head;


        System.out.printf("[");
        while (current != null) {
            System.out.print(current.element);
            current = current.next;
            if (current != null) System.out.printf(", ");
        }
        System.out.println("]");

    }

    public static void main (String[]args) {

        DoubleLinkedList DLL = new DoubleLinkedList();

        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for(int i = 0; i < s.length; ++i)
                arr[i] = Integer.parseInt(s[i]);
            for(int i = 0; i < s.length; ++i){
                DLL.add(arr[i]);
            }
        }
        String op = sc.nextLine();

        switch (op) {
            case "add":
                int numAdd = sc.nextInt();
                DLL.add(numAdd);
                DLL.display();
                break;
            case "addToIndex":
                int InsertionIndex = sc.nextInt();
                if (InsertionIndex<0||InsertionIndex>=DLL.size()){
                    System.out.println("Error");
                    break;
                }
                int InsertionValue = sc.nextInt();
                DLL.addToIndex(InsertionIndex, InsertionValue);
                if (!DLL.isEmpty()) DLL.display();
                break;

            case "get":
                int getIndex = sc.nextInt();
                if (getIndex<0){
                    System.out.println("Error");
                    break;
                }
                System.out.println(DLL.get(getIndex));
                break;

            case "set":
                int setIndex = sc.nextInt();
                if (setIndex<0){
                    System.out.println("Error");
                    break;
                }
                int setValue = sc.nextInt();
                DLL.set(setIndex, setValue);
                if (DLL.error == false) DLL.display();
                break;
            case "clear":
                DLL.clear();
                DLL.display();
                break;
            case "isEmpty":

                if (DLL.isEmpty())
                    System.out.println("True");
                else
                    System.out.println("False");
                break;

            case "remove":

                int removeIndex = sc.nextInt();
                if (removeIndex<0 || removeIndex>=DLL.size()){
                    System.out.println("Error");
                    break;
                }
                DLL.remove(removeIndex);
                if (DLL.error == false) DLL.display();
                break;
            case "sublist":
                int StartIndex = sc.nextInt();
                int EndIndex = sc.nextInt();
                if (StartIndex < 0 || EndIndex < 0 ||EndIndex>= DLL.size()) {
                    System.out.println("Error");
                    break;
                } else {
                    try {
                        DLL.sublist(StartIndex, EndIndex);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                }
                break;
            case "contains":
                int exist = sc.nextInt();
                if (DLL.contains(exist))
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "size":

                System.out.println(DLL.size());
                break;


        }

    }



}