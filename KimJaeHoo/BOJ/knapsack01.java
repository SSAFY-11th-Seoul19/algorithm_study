import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] weight, values;
    static int[][] dp;

    public static void knapsack(){
        for(int c = 0; c <= K; c++){
            if(weight[1] > c) continue;
            dp[1][c] = values[1];
        }

        for(int i = 2; i <= N; i++){
            for(int j = 0; j <= K; j++){
                if(weight[i] > j)
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Integer.max(dp[i-1][j], values[i] + dp[i-1][j - weight[i]]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        weight = new int[N+1];
        values = new int[N+1];
        dp = new int[N+1][K+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }
        knapsack();
        System.out.println(dp[N][K]);
    }
}
