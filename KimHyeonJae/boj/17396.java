import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;


public class Main {
	static List<List<Node>> graph = new ArrayList<>();
	static int[] visibles;
	static long[] dp;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	// 정점의 수, edge의 수
    	int numberOfVertex = Integer.parseInt(st.nextToken());
    	int numberOfEdge = Integer.parseInt(st.nextToken());
    	
    	visibles = new int[numberOfVertex];
    	dp = new long[numberOfVertex];
    	st = new StringTokenizer(br.readLine());
    	for (int i = 0;i < numberOfVertex; i++) {
    		visibles[i] = Integer.parseInt(st.nextToken());
    		dp[i] = Long.MAX_VALUE;
    		graph.add(new ArrayList<>());
    	}
    	visibles[numberOfVertex - 1] = 0;
    	// 다음 노드, 시간
    	for (int i = 0; i < numberOfEdge; i ++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		long t = Long.parseLong(st.nextToken());
    		graph.get(a).add(new Node(t, b));
    		graph.get(b).add(new Node(t, a));
    	}
    	dp[0] = 0;
    	PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> {
    		if (a.weight > b.weight) {
    			return 1;
    		}
    		return -1;
    	} );
    	// 시간, 현재 위치
    	que.add(new Node(0, 0));
    	while (!que.isEmpty()) {
    		Node node = que.poll();
    		long curTime = node.weight;
    		int curPos = node.position;
    		if (dp[curPos] < curTime) {
    			continue;
    		}
    		for (Node nextNode: graph.get(curPos)) {
    			int nextPos = nextNode.position;
    			long nextTime = nextNode.weight +curTime;
    			if (nextPos == numberOfVertex - 1) {
    				dp[nextPos] = Math.min(dp[nextPos], nextTime);
    				continue;
    			}
    			if (visibles[nextPos] == 0 && dp[nextPos] > nextTime) {
    				dp[nextPos] = nextTime;
    				que.add(new Node(nextTime, nextPos));
    			}
    		}
    	}
    	
    	if (dp[numberOfVertex - 1] == Long.MAX_VALUE) {
    		System.out.println(-1);
    		return;
    	}
    	System.out.println(dp[numberOfVertex - 1]);
    }
    
    static class Node {
    	long weight;
    	int position;
    	
    	public Node(long weight, int position) {
    		this.weight = weight;
    		this.position = position;
    	}
    }
}
