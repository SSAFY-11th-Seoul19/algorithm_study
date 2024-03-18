import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static ArrayList<Node>[] list;
	static int[] haveSight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		haveSight = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {	
			haveSight[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, t));
			list[b].add(new Node(a,t));
		}
		
		long ans = Dijkstra(0);
		if(ans==Long.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
	}
	
	public static long Dijkstra(int n) {
		long[] distance = new long[N];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[n] = 0;
		
		boolean[] visited = new boolean[N];
		
		//우선순위 큐
		PriorityQueue<Node>pq = new PriorityQueue<>((o1, o2)->{
			if(o1.val>o2.val) {
				return 1;
			}	
			return -1;
		});	 
		
		pq.offer(new Node(n, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(visited[node.node]) continue;
			visited[node.node] = true;
			
			for(int i=0; i<list[node.node].size(); i++) {
				Node nextNode = list[node.node].get(i);
				if(nextNode.node!=N-1 && haveSight[nextNode.node]==1) continue;
				if(distance[nextNode.node] > distance[node.node] + nextNode.val) {
					distance[nextNode.node] = distance[node.node] + nextNode.val;
					pq.offer(new Node(nextNode.node, distance[nextNode.node]));
				}
			}
		}
		return distance[N-1];
	}

}

class Node {
	int node;
	long val;

	public Node(int node, long val) {
		this.node = node;
		this.val = val;
	}
}
