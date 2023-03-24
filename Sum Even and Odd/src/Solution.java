import java.util.*;

public class Solution {
    public int[] sum(int[] a) {
        int i;
        int OddSum=0;
        int EvenSum=0;
        int n = a.length;
        for (i = 0; i < n ; i++) {
            if(a[i]%2 != 0){
                //odd
                OddSum+=a[i];


            }else{
                //even
                EvenSum+=a[i];

            }

        }
        int[] SumArray = new int[]{ EvenSum ,OddSum};
        return SumArray;
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
        int [] res = new Solution().sum(arr);
        System.out.print("[");
        for(int m = 0; m < res.length; ++m) {
            System.out.print(res[m]);
            if(m != res.length - 1)
                System.out.print(", ");
        }
        System.out.print("]");
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}