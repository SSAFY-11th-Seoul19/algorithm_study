import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int N;
	static int count;
	static int[] queens;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in)); // 입력
		bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력
		N = Integer.parseInt(br.readLine()); // N 입력 => 재귀엔 값이 달라지므로 static 선언X
		queens = new int[N];
		visited = new boolean[N];
		dfs(0);
		bw.append(Integer.toString(count)); // bw에 min 저장
		bw.flush(); // bw 출력
		br.close(); // 입력 종료
		bw.close(); // 출력 종료

	}

	static void dfs(int depth) {
		if (depth == N) {
			count++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i]) { // i가 이미 사용된 열이면
				continue;
			}
			if (!canQueenLocate(depth, i)) { // depth에 i값 갱신 전 => i값 피수로 넣어줘야 함
				continue;
			}
			queens[depth] = i; // depth번째 depth행엔(1차원이지만) i값 == i열에 queen
			visited[i] = true;
			dfs(depth + 1);
			visited[i] = false;
		}

	}

	static boolean canQueenLocate(int index, int value) {
		for (int i = 0; i < index; i++) {
			// visited로 열체크 => 대각선 체크만 하면 됨
			if (index - i == value - queens[i] || index - i == queens[i] - value) {
				return false;
			}
		}
		return true;
	}

}
