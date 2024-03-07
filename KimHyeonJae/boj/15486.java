import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        for (int cur = 0; cur < n; cur++) {
            st = new StringTokenizer(br.readLine());
            int duration = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            if (cur > 0) {
                dp[cur] = Math.max(dp[cur], dp[cur - 1]);
            }
            if (cur + duration > n) {
                continue;
            }
            dp[cur + duration] = Math.max(dp[cur + duration], dp[cur] + money);
        }
        int answer = 0;
        for (int i = 0; i < n + 1; i++) {
            answer = Math.max(answer, dp[i]);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}
