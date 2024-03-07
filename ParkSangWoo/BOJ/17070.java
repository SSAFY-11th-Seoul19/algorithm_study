import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stn;
	static int[][] arr;
	static int N, cnt;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			stn = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(stn.nextToken());
			}
		}

		move(1, 1, 2);

		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void move(int type, int row, int col) {
		if (row == N || col == N) {
			if (row == N && col == N) {
				cnt++;
				return;
			}
			if (type != 2) {
				return;
			}
			if (findWall(row, col)) {
				cnt++;
			}
			return;
		}
		// 가로
		if (type == 1) {
			if (arr[row][col + 1] != 0) {
				return;
			}
			if (col + 1 > N) {
				return;
			}
			move(1, row, col + 1);
			if (arr[row + 1][col] != 0 || arr[row + 1][col + 1] != 0) {
				return;
			}
			if (row + 1 > N) {
				return;
			}
			move(2, row + 1, col + 1);
			return;
		}

		// 대각선
		if (type == 2) {
			if (arr[row][col + 1] != 0 || col + 1 > N) {
				if (arr[row + 1][col] != 0 || row + 1 > N) {
					return;
				}
				move(3, row + 1, col);
				return;
			}
			move(1, row, col + 1);
			if (arr[row + 1][col] != 0 || row + 1 > N) {
				return;
			}
			move(3, row + 1, col);
			if (arr[row + 1][col + 1] != 0) {
				return;
			}
			move(2, row + 1, col + 1);
			return;
		}

		// 세로
		if (type == 3) {
			if (arr[row + 1][col] != 0) {
				return;
			}
			if (row + 1 > N) {
				return;
			}
			move(3, row + 1, col);
			if (arr[row][col + 1] != 0 || arr[row + 1][col + 1] != 0) {
				return;
			}
			if (col + 1 > N) {
				return;
			}
			move(2, row + 1, col + 1);
			return;
		}
	}

	public static boolean findWall(int row, int col) {
		if (row == N) {
			for (int i = col + 1; i <= N; i++) {
				if (arr[N][i] == 1) {
					return false;
				}
			}
			return true;
		}
		for (int i = row + 1; i <= N; i++) {
			if (arr[i][N] == 1) {
				return false;
			}
		}
		return true;
	}
}
