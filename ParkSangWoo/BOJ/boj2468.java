
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static Queue<int[]> queue = new LinkedList<>();
	static int N;
	static int[][] board;
	static String[] str;
	static int maxHeight;
	static boolean[][] isvisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int ans = 1; // 아무 곳도 잠기지 않았을 때 1이기 때문에 1로 시작
	static int cnt;
	static int floodHeight = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		isvisited = new boolean[N][N];

		for (int boardRow = 0; boardRow < N; boardRow++) {
			str = br.readLine().split(" ");
			for (int boardCol = 0; boardCol < N; boardCol++) {
				board[boardRow][boardCol] = Integer.parseInt(str[boardCol]);
				maxHeight = Math.max(maxHeight, board[boardRow][boardCol]); // Math를 사용하는 것과 if문에 continue로 넘기는 것 중 뭐가
																			// 좋은가??
			}
		} // 변수 선언, 입력값 할당

		while (floodHeight < maxHeight) {
			for (int boardRow = 0; boardRow < N; boardRow++) {
				for (int boardCol = 0; boardCol < N; boardCol++) {
//					board[boardRow][boardCol] -= 1;
					isvisited[boardRow][boardCol] = false;
				}
			} // 비에 젖은 보드 완성
			cnt = 0;
			find();
			ans = Math.max(ans, cnt);

			floodHeight++;
		}

		System.out.println(ans);
	}// main end

	public static void find() {
		for (int boardRow = 0; boardRow < N; boardRow++) {
			for (int boardCol = 0; boardCol < N; boardCol++) {
				if (board[boardRow][boardCol] <= floodHeight || isvisited[boardRow][boardCol]) {
					continue;
				}
				findSafetyArea(boardRow, boardCol);
			}
		}
	}// find end 보드 처음부터 끝까지 찍으면서 0이 아닌 곳을 찾으면 파인더를 실행하여 연결된거 싹 죽이는 메서드

	public static void findSafetyArea(int startRow, int startCol) {
		queue.add(new int[] { startRow, startCol });
		isvisited[startRow][startCol] = true;
		cnt++;
		while (queue.size() > 0) {
			int[] temp = queue.poll();
			findAdjacent(temp[0], temp[1]);
		}
	} // end findSafetyArea

	public static void findAdjacent(int currentRow, int currentCol) {
//		isvisited[currentRow][currentCol] = true;

		for (int i = 0; i < 4; i++) {
			int nextRow = currentRow + dr[i];
			int nextCol = currentCol + dc[i];
			if (nextRow < 0 || nextRow > N - 1) {
				continue;
			}
			if (nextCol < 0 || nextCol > N - 1) {
				continue;
			}
			if (isvisited[nextRow][nextCol] || board[nextRow][nextCol] <= floodHeight) {
				continue;
			}
			queue.add(new int[] { nextRow, nextCol });
			isvisited[nextRow][nextCol] = true;
		}

	}// end findAdjacent 상하좌우 확인하여 예외 처리 후 큐에 넣어주고 방문처리 해준다.

}
