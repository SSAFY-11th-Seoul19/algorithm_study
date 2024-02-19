import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 물품의 수
        int K = Integer.parseInt(st.nextToken());   // 최대 무게

        int[] dp = new int[K + 1];
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            for (int i = K; i > 0; i--) {
                if (i < W) {
                    break;
                }
                dp[i] = Math.max(dp[i], dp[i - W] + V);
            }
        }

        bw.write(String.valueOf(dp[K]));
        bw.flush();
    }
}



