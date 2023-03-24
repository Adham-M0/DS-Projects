import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

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

public class ArrayQueue implements IQueue {
    int front, rear =0;
    int N=50;
    int count =0;
    int queue[]= new int[N];


    @Override
    public void enqueue(Object item) {
        try{
            if (count == N) System.out.println("Error");
            else {
                if(!isEmpty())
                {
                    for (int i=size(); i>0;i--){
                        queue[i]=queue[i-1];
                    }

                }
                queue[0]= (int) item;
                count++;
                if (rear== N-1) rear=0;
                else rear++;

            }
        } catch (Exception e) {System.out.println("Error");}


    }

    @Override
    public Object dequeue() {
        try {
            int temp;
            if (isEmpty()) System.out.println("Error");
            else {
                //System.out.println("rear= "+rear +" front= "+ front);

                temp= queue[front];
                count--;
                if (front== N-1) front=0;
                else front++;
                return temp;

            }


        }catch (Exception e) {System.out.println("Error");}
        return null;
    }

    @Override
    public boolean isEmpty() {
        if (count==0) return true;
        else
            return false;
    }

    @Override
    public int size() {
        return count;
    }

    public void printQueue(){
        if (isEmpty()) System.out.println("[]");
        else {
            System.out.printf("[");
            for (int i=0 ; i< size() ; i++){
                System.out.printf(String.valueOf(queue[i]));
                if (i<size()-1) System.out.printf(", ");
            }
            System.out.println("]");


        }
    }

    public static void main(String[] args) {
        try{

            ArrayQueue queue1 = new ArrayQueue();
            Scanner sc = new Scanner(System.in);
            String sin = sc.nextLine().replaceAll("\\[|\\]", "");
            String[] s = sin.split(", ");
            int[] arr = new int[s.length];

            if (s.length == 1 && s[0].isEmpty())
                arr = new int[]{};
            else {
                for (int i = 0; i < s.length; ++i)
                    arr[s.length-i-1] = Integer.parseInt(s[i]);
            }

            for (int i = 0; i < arr.length; i++) {
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
                        item = (int) queue1.dequeue();
                    }catch (Exception e){ break;}

                    queue1.printQueue();
                    break;
                case "isEmpty":
                    if (queue1.isEmpty()) System.out.println("True");
                    else System.out.println("False");
                    break;
                case "size":
                    System.out.println(queue1.size());
                    break;
            }



        } catch (Exception e) {
            System.out.println("Error");
        }

    }
}