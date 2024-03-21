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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			// 나가는 그래프
			// 들어오는 노드 결정하는 배열
			List<List<Integer>> outGraph = new ArrayList<>();
			int[] inGraph = new int[n];
			int[] buildingTimes = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				buildingTimes[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < n; i++) {
				outGraph.add(new ArrayList<>());
			}
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				outGraph.get(start).add(end);
				inGraph[end] += 1;
			}
			int endPosition = Integer.parseInt(br.readLine()) - 1;
			// 입력 끝

			LinkedList<int[]> que = new LinkedList<>();
			int[] dp = new int[n];
			Arrays.fill(dp, -1);
			for (int i = 0; i < n; i++) {
				if (inGraph[i] == 0) {
					// 현재 노드, 걸리는 시간
					que.add(new int []{i, buildingTimes[i]});
					dp[i] = buildingTimes[i];
				}
			}

			while (!que.isEmpty()) {
				int[] node = que.removeFirst();
				int cur = node[0];
				int time = node[1];
				boolean isGameEnd = false;
				for (int newNode: outGraph.get(cur)) {
					int totalTime = time + buildingTimes[newNode];
					dp[newNode] = Math.max(totalTime, dp[newNode]);
					inGraph[newNode] -= 1;
					if (inGraph[newNode] == 0) {
						que.add(new int[] {newNode, dp[newNode]});
					}
					if (inGraph[newNode] == 0 && newNode == endPosition) {
						isGameEnd = true;
						break;
					}
				}
				if (isGameEnd) {
					break;
				}
			}
			System.out.println(dp[endPosition]);
		}
	}
}
