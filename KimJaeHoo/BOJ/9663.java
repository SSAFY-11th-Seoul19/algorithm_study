import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] chess = new int [15];
    static int ans;

    public static boolean isOk(int r, int c){
        boolean result = true;
        for(int i = 0 ; i < r; i++){
            result = false;
            if(chess[i] == c)
                break;
            if(Math.abs(r-i) == Math.abs(c-chess[i]))
                break;
            result = true;
        }
        return result;
    }
    public static void queens(int row){
        if(row >= N){
            ans++;
            return;
        }
        for(int col = 0; col < N; col++){

            if(!isOk(row, col))
                continue;

            chess[row] = col;
            queens(row+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른입력
        N = Integer.parseInt(br.readLine());
        queens(0);
        System.out.println(ans);
    }
}
