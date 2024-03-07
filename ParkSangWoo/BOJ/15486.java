import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stn;
	static int N;
	static int[] dp, work;
	static int[][] schedule;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 2];
		work = new int[2];
		schedule = new int[N + 1][2];

		for (int i = 0; i < N; i++) {
			stn = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(stn.nextToken());
			schedule[i][1] = Integer.parseInt(stn.nextToken());
		}
		int max = -1;
		int nxt = 0;
		for (int i = 1; i <= N; i++) {
			work = schedule[i - 1];
			if (max < dp[i]) {
				max = dp[i];
			}
			nxt = i + work[0];
			if (nxt > N + 1) {
				continue;
			}
			dp[nxt] = Math.max(dp[nxt], max + work[1]);
		}

		for (int i = N + 1; i >= 1; i--) {
			if (max < dp[i]) {
				max = dp[i];
				break;
			}
		}
		bw.write(Integer.toString(max));
		bw.flush();
		bw.close();
	}
}
