import java.io.*;
import java.util.*;



import java.util.Scanner;


public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IPolynomialSolver obj = new PolynomialSolver();

        String command = "";
        try{
            while(true)
            {
                try {
                    command = sc.nextLine().replaceAll(" ", "");
                }
                catch(Exception e)
                {
                    break;
                }

                if(command.equals("set"))
                {
                    command = sc.nextLine();
                    String sin = sc.nextLine().replaceAll("\\[|\\]", "");
                    String[] s = sin.split(",");
                    int[][] arr;
                    //check form empty input
                    if (s.length == 1 && s[0].isEmpty()) {
                        arr = new int[0][0];
                        arr[0][0] = 0;
                    }
                    else {
                        arr = new int[s.length][2];
                        for(int i = 0; i < s.length; i++)
                        {
                            arr[i][1] = Integer.parseInt(s[i]);
                        }
                        int k = 0;
                        for(int i = s.length-1; i >= 0;i--)
                        {
                            arr[k][0] = i;
                            k++;
                        }
                    }
                    obj.setPolynomial(command.charAt(0), arr);
                }
                else if(command.equals("print"))
                {
                    String sin = sc.nextLine();
                    System.out.println(obj.print(sin.charAt(0)));
                }
                else if(command.equals("eval"))
                {
                    String sin = sc.nextLine();
                    float num = sc.nextFloat();
                    System.out.println((int)obj.evaluatePolynomial(sin.charAt(0), num));
                }
                else if(command.equals("add"))
                {
                    String s1 = sc.nextLine();
                    String s2 = sc.nextLine();
                    obj.setPolynomial('R', obj.add(s1.charAt(0), s2.charAt(0)));
                    System.out.println(obj.print('R'));
                }
                else if(command.equals("sub"))
                {
                    String s1 = sc.nextLine();
                    String s2 = sc.nextLine();
                    obj.setPolynomial('R', obj.subtract(s1.charAt(0), s2.charAt(0)));
                    System.out.println(obj.print('R'));
                }
                else if(command.equals("mult"))
                {
                    String s1 = sc.nextLine();
                    String s2 = sc.nextLine();
                    obj.setPolynomial('R', obj.multiply(s1.charAt(0), s2.charAt(0)));
                    System.out.println(obj.print('R'));
                }
                else if(command.equals("clear"))
                {
                    String sin = sc.nextLine();
                    obj.clearPolynomial(sin.charAt(0));
                }
                else if(command.equals("")){
                    continue;
                }
                else
                {
                    throw new RuntimeException();
                }
            }
            sc.close();
            return;
        }
        catch (RuntimeException e)
        {
            sc.close();
            System.out.println("Error");
            return;
        }


    }
}
interface IPolynomialSolver {
    /**
     * Set polynomial terms (coefficients & exponents)
     * @param poly: name of the polynomial
     * @param terms: array of [coefficients][exponents]
     */
    void setPolynomial(char poly, int[][] terms);
    /**
     * Print the polynomial in ordered human readable representation
     * @param poly: name of the polynomial
     * @return: polynomial in the form like 27x^2+x-1
     */
    String print(char poly);
    /**
     * Clear the polynomial
     * @param poly: name of the polynomial
     */
    void clearPolynomial(char poly);
    /**
     * Evaluate the polynomial
     * @param poly: name of the polynomial
     * @param value: the polynomial constant value
     * @return the value of the polynomial
     */
    float evaluatePolynomial(char poly, float value);
    /**
     * Add two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial
     */
    int[][] add(char poly1, char poly2);
    /**
     * Subtract two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial
     */
    int[][] subtract(char poly1, char poly2);
    /**
     * Multiply two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return: the result polynomial
     */
    int[][] multiply(char poly1, char poly2);
}
class PolynomialSolver implements IPolynomialSolver{
    ILinkedList A = new DoubleLinkedList();
    ILinkedList B = new DoubleLinkedList();
    ILinkedList C = new DoubleLinkedList();
    ILinkedList R = new DoubleLinkedList();

