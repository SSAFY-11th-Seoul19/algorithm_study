import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] weights;
    static int[] values;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weights = new int[N+1];
        values = new int[N+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+1][K+1];
        for(int i=1;i<=N;i++){
            int weight = weights[i];
            int value = values[i];

            for(int j=1;j<=K;j++){
                if(weight>j){
                    dp[i][j] = dp[i-1][j];
                    continue;
                }

                dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight]+value);
            }
        }
        bw.write(Integer.toString(dp[N][K]));
        bw.flush();

        br.close();
        bw.close();
    }
}
