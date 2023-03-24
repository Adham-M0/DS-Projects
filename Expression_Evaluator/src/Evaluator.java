import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IExpressionEvaluator {

    /**
     * Takes a symbolic/numeric infix expression as input and converts it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     *
     * @param expression infix expression
     * @return postfix expression
     */

    public String infixToPostfix(String expression);


    /**
     * Evaluate a postfix numeric expression, with a single space separator
     * @param expression postfix expression
     * @return the expression evaluated value
     */

    public int evaluate(String expression);

}

class Stack {
    class Node {
        Object data;
        Node next;

        // constructor to create new node
        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    // Initially both head and tail are not
    // pointing to any other node
    Node head = null;
    Node tail = null;


    // display linked list
    void displayNodes() {

        Node current = head;
        if (head == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        while (current != null) {

            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.print("]");
        System.out.println();
    }

    public void add(Object data) {

        Node newNode = new Node(data);

        // Checks if the list is empty
        if (head == null) {
            // If list is empty, both head and
            // tail will point to new node
            head = newNode;
            tail = newNode;
        } else {

            tail.next = newNode;
            // storing newnode in tail
            tail = newNode;
        }

    }


    public Object pop() {
        Node current = head;
        try {
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


    public Object peek() {
        Node current = head;
        try {
            Object x = current.data;
            if (head == null) {
                System.out.println("Error");
            }
            return x;
        } catch (Exception e) {
            return "Error";
        }
    }


    public void push(Object element) {
        int index = 0;
        Node current = head;
        Node newNode = new Node(element);
        current = head;
        newNode.next = current;
        newNode.data = element;
        head = newNode;
    }


    public boolean isEmpty() {
        if (head == null && tail == null)
            return true;
        else
            return false;
    }


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
}

public class Evaluator implements IExpressionEvaluator {

    @Override
    public int evaluate(String expression) throws RuntimeException {
        Stack SValue=new Stack();
        String[] in =expression.split(" ");;
        int x=0;

        // Scan all characters one by one

        for(int i=0;i<in.length;i++) {
            String c=in[i];
            if(c.trim().isEmpty())
                continue;
            else if(c.equals("/")){
                int n2=(int)SValue.pop();
                int n1=(int)SValue.pop();
                int n3=0;
                n3=n1/n2;
                SValue.push(n3);
            } else if(c.equals("+")){
                int n2=(int)SValue.pop();
                int n1=(int)SValue.pop();
                int n3=n1+n2;
                SValue.push(n3);
            } else if(c.equals("*")){
                int n2= (int) SValue.pop();
                int n1=(int)SValue.pop();
                int n3=n1*n2;
                SValue.push(n3);
            } else if(c.equals("-")){
                if(SValue.size()==1){
                    int n1=(int)SValue.pop();
                    int n3=-1*n1;
                    SValue.push(n3);
                } else{
                    int n2=(int)SValue.pop();
                    int n1=(int)SValue.pop();
                    int n3=n1-n2;
                    SValue.push(n3);}
            } else if(c.equals("^")){
                int n2=(int)SValue.pop();
                int n1=(int)SValue.pop();
                if(n2<0){
                    SValue.push(0);
                } else{
                    int n3=1;
                    n3=(int)Math.pow(n1,n2);
                    SValue.push(n3);
                }
            } else{
                int o=Integer.parseInt(c);
                SValue.push(o);
            }
        }
        x=(int)SValue.pop();
        return x;
    }
    @Override
    public String infixToPostfix(String expression) throws RuntimeException {
        Stack S = new Stack();
        String postfix = "";
        char hld = ' ';
        boolean OperandReq = false;
        boolean OpenBracket = false;
        if (expression.charAt(0) == '-' && expression.charAt(1) == '-')
            expression = expression.substring(2);
        if (expression.charAt(expression.length() - 1) == '/' || expression.charAt(expression.length() - 1) == '-' || expression.charAt(expression.length() - 1) == '+' || expression.charAt(expression.length() - 1) == '*' || expression.charAt(expression.length() - 1) == '^')
            throw new RuntimeException();
        expression = expression.replace("/--", "/");
        expression = expression.replace("*--", "*");
        expression = expression.replace("^--", "^");
        expression = expression.replace("--", "+");
        expression = expression.replace(" ", "");
        expression = expression.replace("\t", "");
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == 'a' || expression.charAt(i) == 'b' || expression.charAt(i) == 'c') {
                if (OperandReq == true) 
                    throw new RuntimeException();
                hld = expression.charAt(i);
                postfix = postfix.concat(Character.toString(hld));
                OperandReq = true;
            }
            else if (expression.charAt(i) == '0' || expression.charAt(i) == '1' || expression.charAt(i) == '2'|| expression.charAt(i) == '3'|| expression.charAt(i) == '4'|| expression.charAt(i) == '5'|| expression.charAt(i) == '6'|| expression.charAt(i) == '7'|| expression.charAt(i) == '8'|| expression.charAt(i) == '9') {
                if (OperandReq == true) 
                    throw new RuntimeException();
                int counter = i;
                while (expression.charAt(i) == '0' || expression.charAt(i) == '1' || expression.charAt(i) == '2'|| expression.charAt(i) == '3'|| expression.charAt(i) == '4'|| expression.charAt(i) == '5'|| expression.charAt(i) == '6'|| expression.charAt(i) == '7'|| expression.charAt(i) == '8'|| expression.charAt(i) == '9') {
                    hld = expression.charAt(counter);
                    postfix = postfix.concat(Character.toString(hld));
                    counter++;
                    if (counter == (expression.length())) 
                        break;
                }
                counter--;
                i = counter;
                OperandReq = true;
                postfix = postfix.concat(" ");
            }
            else if ( expression.charAt(i) == '-' || expression.charAt(i) == '+' || expression.charAt(i) == '*'|| expression.charAt(i) == '/' || expression.charAt(i) == '^'  || expression.charAt(i) == '(' || expression.charAt(i) == ')') {
                if (S.isEmpty() || (char) S.peek() == '(' || expression.charAt(i) == '(') {
                    if ( expression.charAt(i) == '(')
                        OpenBracket = true;
                    S.push(expression.charAt(i));
                } else if (expression.charAt(i) == ')') {
                    if(OpenBracket == true){
                    while (!S.isEmpty() && (char) S.peek() != '(') {
                        hld = (char) S.pop();
                        postfix = postfix.concat(Character.toString(hld));
                    }
                    S.pop();
                    }else{
                        throw new RuntimeException();
                    }
                }  else {
                    if (prec(expression.charAt(i)) > prec((char) S.peek())) {
                        S.push(expression.charAt(i));
                    } else {
                        while (!S.isEmpty() && prec(expression.charAt(i)) <= prec((char) S.peek())) {
                            if ((char) S.peek() == '(') {
                                break;
                            } else {
                                hld = (char) S.pop();
                                postfix = postfix.concat(Character.toString(hld));
                            }
                        }
                        S.push(expression.charAt(i));
                    }

                }
                OperandReq = false;
            } else
                throw new RuntimeException();
        }
        while (!S.isEmpty()) {
            if ((char) S.peek() == '(')
                throw new RuntimeException();
            hld = (char) S.pop();
            postfix = postfix.concat(Character.toString(hld));
        }
        if (postfix == "")
            throw new RuntimeException();
        postfix.replaceAll(" ", "");
        return postfix;
    }
    static int prec(char x)
    {
        if (x == '+' || x == '-')
            return 1;
        if (x == '*' || x == '/' || x == '%')
            return 2;
        if (x == '^')
            return 3;
        return -1;
    }




    public static void main(String[] args) {
        try {
            Evaluator x = new Evaluator();
            /* Enter your code here. Read input from STDIN. Print postfix to STDOUT. */
            Scanner sc = new Scanner(System.in);
            //infixToPostfix
            String infix = sc.nextLine();
            String postfix = x.infixToPostfix(infix);
            //Convert a,b,c to their values in postfix expression
            int n = postfix.length();
            String SubstitutePostfix, y1, y2, k;
            String sv = new String("");
            for (int i = 0; i < n; i++) {
                sv += Character.toString(postfix.charAt(i));
                if (i < n - 1)
                    sv += " ";
            }
            //evaluate
            String a = sc.nextLine();
            k = a.substring(2);
            y1 = sv.replaceAll("a", k);

            String b = sc.nextLine();
            k = b.substring(2);
            y2 = y1.replaceAll("b", k);

            String c = sc.nextLine();
            k = c.substring(2);
            SubstitutePostfix = y2.replaceAll("c", k);

            int evaluation = x.evaluate(SubstitutePostfix);
            System.out.println(postfix);
            System.out.println(evaluation);
        }
        catch (RuntimeException e) {
            System.out.print("Error");
        }
    }
}