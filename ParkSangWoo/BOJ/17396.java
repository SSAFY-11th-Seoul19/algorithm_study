import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stn;
	static int N, M, a, b, t;
	static long[] distance;
	static PriorityQueue<PqFormat> pqueue = new PriorityQueue<>();
	static List<Node>[] adjList;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		stn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stn.nextToken());
		M = Integer.parseInt(stn.nextToken());
		isVisited = new boolean[N];
		distance = new long[N + 1];

		stn = new StringTokenizer(br.readLine());
		int k;
		for (int i = 0; i < N; i++) {
			k = Integer.parseInt(stn.nextToken());
			if (k == 0) {
				continue;
			}
			isVisited[i] = true;
		}

		adjList = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new LinkedList<>();
		}

		for (int i = 0; i < M; i++) {
			stn = new StringTokenizer(br.readLine());
			a = Integer.parseInt(stn.nextToken());
			b = Integer.parseInt(stn.nextToken());
			t = Integer.parseInt(stn.nextToken());

			adjList[a].add(new Node(b, t));
			if (b == N - 1) {
				continue;
			}
			adjList[b].add(new Node(a, t));
		}

		Arrays.fill(distance, Long.MAX_VALUE);
		distance[0] = 0;

		pqueue.add(new PqFormat(0, 0));
		while (!pqueue.isEmpty()) {
			PqFormat now = pqueue.poll();
			if (isVisited[now.index] == true) {
				continue;
			}
			isVisited[now.index] = true;

			for (Node curNode : adjList[now.index]) {
				if (distance[curNode.Next] < curNode.Cost + distance[now.index]) {
					continue;
				}
				distance[curNode.Next] = curNode.Cost + distance[now.index];
				pqueue.add(new PqFormat(curNode.Next, distance[curNode.Next]));
			}

		}

		if (distance[N - 1] == Long.MAX_VALUE) {
			bw.write(Integer.toString(-1));
			bw.flush();
			bw.close();
			return;
		}
		bw.write(Long.toString(distance[N - 1]));
		bw.flush();
		bw.close();
	}
}

class Node {
	int Next, Cost;

	Node(int Next, int Cost) {
		this.Next = Next;
		this.Cost = Cost;
	}
}

class PqFormat implements Comparable<PqFormat> {
	int index;
	long dist;

	PqFormat(int Index, long dist) {
		this.index = Index;
		this.dist = dist;
	}

	@Override
	public int compareTo(PqFormat o) {
		if (this.dist < o.dist) {
			return -1;
		}
		return 1;
	}
}
