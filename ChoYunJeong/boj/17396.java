/*
 * vertex와 dege 리스트를 각각 만드려 했지만, time까지 만드는건 복잡해보임 => 객체 생성으로 전환
 * 단순 bfs로 탐색 => 최소시간을 찾기 위해 다익스트라로 변경
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int link; // 연결된 노드 번호
	long time; // 걸리는 시간

	public Node(int link, long time) {
		this.link = link;
		this.time = time;
	}

	@Override
	public int compareTo(Node n) { // 우선순위 큐에 넣을 예정이라 필수 작성,,
		return (int) (this.time - n.time);
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk; // 문자열을 자를 stk
	static ArrayList<Node>[] nodes; // ArrayList<cNode>로 이루어진 배열

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken()); // 집합의 개수 => N+1
		int M = Integer.parseInt(stk.nextToken()); // 연산의 개수
		nodes = new ArrayList[N]; // Node로 선언시 타입 안정성 오류,,
		long[] dist = new long[N];
		Arrays.fill(dist, Long.MAX_VALUE); // 최솟값을 구해야하므로, 최댓값으로 변경
		dist[0] = 0;
		String answer = "-1";

		// 적에게 보이는지 입력 받기 + nodes 초기화2
		boolean[] visiblity = new boolean[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nodes[i] = new ArrayList<Node>();
			if (stk.nextToken().equals("1")) {
				visiblity[i] = true; // 보이는 경우
				continue;
			}
			visiblity[i] = false; // 안 보이는 경우
		}

		// cNode 리스트 넣기
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int t = Integer.parseInt(stk.nextToken());
			nodes[a].add(new Node(b, t));
			nodes[b].add(new Node(a, t));
		}

		// 다익스트라 실행
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		queue.offer(new Node(0, 0)); // 초기값 넣기

		while (!queue.isEmpty()) {
			Node cNode = queue.poll(); // 현재 위치한 노드
			if (visited[cNode.link]) { // 현재 노드와 연결된 노드로 넘어갈 테니
				continue; // 현재 노드와 연결된 노드에 이미 방문했다면 넘김
			}
			visited[cNode.link] = true; // 연결된 노드 방문 처리

			for (int i = 0; i < nodes[cNode.link].size(); i++) {
				Node nNode = nodes[cNode.link].get(i); // 이동할 노드의 연결된 모든 노드로 순차 이동
				if (visiblity[nNode.link] && nNode.link != N - 1) {
					continue; // 마지막 분기점이 아님에도 보이는 분기점이라면 못 감 => 넘김
				}
				// 이동할 노드의 연결된 노드까지의 time값을 더 작은 값으로 갱신
				if (dist[nNode.link] > dist[cNode.link] + nNode.time) {
					dist[nNode.link] = dist[cNode.link] + nNode.time;
					queue.offer(new Node(nNode.link, dist[nNode.link])); // 이동한 노드 큐에 추가
				}
			}
		}

		if (dist[N - 1] != Long.MAX_VALUE) {
			answer = Long.toString(dist[N - 1]);
		}

		bw.write(answer);
		bw.flush();
		br.close();
		bw.close();
	}
}
