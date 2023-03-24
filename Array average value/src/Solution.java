import java.util.*;

public class Solution {
    public double average(int[] a) {
        int i;
        double t=0;
        int n = a.length;
        for (i = 0; i < n ; i++) {
            t += a[i];

        }
        return t/a.length;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{0};
        else {
            for(int i = 0; i < s.length; ++i)
                arr[i] = Integer.parseInt(s[i]);
        }
        double res = new Solution().average(arr);
        System.out.print(res);
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}