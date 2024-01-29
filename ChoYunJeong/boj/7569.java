import java.util.*;
import java.io.*;
import java.util.ArrayDeque;

class tomato {
	int x;
	int y;
	int z;

	tomato(int z, int y, int x) {
		this.x = x; // x=>행
		this.y = y; // y=>열
		this.z = z; // z=>높이
	}
}

public class Main {
	static int n, m, h; // 행, 열, 높이의 크기가 담길 변
	static int[][][] tomatoes; //
	static boolean[][] checked;
	static int[] dx = { -1, 1, 0, 0, 0, 0 }; // row의 방향 벡터
	static int[] dy = { 0, 0, 1, -1, 0, 0 }; // column의 방향 벡터
	static int[] dz = { 0, 0, 0, 0, -1, 1 }; // height의 방향 벡터
	static Queue<tomato> tomatoQ = new ArrayDeque<tomato>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		;
		m = Integer.parseInt(st.nextToken()); // 2차원 배열 행의 크기
		n = Integer.parseInt(st.nextToken()); // 2차원 배열 열의 크기
		h = Integer.parseInt(st.nextToken()); // 2차원 배열 높이의 크기
		tomatoes = new int[h][n][m];

		for (int i = 0; i < h; i++) { // tomatoes에 tomato 담고, box에 tomatoes 담기
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < m; k++) {
					tomatoes[i][j][k] = Integer.parseInt(st.nextToken());
					if (tomatoes[i][j][k] == 1) {
						tomatoQ.add(new tomato(i, j, k));
					}
				}
			}
		}

		bw.write(Integer.toString(bfs()));
		bw.flush();

		br.close();
		bw.close();
	}

	static int bfs() {
		int maxDay = Integer.MIN_VALUE;
		while (!(tomatoQ.isEmpty())) {
			tomato t = tomatoQ.remove(); // Q가 비어있다면, 에러 반환
			int x = t.x;
			int y = t.y;
			int z = t.z;
			for (int v = 0; v < 6; v++) { // 방향 벡터를 돌며
				int nx = x + dx[v];
				int ny = y + dy[v];
				int nz = z + dz[v];

				if (nx >= 0 && nx < n && ny >= 0 && ny < m && nz >= 0 && nz < h) {
					if (tomatoes[nz][nx][ny] == 0) {
						tomatoQ.add(new tomato(nz, ny, nx));

						tomatoes[nz][nx][ny] = tomatoes[nz][nx][ny] + 1;
					}
				}
			}
		}
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if (tomatoes[i][j][k] == 0) {
						return -1;
					}
					maxDay = Math.max(maxDay, tomatoes[i][j][k]);

				}
			}
		}

		if (maxDay == 1) {
			return 0;
		}
		return maxDay - 1;
	}
}