    class PolyNode{
        private int exp;
        private int coef;
        public boolean getCoef;
        public PolyNode(int exp, int coef){
            this.exp = exp;
            this.coef = coef;
        }
        public int getExp(){
            return exp;
        }
        public int getCoef(){
            return coef;
        }
    }

    public void setPolynomial(char poly, int[][] terms) {
        switch(poly){
            case 'A':
                setPoly(this.A, terms);
                return;
            case 'B':
                setPoly(this.B, terms);
                return;
            case 'C':
                setPoly(this.C, terms);
                return;
            case 'R':
                setPoly(this.R, terms);
                return;
            default:
                throw new RuntimeException();
        }
    }

    private void setPoly(ILinkedList poly, int[][] terms) {
        poly.clear();
        int termsNumber = terms.length;
        for(int i = 0; i < termsNumber; i++){
            PolyNode term = new PolyNode(terms[i][0], terms[i][1]);
            poly.add(term);
        }

    }

    @Override
    public String print(char poly) {
        switch(poly){
            case 'A':
                return printPoly(this.A);
            case 'B':
                return printPoly(this.B);
            case 'C':
                return printPoly(this.C);
            case 'R':
                return printPoly(this.R);
            default:
                throw new RuntimeException();
        }
    }

    private String printPoly(ILinkedList poly){
        if(poly.isEmpty())
            throw new RuntimeException();

        int sizePoly = poly.size();
        String polynomial = "";
        if (sizePoly == 1) {
            return polynomial + ((PolyNode) poly.get(0)).getCoef();
        }
        for(int i = 0; i < sizePoly; i++){
            if(((PolyNode) poly.get(i)).getExp() > 0){
                if(((PolyNode) poly.get(i)).getCoef() == 0){
                    continue;
                }

                if(((PolyNode) poly.get(i)).getCoef() != 1)
                {
                    polynomial = polynomial + ((PolyNode) poly.get(i)).getCoef();
                }
                polynomial = polynomial + "x";
                if(((PolyNode) poly.get(i)).getExp() > 1){
                    polynomial = polynomial + "^" + ((PolyNode) poly.get(i)).getExp();
                }
                if(((PolyNode) poly.get(i+1)).getCoef() > 0 && ((PolyNode) poly.get(i+1)).getExp() != 0){
                    polynomial = polynomial + "+";
                }
            }
            else if(((PolyNode) poly.get(i)).getExp() == 0){
                if(((PolyNode) poly.get(i)).getCoef() == 0){
                    break;
                }
                else if(((PolyNode) poly.get(i)).getCoef() > 0){
                    polynomial = polynomial + "+" +((PolyNode) poly.get(i)).getCoef();
                }
                else {
                    polynomial = polynomial + ((PolyNode) poly.get(i)).getCoef();
                }
            }
        }
        if(polynomial.length() == 0){
            polynomial = polynomial + ((PolyNode) poly.get(sizePoly-1)).getCoef();
        }
        return polynomial;
    }

