import java.util.*;

public class Solution {
    public int[] MoveValue(int[] array, int number) {
        int startOfArrayIdx=0;
        int endOfArrayIdx = array.length -1;
        int[] result = new int[array.length];

        for (int i = 0; i < array.length  ; i++) {
            if (array[i] != number) {
                result[startOfArrayIdx] = array[i];
                startOfArrayIdx++;
            } else {
                result[endOfArrayIdx] = number;
                endOfArrayIdx--;
            }

        }


        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        int num = sc.nextInt();
        String[] s = sin.split(", ");

        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for(int i = 0; i < s.length; ++i)
                arr[i] = Integer.parseInt(s[i]);
        }
        int [] res = new Solution().MoveValue(arr,num);
        System.out.print("[");
        for(int m = 0; m < res.length; ++m) {
            System.out.print(res[m]);
            if(m != res.length - 1)
                System.out.print(", ");
        }
        System.out.print("]");



    }
}