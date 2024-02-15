import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int[] parent;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//분리집합에서 사이클임을 확인하기 위해 자신의 부모 선언
		parent = new int[n+1];
		for(int i=1; i<=n; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cal==1) {
				if(find(a) == find(b)) {
					sb.append("YES").append("\n");
					continue;
				}
				sb.append("NO").append("\n");
				continue;
			}
			if(cal==0) {
				if(find(a) ==find(b)) continue;
				union(a, b);
			}
			
		}
		bw.write(sb.toString());
		bw.flush();
	}
	public static int find(int x) {
		if(parent[x] !=x) {
			parent[x] = find(parent[x]);
		}
		
		return parent[x];
	}
	
	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA > rootB) {
			parent[rootA] = rootB;
			return;
		}
		if(rootB>rootA) {
			parent[rootB] = rootA;
			return;
		}
	}

} 