    @Override
    public void clearPolynomial(char poly) {
        if(poly == 'A')
        {
            A.clear();
            System.out.println("[]");
        }
        else if(poly == 'B')
        {
            B.clear();
            System.out.println("[]");
        }
        else if(poly == 'C')
        {
            C.clear();
            System.out.println("[]");
        }
        else
        {
            throw new RuntimeException();
        }
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        if(poly == 'A')
        {
            if(A.isEmpty())
            {
                throw new RuntimeException();
            }
            float res = (int) ((PolynomialSolver.PolyNode) A.get(0)).getCoef();
            for(int i = 1; i < A.size(); i++)
            {
                res = (res*value) + (int)((PolynomialSolver.PolyNode) A.get(i)).getCoef();
            }
            return res;
        }
        else if(poly == 'B')
        {
            if(B.isEmpty())
            {
                throw new RuntimeException();
            }
            float res = (int) ((PolynomialSolver.PolyNode) B.get(0)).getCoef();
            for(int i = 1; i < B.size(); i++)
            {
                res = (res*value) + (int)((PolynomialSolver.PolyNode) B.get(i)).getCoef();
            }
            return res;
        }
        else if(poly == 'C')
        {
            if(C.isEmpty())
            {
                throw new RuntimeException();
            }
            float res = (int) ((PolynomialSolver.PolyNode) C.get(0)).getCoef();
            for(int i = 1; i < C.size(); i++)
            {
                res = (res*value) + (int)(((PolynomialSolver.PolyNode) C.get(i)).getCoef());
            }
            return res;
        }
        else
        {
            throw new RuntimeException();
        }
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        if(poly1 =='A' && poly2 == 'B'){
            return addPoly(this.A, this.B);
        }
        else if(poly1 =='B' && poly2 == 'A'){
            return addPoly(this.B, this.A);
        }
        else if(poly1 =='B' && poly2 == 'C'){
            return addPoly(this.B, this.C);
        }
        else if(poly1 =='C' && poly2 == 'B'){
            return addPoly(this.C, this.B);
        }
        else if(poly1 =='A' && poly2 == 'C'){
            return addPoly(this.A, this.C);
        }
        else if(poly1 =='C' && poly2 == 'A'){
            return addPoly(this.C, this.A);
        }
        else if((poly1 =='A' && poly2 == 'A')){
            return addPoly(this.A, this.A);
        }
        else if((poly1 =='B' && poly2 == 'B')){
            return addPoly(this.B, this.B);
        }
        else if(poly1 =='C' && poly2 == 'C'){
            return addPoly(this.C, this.C);
        }
        else{
            throw new RuntimeException();
        }
    }
    private int[][] addPoly(ILinkedList poly1, ILinkedList poly2){
        if(poly1.isEmpty() || poly2.isEmpty())
            throw new RuntimeException();
        int greatestExp1 = ((PolyNode) poly1.get(0)).getExp();
        int greatestExp2 = ((PolyNode) poly2.get(0)).getExp();
        if(greatestExp1 >= greatestExp2) {
            int[][] result = new int[greatestExp1+1][2];
            int index = greatestExp1 - greatestExp2;
            for(int i = 0; i < index; i++){
                result[i][0] = ((PolyNode) poly1.get(i)).getExp();
                result[i][1] = ((PolyNode) poly1.get(i)).getCoef();
            }
            for(int i = 0; i < greatestExp2+1; i++){
                int j =i+index;
                result[j][0] = ((PolyNode) poly2.get(i)).getExp();
                result[j][1] = ((PolyNode) poly1.get(j)).getCoef()+((PolyNode) poly2.get(i)).getCoef();
            }
            return result;
        }
        else {
            int[][] result = new int[greatestExp2+1][2];
            int index = greatestExp2 - greatestExp1;
            for(int i = 0; i < index; i++){
                result[i][0] = ((PolyNode) poly2.get(i)).getExp();
                result[i][1] = ((PolyNode) poly2.get(i)).getCoef();
            }
            for(int i = 0; i < greatestExp1+1; i++){
                int j =i+index;
                result[j][0] = ((PolyNode) poly1.get(i)).getExp();
                result[j][1] = ((PolyNode) poly1.get(i)).getCoef()+((PolyNode) poly2.get(j)).getCoef();
            }
            return result;
        }
    }

