import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public int fib(int n) {
        // Implement your method here.
        if(n==1){
            return 0;
        } else if (n==2) {
            return 1;
        } else {
            return fib(n-1)+fib(n-2);
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int res = new Solution().fib(num);
        System.out.print(res);
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}