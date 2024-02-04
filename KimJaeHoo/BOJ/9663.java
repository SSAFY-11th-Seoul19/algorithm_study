import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static boolean[] occupiedColumn = new boolean[14];
    static boolean[] occupiedDiagonalRtoL = new boolean[14 * 2 -1];
    static boolean[] occupiedDiagonalLtoR = new boolean[14 * 2 -1];
    static int ans;

    public static void queens(int row){
        if(row == N){
            ans++;
            return;
        }
        for(int col = 0; col < N; col++){
            if(occupiedColumn[col] || occupiedDiagonalRtoL[row + col] || occupiedDiagonalLtoR[row - col + N - 1])
                continue;

            occupiedColumn[col] = true;
            occupiedDiagonalRtoL[row + col] = true;
            occupiedDiagonalLtoR[row - col + N - 1] = true;

            queens(row+1);

            occupiedColumn[col] = false;
            occupiedDiagonalRtoL[row + col] = false;
            occupiedDiagonalLtoR[row - col + N - 1] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른입력
        N = Integer.parseInt(br.readLine());
        queens(0);
        System.out.println(ans);
    }
}