    @Override
    public int[][] subtract(char poly1, char poly2){
        if(poly1 =='A' && poly2 == 'B'){
            return subtractPoly(this.A, this.B);
        }
        else if(poly1 =='B' && poly2 == 'A'){
            return subtractPoly(this.B, this.A);
        }
        else if(poly1 =='B' && poly2 == 'C'){
            return subtractPoly(this.B, this.C);
        }
        else if(poly1 =='C' && poly2 == 'B'){
            return subtractPoly(this.C, this.B);
        }
        else if(poly1 =='A' && poly2 == 'C'){
            return subtractPoly(this.A, this.C);
        }
        else if(poly1 =='C' && poly2 == 'A'){
            return subtractPoly(this.C, this.A);
        }
        else if((poly1 =='A' && poly2 == 'A')){
            return subtractPoly(this.A, this.A);
        }
        else if((poly1 =='B' && poly2 == 'B')){
            return subtractPoly(this.B, this.B);
        }
        else if(poly1 =='C' && poly2 == 'C'){
            return subtractPoly(this.C, this.C);
        }
        else{
            throw new RuntimeException();
        }
    }
    private int[][] subtractPoly(ILinkedList poly1, ILinkedList poly2){
        if(poly1.isEmpty() || poly2.isEmpty())
            throw new RuntimeException();
        int greatestExp1 = ((PolyNode) poly1.get(0)).getExp();
        int greatestExp2 = ((PolyNode) poly2.get(0)).getExp();
        if(greatestExp1 >= greatestExp2) {
            int[][] result = new int[greatestExp1+1][2];
            int index = greatestExp1 - greatestExp2;
            for(int i = 0; i < index; i++){
                result[i][0] = ((PolyNode) poly1.get(i)).getExp();
                result[i][1] = ((PolyNode) poly1.get(i)).getCoef();
            }
            for(int i = 0; i < greatestExp2+1; i++){
                int j =i+index;
                result[j][0] = ((PolyNode) poly2.get(i)).getExp();
                result[j][1] = ((PolyNode) poly1.get(j)).getCoef()-((PolyNode) poly2.get(i)).getCoef();
            }
            return result;
        }
        else {
            int[][] result = new int[greatestExp2+1][2];
            int index = greatestExp2 - greatestExp1;
            for(int i = 0; i < index; i++){
                result[i][0] = ((PolyNode) poly2.get(i)).getExp();
                result[i][1] = -((PolyNode) poly2.get(i)).getCoef();
            }
            for(int i = 0; i < greatestExp1+1; i++){
                int j =i+index;
                result[j][0] = ((PolyNode) poly1.get(i)).getExp();
                result[j][1] = ((PolyNode) poly1.get(i)).getCoef()-((PolyNode) poly2.get(j)).getCoef();
            }
            return result;
        }
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        if((poly1 =='A' && poly2 == 'B')||(poly1 =='B' && poly2 == 'A')){
            return multiplyPoly(this.A, this.B);
        }
        else if((poly1 =='B' && poly2 == 'C') || (poly1 =='C' && poly2 == 'B') ){
            return multiplyPoly(this.B, this.C);
        }

        else if((poly1 =='A' && poly2 == 'C') || (poly1 =='C' && poly2 == 'A')) {
            return multiplyPoly(this.A, this.C);
        }

        else if((poly1 =='A' && poly2 == 'A')){
            return multiplyPoly(this.A, this.A);
        }
        else if((poly1 =='B' && poly2 == 'B')){
            return subtractPoly(this.B, this.B);
        }
        else if(poly1 =='C' && poly2 == 'C'){
            return multiplyPoly(this.C, this.C);
        }
        else{
            throw new RuntimeException();
        }
    }
    private int[][] multiplyPoly(ILinkedList poly1, ILinkedList poly2){
        if(poly1.isEmpty() || poly2.isEmpty())
            throw new RuntimeException();
        int greatestExp1 =  ((PolyNode) poly1.get(0)).getExp();
        int greatestExp2 = ((PolyNode) poly2.get(0)).getExp();
        int greatestExpMult= greatestExp1 + greatestExp2;
        int [][] mult = new int[greatestExpMult+1][2];
        for(int i = 0; i < greatestExp1 + 1; i++){
            for(int j = 0; j < greatestExp2 + 1; j++){
                int multExp = ((PolyNode) poly1.get(i)).getExp() + ((PolyNode) poly2.get(j)).getExp();
                mult[greatestExpMult - multExp][0] = multExp;
                mult[greatestExpMult - multExp][1] += ((PolyNode) poly1.get(i)).getCoef() * ((PolyNode) poly2.get(j)).getCoef();
            }
        }
        return mult;
    }

}

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
class DoubleLinkedList implements ILinkedList {
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