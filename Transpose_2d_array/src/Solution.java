import java.util.*;
import java.lang.Math;

public class Solution {
    public int[][] Transpose(int[][] array, int f) {

        int[][] result = new int[(int) Math.sqrt(f)][(int) Math.sqrt(f)];
        for(int i = 0; i < (int) Math.sqrt(f); ++i) {
            for(int j = 0; j < (int) Math.sqrt(f); ++j){
                result[i][j]=array[j][i];
            }
        }

        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        // int num = sc.nextInt();
        String[] s = sin.split(", ");
        int k=0;
        int[][] arr = new int[(int) Math.sqrt(s.length)][(int) Math.sqrt(s.length)];
        if (s.length == 1 && s[0].isEmpty())
            System.out.print("[[]]");
        else {
            for(int i = 0; i < (int) Math.sqrt(s.length); ++i) {
                for(int j = 0; j < (int) Math.sqrt(s.length); ++j){
                    arr[i][j] = Integer.parseInt(s[k]);
                    k++;
                }
            }
        }
        int z = s.length;
        int [][] res = new Solution().Transpose(arr,z);
        if (s.length == 1 && s[0].isEmpty()){

        }else {
            System.out.println(Arrays.deepToString(res));
        }


    }
}