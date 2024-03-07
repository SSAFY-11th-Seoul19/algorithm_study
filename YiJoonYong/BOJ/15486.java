import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];

        StringTokenizer st;
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int lastDay = N + 1;
        int[] dp = new int[lastDay + 1];
        for (int i = N; i > 0; i--) {
            if (lastDay < i + T[i]) {
                dp[i] = dp[i + 1];
                continue;
            }

            dp[i] = Math.max(dp[i + 1], dp[i + T[i]] + P[i]);
        }

        bw.write(String.valueOf(dp[1]));
        bw.flush();
    }
}
