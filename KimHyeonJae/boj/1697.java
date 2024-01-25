import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
		if (start == end) {
			System.out.println(0);
			return;
		}
		LinkedList<Integer> que = new LinkedList<>();
		int[] dp = new int[200_002];
		Arrays.fill(dp, 200_002);
		dp[start] = 0;
		que.add(start);
		while (!que.isEmpty()) {
			int cur = que.removeFirst();
			int[] nexts = new int[] {cur + 1, cur -1, cur* 2};
			for (int nextPos:nexts) {
				if (nextPos < 0 || nextPos >= 200_002 || dp[nextPos] < dp[cur] + 1) {
					continue;
				}
				dp[nextPos] = dp[cur] + 1; 
				que.add(nextPos);
			}
		}
		System.out.println(dp[end]);
	}

}
