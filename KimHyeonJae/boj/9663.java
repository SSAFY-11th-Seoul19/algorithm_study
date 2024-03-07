import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// input 
		int n = Integer.parseInt(br.readLine());
		int[][] graph = new int[n][n];
		dfs(0, graph);
		bw.write(String.valueOf(answer));
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void dfs(int cy, int[][] graph) {
		if (cy >= graph.length) {
			answer += 1;
			return;
		}
		for (int x = 0; x < graph.length; x++) {
			if (canExist(cy, x, graph)) {
				graph[cy][x] = 1;
				dfs(cy + 1, graph);
				graph[cy][x] = 0;
			}
		}
	}
	
	private static boolean canExist(int cy, int cx, int[][] graph) {
		for (int y = 0; y < cy; y++) {
			if (graph[y][cx] == 1) {
				return false;
			}
		}
		int prevX = cx;
		int nextX = cx;
		for (int y = cy - 1; y >= 0; y--) {
			prevX -= 1;
			nextX += 1;
			if (prevX >= 0 && graph[y][prevX] == 1) {
				return false;
			}
			if (nextX < graph.length && graph[y][nextX] == 1) {
				return false;
			}
		}
		
		return true;
	}
}
