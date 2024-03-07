import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 20분
// dfs 이용한 조합 문제로 판단.
// curSize를 이용해서 현재 위치를 유지, curPos 를 통해서 다음 재귀에서 탐색할 곳을 정한다.
// curSize 가 6이 되면 끝
public class Main {
	private static final int LOTTO_SIZE = 6;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if (n == 0) {
				break;
			}
			int[] numbers = new int[n];
			for (int i = 0; i < n; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			pickRottos(0, 0, numbers, new int[6]);
			System.out.println();
		}
	}
	
	private static void pickRottos(int curPos, int curSize, int[] numbers,int[] lottos) {
		if (curSize == LOTTO_SIZE) {
			for (int i = 0; i < lottos.length; i++) {
				System.out.print(lottos[i] +" ");
			}
			System.out.println();
			return;
		}
		for (int i = curPos; i < numbers.length; i++) {
			lottos[curSize] = numbers[i];
			pickRottos(i + 1, curSize + 1, numbers, lottos);
		}
	}
}
