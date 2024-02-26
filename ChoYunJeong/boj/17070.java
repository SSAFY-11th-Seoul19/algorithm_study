/*
 * 가로, 세로, 대각선을 표시하며 dp로 해결 => 해당 칸에 도달하기 까지의 이전 칸들의 모든 경우의 수 합 => 누적합..?
 * 이전 파이프의 위치에 따라 달라지는 방법 => 이전 위치 저장
 * 방향 그대로 밀거나, 45도 회전만 가능 => 가로에서 세로/세로에서 가로 직각 이동 불가능
 * 가로, 대각선 => 가로 / 세로, 대각선 => 세로 / 가로, 세로, 대각선 => 대각선 : 이전 칸의 경우가 나뉘어진다.
 * 이전칸을 확인하면 경우의 수가 작아짐
 */

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N; // 집의 크기
	static int[][][] dp;
	static char[][] home; // 집의 상태

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[N][N][3]; // 가로, 세로, 대각선
		home = new char[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				home[i][j] = st.nextToken().charAt(0);
			}
		}

		dp[0][1][0] = 1; // 처음 파이프의 위치(가장 뒷부분) => 현재 위치와 상태(가로)
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) { // 가장 왼쪽은 파이프의 크기가 2라 갈 수 없음
				if (i == 0 && j < 2) { // 첫번째 행, j==1의 경우는 초기값이므로 건너띔
					continue;
				}
				if (home[i][j] == '1') { // 만약 상태가 벽이라면
					continue; // 넘김
				}

				// 현재 위치로 오기까지의 이전 가로, 이전 세로, 이전 대각선의 경우의 수 덧셈
				if (home[i][j - 1] != '1') { // 가로는 이전 가로 + 이전 대각선
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
				}

				if (i - 1 < 0) { // 세로와 대각선 체크
					continue;
				}

				if (home[i - 1][j] != '1') { // 세로는 이전 세로 + 이전 대각선
					dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
				}
				// 대각선은 이전 가로 + 이전 세로 + 이전 대각선
				if (home[i - 1][j - 1] != '1' && home[i - 1][j] != '1' && home[i][j - 1] != '1') {
					dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				}
			}
		}
		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}
}
