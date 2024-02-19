import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static List<int[]> combs = new ArrayList<int[]>();
    private static int[] graph;
    private static int answer = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int maxKg = Integer.parseInt(st.nextToken());

        int[] dp = new int[maxKg + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int kg = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            for (int curKg = maxKg; curKg >= kg; curKg--) {
                dp[curKg] = Math.max(dp[curKg], dp[curKg - kg] + v);
            }
        }
        bw.write(String.valueOf(dp[maxKg]));
        bw.flush();
        br.close();
        bw.close();
    }
}
