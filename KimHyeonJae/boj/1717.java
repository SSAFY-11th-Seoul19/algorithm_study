import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());

    	
    	parents = new int[n + 1];
    	
    	for (int i = 1; i < n + 1; i++) {
    		parents[i] = i;
    	}
    	
    	for (int i = 0; i < m; i ++) {
    		st = new StringTokenizer(br.readLine());
    		int kind = Integer.parseInt(st.nextToken());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		// 합집합을 함
    		if (kind == 0) {
    			union(a, b);
    			continue;
    		}
    		// 같은 집합에 있는지 확인하는 연산
    		if (find(a) == find(b) ) {
    			sb.append("YES").append("\n");
    			continue;
    		}
    		sb.append("NO").append("\n");
    	}
    	System.out.println(sb.toString());
    }
    
    private static int find(int n) {
    	if (parents[n] == n) {
    		return n;
    	}
    	parents[n] = find(parents[n]);
    	return parents[n];
    }
    
    private static void union(int c1, int c2) {
    	int p1 = find(c1);
    	int p2 = find(c2);
    	if (p1 < p2) {
    		parents[p2] = p1;
    		return;
    	}
    	parents[p1] = p2;
    }
}
