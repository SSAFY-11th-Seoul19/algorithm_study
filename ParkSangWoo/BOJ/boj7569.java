
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dh = { 1, -1, 0, 0, 0, 0 };
	static int[] dr = { 0, 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 0, -1, 1 };
	static int[][][] box;
	static int checkTomato0 = 0;
	static Queue<int[]> queue1 = new LinkedList<>();
	static Queue<int[]> queue2 = new LinkedList<>();
	static int day = -1;
	static int M;
	static int N;
	static int H;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		H = Integer.parseInt(str[2]);
		box = new int[H][N][M];

		for (int height = 0; height < H; height++) {
			for (int row = 0; row < N; row++) {
				str = br.readLine().split(" ");
				for (int col = 0; col < M; col++) {
					box[height][row][col] = Integer.parseInt(str[col]);
					if (box[height][row][col] == 0) {
						checkTomato0++;
					}
					if (box[height][row][col] == 1) {
						queue1.add(new int[] { height, row, col }); // queue는 height, row, col의 값을 가지는 1차원 배열을 받는다.
					}
				}
			}
		}

		if (checkTomato0 == 0) {
			ans = 0;
		} else {
			while (queue1.size() != 0 || queue2.size() != 0) {
				method();
			}
			if (checkTomato0 == 0) {
				ans = day;
			} else
				ans = -1;
		}
		
		

		System.out.println(ans);

	} // main end

	public static void method() {
		day++;
		if (queue2.size() == 0) {
			while (queue1.size() > 0) {
				int[] temp = queue1.poll();
				bfsForQueue1(temp[0], temp[1], temp[2]);
			}
		} else if (queue1.size() == 0) {
			while (queue2.size() > 0) {
				int[] temp = queue2.poll();
				bfsForQueue2(temp[0], temp[1], temp[2]);
			}
		}
	}

	public static void bfsForQueue1(int currentHeight, int currentRow, int currentCol) {
		for (int direction = 0; direction < 6; direction++) {
			int nextHeight = currentHeight + dh[direction];
			int nextRow = currentRow + dr[direction];
			int nextCol = currentCol + dc[direction];

			if (nextHeight < 0 || nextHeight > H - 1) {
				continue;
			}
			if (nextRow < 0 || nextRow > N - 1) {
				continue;
			}
			if (nextCol < 0 || nextCol > M - 1) {
				continue;
			}
			if (box[nextHeight][nextRow][nextCol] != 0) {
				continue;
			}
			queue2.add(new int[] { nextHeight, nextRow, nextCol });
			box[nextHeight][nextRow][nextCol] = 1;
			checkTomato0--;
		}
	}

	public static void bfsForQueue2(int currentHeight, int currentRow, int currentCol) {
		for (int direction = 0; direction < 6; direction++) {
			int nextHeight = currentHeight + dh[direction];
			int nextRow = currentRow + dr[direction];
			int nextCol = currentCol + dc[direction];

			if (nextHeight < 0 || nextHeight > H - 1) {
				continue;
			}
			if (nextRow < 0 || nextRow > N - 1) {
				continue;
			}
			if (nextCol < 0 || nextCol > M - 1) {
				continue;
			}
			if (box[nextHeight][nextRow][nextCol] != 0) {
				continue;
			}
			queue1.add(new int[] { nextHeight, nextRow, nextCol });
			box[nextHeight][nextRow][nextCol] = 1;
			checkTomato0--;
		}
	}

} // class end
