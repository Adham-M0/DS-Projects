import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.List;
import java.util.regex.*;
import java.util.Arrays;
 interface IPlayersFinder {

    /**
     * Search for players locations at the given photo
     * @param photo
     *     Two dimension array of photo contents
     *     Will contain between 1 and 50 elements, inclusive
     * @param team
     *     Identifier of the team
     * @param threshold
     *     Minimum area for an element
     *     Will be between 1 and 10000, inclusive
     * @return
     *     Array of players locations of the given team
     */
    java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}
public class PlayersFinder implements IPlayersFinder {
    int r = 0;
    int l = 0;
    int u = 0;
    int d = 0;
    public static void main(String[] args) {
        /* Implement main method to parse the input from stdin and print output to stdout */
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String[] sfinal = s.split(", ");
        int sizei = Integer.parseInt(sfinal[0]);
        int sizej = Integer.parseInt(sfinal[1]);
        String [] string = new String[sizei];
        if(sizei == 0 || sizej == 0)
        {
            System.out.print("[]");
        }
        for(int i = 0; i < sizei; i++)
        {
            string[i] = input.nextLine();
            if(string[i] == null)
            {
                System.out.print("[]");
            }
        }
        int team = input.nextInt();
        int threshold = input.nextInt();
        input.close();
        Point [] points = new PlayersFinder().findPlayers(string, team, threshold);

       Point temp = new Point();
        int [] ResX = new int[points.length];
        int [] ResY = new int[points.length];
        for (int i = 0; i < points.length; i++)
        {
            ResX[i] = (int) points[i].getX();
            ResY[i] = (int) points[i].getY();
        }
        //Sorting
        PlayersFinder ob = new PlayersFinder();
        ob.selectionSort(ResX, ResY);

        for (int i = 0; i < points.length-1; i++)
        {
           if(ResX[i]==ResX[i+1])
           {
               if(ResY[i]>ResY[i+1])     //Sorting Y
               {int temppp;
                   temppp = ResY[i];
                   ResY[i] = ResY[i+1];
                   ResY[i+1] = temppp;
                   temppp = ResX[i];
                   ResX[i] = ResX[i+1];
                   ResX[i+1] = temppp;
               }
           }
        }
        System.out.print("[");
        for(int i = 0; i < points.length; i++)
        {
            System.out.print("(" + ResX[i] + ", " + ResY[i] + ")");
            if(i != points.length - 1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
//SortingX
    private void selectionSort(int[] ResX, int[] ResY) {
        int pos;
        int temp;
        for (int i = 0; i < ResX.length; i++)
        {
            pos = i;
            for (int j = i+1; j < ResX.length; j++)
            {
                    if (ResX[j] < ResX[pos])
                    {
                        pos = j;
                    }
            }
                temp = ResY[pos];
                ResY[pos] = ResY[i];
                ResY[i] = temp;
                temp = ResX[pos];
                ResX[pos] = ResX[i];
                ResX[i] = temp;
        }
    }

    @Override
    public java.awt.Point[] findPlayers(String[] photo, int team, int threshold)
    {
        int sizei = photo.length; int sizej = photo[0].length();
        char [][] arr = new char[sizei][sizej];
        //Creating 2D array
        for(int i = 0; i < sizei; ++i)
        {
            for(int j = 0; j < sizej; ++j)
            {
                arr[i][j] = photo[i].charAt(j);
            }
        }
        //Flags
        Boolean[][] IsVisited = new Boolean[sizei][sizej];
        Boolean[][] IsVisited_Center = new Boolean[sizei][sizej];
        for(int i = 0; i < sizei; i++)
        {
            for(int j = 0; j < sizej; j++)
            {
                IsVisited[i][j] = false;
                IsVisited_Center[i][j] = false;
            }
        }
        //AllMethods
        class methods
        {
            int CheckArea(char[][] arr, int x, int y, char team) {
                int sum=0;
                //Out Of Boundaries Try&Catch
                try {
                    if (IsVisited[x][y]) {
                        return 0;
                    } else if (arr[x][y] == team) {
                        IsVisited[x][y] = true;
                        sum = 4;
                        sum += CheckArea(arr, x + 1, y, team);
                        sum += CheckArea(arr, x - 1, y, team);
                        sum += CheckArea(arr, x, y + 1, team);
                        sum += CheckArea(arr, x, y - 1, team);
                    }
                    return sum;
                }catch(Exception n)
                {
                    return 0;
                }
            }
            //Center Calculation
            int [] CalcCenter(int x, int y) {
                r = (y+1)*2;l = y*2; d = (x+1)*2; u = x*2;
                int c[] = new int[2];
                Check_r(x, y);
                Check_d(x, y);
                Check_u(x, y);
                c[0] = (r + l)/2;
                c[1] = (u + d)/2;
                return c;
            }
            //Checking right
            void Check_r(int x, int y)
            {
                if(!VisitedMethod(x, y))
                {
                    return;
                }
               IsVisited_Center[x][y] = true;
                if(VisitedMethod(x, y+1) && !IsVisited_Center[x][y+1])
                {
                    if((y+2)*2 > r)
                    {
                        r = ((y+2)*2);
                    }
                    Check_r(x, y+1);
                    Check_d(x, y+1);
                    Check_u(x, y+1);
                }
            }
            //Checking left
            void Check_l(int x, int y)
            {
                if(!VisitedMethod(x, y))
                {
                    return;
                }
               IsVisited_Center[x][y] = true;
                if(VisitedMethod(x, y-1) && !IsVisited_Center[x][y-1])
                {
                    if((y-1)*2 < l)
                    {
                        l = (y-1)*2;
                    }
                    Check_l(x, y-1);
                    Check_d(x, y-1);
                    Check_u(x, y+1);
                }
            }
            //Checking down
            void Check_d(int x, int y)
            {
                if(!VisitedMethod(x, y))
                {
                    return;
                }
               IsVisited_Center[x][y] = true;
                if(VisitedMethod(x+1, y) && !IsVisited_Center[x+1][y])
                {
                    if((x+2)*2 > d)
                    {
                        d = ((x+1)+1)*2;
                    }
                    Check_d(x+1, y);
                    Check_r(x+1, y);
                    Check_l(x+1, y);
                }
            }
            //Checking up
            void Check_u(int x, int y)
            {
                if(!VisitedMethod(x, y))
                {
                    return;
                }
               IsVisited_Center[x][y] = true;
                if(VisitedMethod(x-1, y) && !IsVisited_Center[x-1][y])
                {
                    if(x*2 < u)
                    {
                        u = x*2;
                    }
                    Check_u(x-1, y);
                    Check_r(x-1, y);
                    Check_l(x-1, y);
                }
            }
            //Try&Catch for FLags
            boolean VisitedMethod(int x, int y)
            {
                try {
                    if(IsVisited[x][y] == true)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                catch(Exception e)
                {
                    return false;
                }
            }
        }
        methods method = new methods();
        int sum;
        List<java.awt.Point> points = new ArrayList<>();
        for(int i = 0; i < sizei; i++)
        {
            for(int j = 0; j < sizej; j++)
            {
                if(arr[i][j] == (char)(team+'0'))
                {
                    if(!IsVisited[i][j])
                    {
                        sum = method.CheckArea(arr, i, j, (char)(team+'0'));//Returns Sum of players found to be compared with threshold
                        if(sum >= threshold)
                        {
                            int [] Center =  method.CalcCenter(i, j); //Returns array with center points
                            java.awt.Point center = new java.awt.Point(Center[0], Center[1]);
                            points.add(center);
                        }
                    }
                }
            }
        }
        java.awt.Point[] centers = new java.awt.Point[points.size()];
        for(int i = 0; i < points.size(); i++)
        {
            centers[i] = new java.awt.Point();
            centers[i] = points.get(i);
        }
        return centers;
    }
}