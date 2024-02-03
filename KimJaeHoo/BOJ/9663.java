import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static boolean[][] chess = new boolean [15][15];
    static int ans;

    public static boolean isOk(int r, int c){
        boolean result = false;
        for(int i = 0 ; i < N; i++){
            result = false;
            if(r+i < N && c+i < N && chess[r+i][c+i])
                break;
            if(r-i >= 0 && c-i >= 0 && chess[r-i][c-i])
                break;
            if(r+i < N && c-i >= 0 && chess[r+i][c-i])
                break;
            if(r-i >= 0 && c+i < N && chess[r-i][c+i])
                break;
            if(chess[r][i] || chess[i][c])
                break;
            result = true;
        }
        return result;
    }
    public static void queens(int row){
        if(row == N){
            ans++;
            return;
        }
        for(int col = 0; col < N; col++){

            if(!isOk(row, col))
                continue;

            chess[row][col] = true;
            queens(row+1);
            chess[row][col] = false;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠른입력
        N = Integer.parseInt(br.readLine());
        queens(0);
        System.out.println(ans);
    }
}
