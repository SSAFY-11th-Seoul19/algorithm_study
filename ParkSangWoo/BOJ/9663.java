
import java.util.Scanner;

public class Main {
	static int N;
	static int ans = 0;
	static int[][] board;
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		N = sc.nextInt();

		board = new int[N][N];

		method(0, 0, 0);
		
		System.out.println(ans);

	}

	public static void method(int startRow, int startCol, int cnt) {
		if (cnt == N) {
			ans++;
			return;
		}

		for (int row = startRow; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (board[row][col] != 0 || (row==startRow && col<startCol)) {
					continue;
				}
				region(row, col, cnt);
				method(row, col, cnt + 1);
				regionDelete(row, col, cnt);
			}
		}

	}

	public static void region(int row, int col, int cnt) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == row || j == col || i - row == j - col || i - row == col - j) {
					board[i][j] += cnt + 1;
				}
			}
		}
	}

	public static void regionDelete(int row, int col, int cnt) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == row || j == col || i - row == j - col || i - row == col - j) {
					board[i][j] -= cnt + 1;
				}
			}
		}
	}
}

