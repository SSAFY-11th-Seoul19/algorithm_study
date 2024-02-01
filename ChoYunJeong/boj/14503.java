/*
 * 방의 크기 N,M을 받는다.
 * 청소기의 위치 r,c와 청소기의 방향 d를 받는다.
 * 방의 상태를 받는다.
 * 청소기의 이동을 위해 방향벡터를 만든다.
 * 순서대로 구현한다.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Cleaner {
	int r;
	int c;
	int d;

	Cleaner(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class boj_14503_로봇청소기 {
	static int N, M;
	static int[] dx = { -1, 0, 1, 0 }; // 상우하좌 > 북동남서
	static int[] dy = { 0, 1, 0, -1 }; // 상우하좌 > 0123
	static int[][] room; // 방
	static int count = 0; // 청소 횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		room = new int[N][M];

		// 청소기 상태 받고 객체 생성
		stk = new StringTokenizer(br.readLine());
		Cleaner cleaner = new Cleaner(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
		int direction = Integer.parseInt(stk.nextToken());

		// 방 상태 받기
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(stk.nextToken());
			}
		}

		Loop1: while (true) {
			int row = cleaner.r;
			int column = cleaner.c;

			if (room[row][column] == 0) { // 현재 위치의 방이 더럽다면
				count++; // 청소 횟수 증가
				room[row][column] = 2; // 방 상태 변환 => 1은 벽, 0은 청소X
			}
			for (int v = 0; v < 4; v++) { // 최대 4번 돌면서
				direction = (direction + 3) % 4; // 반 시계 방향으로 회전
				int nx = row + dx[direction];
				int ny = column + dy[direction];
				if (room[nx][ny] == 0) {
					cleaner.r = nx;
					cleaner.c = ny;
					continue Loop1;
				}
			}
			// 청소가 모두 되어 있다면
			int nx = row + dx[(direction + 2) % 4];
			int ny = column + dy[(direction + 2) % 4];
			if (room[nx][ny] == 1) {
				break;
			}
			cleaner.r = nx;
			cleaner.c = ny;
		}

		bw.append(Integer.toString(count));
		bw.flush();
		br.close();
		bw.close();
	}

}
