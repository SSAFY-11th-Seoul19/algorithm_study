import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] place;
	static boolean[][] checked;
	static int[] dx = { 1, -1, 0, 0 }; // row의 방향 벡터
	static int[] dy = { 0, 0, 1, -1 }; // column의 방향 벡터

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(br.readLine()); // 2차원 배열 행과 열의 크기
		StringTokenizer st;
		place = new int[n][n];; // 지역을 저장할 변수
		int maxHeight = 0; // 가장 높은 지역의 높이
		int maxArea = 0; // 가장 많은 안전한 영역의 개수
		for (int i = 0; i < n; i++) { // place 배열에 장마지역 값 받기
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				place[i][j] = Integer.parseInt(st.nextToken());
				if (place[i][j] > maxHeight)
					maxHeight = place[i][j];
			}
		}

		for (int h = 0; h < maxHeight + 1; h++) {// 장마 강수량을 증가시키며 maxArea 갱신
			checked = new boolean[n][n]; // 방문 체크 배열
			int temp = 0; // 잠기지 않은 지역을 담을 임시 변수

			for (int i = 0; i < n; i++) { // 모든 지역을 들리며 검사
				for (int j = 0; j < n; j++) {
					// checked == false : 미방문 && place[i][j]>h : 잠기지 않음
					if (!checked[i][j] && place[i][j] > h) {
						temp += dfs(i, j, h); // dfs 호출
					}
				}
			}
			maxArea = Math.max(maxArea, temp); // 임시변수의 값과 maxArea 비교 => 갱신
		}
		bw.write(Integer.toString(maxArea));
        bw.flush();

        br.close();
        bw.close();
	}

	static int dfs(int r, int c, int h) {
		checked[r][c] = true; // 미방문 지역 방문 체크 -> 함수가 끝나도 이후에 추가방문X
		for (int i = 0; i < 4; i++) { // 방향벡터를 돌며, 상하좌우 체크 => 안전한 지역인가?
			int nr = r + dx[i];
			int nc = c + dy[i];

			// nr, nc가 범위 내에 존재X or 이미 방문한 지역 => continue
			if (nr < 0 || nr >= n || nc < 0 || nc >= n || checked[nr][nc])
				continue;
			if (place[nr][nc] > h) // 상하좌우 중 안전한 지역이 있다면, 추가 순찰
				dfs(nr, nc, h);
		}
		// 모든 연결된 지역을 순회했으므로, 안전한 지역 반환(연결되어있을 경우, 지역은 무조건 1개)
		return 1;
	}
}
