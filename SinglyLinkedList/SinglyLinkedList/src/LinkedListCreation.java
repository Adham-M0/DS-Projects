import java.util.*;
interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void addToIndex(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}

public class LinkedListCreation implements ILinkedList {
    @Override
    public void addToIndex(int index, Object data) {
        Node current = head;
        Node newNode = new Node((Integer) data);
        if (head == null && index!=0) {
            System.out.println("Empty");
            return;
        } if(index!=0) {
            for (int i = 0; i <= index; i++) {

                if (i == index - 1) {

                    newNode.next = current.next;
                    current.next = newNode;
                    newNode.data = (int) data;
                }
                if(current!=null) {
                    current = current.next;
                }
            }
         }else{
            current = head;
            newNode.next = current;
            newNode.data = (int) data;
            head = newNode;
        }



    }

    @Override
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
    public Object get(int index) {
        Node current = head;
        try{
        for(int i = 0; i <= index; i++) {
            if(i==index){
                return current.data;
            }
            current = current.next;
        }
        } catch (Exception e) {
            return "Error";
        }

        return "Error";
    }

    @Override
    public void set(int index, Object element) {
        Node current = head;
        for(int i = 0; i <= index; i++) {
            if(i==index){
               current.data = (int) element;
            }
            current = current.next;
        }

    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public boolean isEmpty() {
        if(head == null && tail == null)
            return true;
        else
            return false;
    }

    @Override
    public void remove(int index) {
        Node After = head;
        Node current = head;
        if (head == null && index!=0) {
            System.out.println("Error");
        }
            if (index != 0) {
                for (int i = 0; i <= index; i++) {
                    After = After.next;
                    if (i == index - 1) {

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

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        Node current = head;
        LinkedListCreation L2 = new LinkedListCreation();
        if (head == null || fromIndex>toIndex) {
            System.out.println("Error");
        }else {
            for (int i = 0; i <= toIndex; i++) {
                if (i >= fromIndex) {
                    L2.add(current.data);
                }
                current = current.next;
            }
            L2.displayNodes();
        }

        return L2;
    }

    @Override
    public boolean contains(Object o) {
        Node current = head;
        Boolean flag = false;
        if (head == null) {

            return false;
        }
        while (current != null) {

            if(current.data == (int) o) {
                flag = true;
            }
            current = current.next;
        }
        return flag;
    }

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




        public static void main(String[] args) {
            //Methods


            LinkedListCreation L1 = new LinkedListCreation();

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
                case "add":
                    int NumAdd = sc.nextInt();
                    L1.add(NumAdd);
                    L1.displayNodes();
                    break;
                case "addToIndex":
                    int InsertionIndex = sc.nextInt();
                    int InsertionValue = sc.nextInt();
                    if(InsertionIndex<0){
                        System.out.println("Error");
                    }else {
                        try {
                            L1.addToIndex(InsertionIndex, InsertionValue);
                        } catch (Exception e) {
                            System.out.println("Error");
                            ErrFlag = true;
                        }
                        if (!ErrFlag)
                            L1.displayNodes();
                    }
                    break;
                case "get":
                    int GetIndex = sc.nextInt();
                    if(GetIndex<0){
                        System.out.println("Error");
                    }else {
                        Object IndexElement = L1.get(GetIndex);
                        System.out.println(IndexElement);
                    }
                    break;
                case "set":
                    int SetIndex = sc.nextInt();
                    int SetValue = sc.nextInt();
                    if(SetIndex<0){
                        System.out.println("Error");
                    }else {
                        try {
                            L1.set(SetIndex, SetValue);
                        } catch (Exception e) {
                            System.out.println("Error");
                            ErrFlag = true;
                        }
                        if (!ErrFlag)
                            L1.displayNodes();
                    }
                    break;
                case "clear":
                    L1.clear();
                    L1.displayNodes();
                    break;
                case "isEmpty":
                    Boolean isEmpty = L1.isEmpty();
                    if (isEmpty)
                        System.out.println("True");
                    else
                        System.out.println("False");
                    break;
                case "remove":

                    int RemoveIndex = sc.nextInt();
                    if(RemoveIndex<0){
                        System.out.println("Error");
                    }else {
                        try {
                            L1.remove(RemoveIndex);
                        } catch (Exception e) {
                            System.out.println("Error");
                            ErrFlag = true;
                        }
                        if (!ErrFlag)
                            L1.displayNodes();
                    }
                    break;
                case "sublist":
                    int StartIndex = sc.nextInt();
                    int EndIndex = sc.nextInt();
                    if(StartIndex < 0 || EndIndex < 0){
                        System.out.println("Error");
                    }else {
                        try {
                            L1.sublist(StartIndex, EndIndex);
                        } catch (Exception e) {
                            System.out.println("Error");
                        }
                    }
                    break;
                case "contains":
                    int exist = sc.nextInt();
                    if(exist < 0){
                        System.out.println("Error");
                    }else {
                        Boolean exists = L1.contains(exist);
                        if (exists)
                            System.out.println("True");
                        else
                            System.out.println("False");
                    }
                    break;
                case "size":
                    int size = L1.size();
                    System.out.println(size);
                    break;



            }
        }
}