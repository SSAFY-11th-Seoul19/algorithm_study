import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		
		// for문에서 j=K-1으로 하면 값이 바뀜 => backpack[K]는 index범위 out => K+1로 초기화
		int[] backpack = new int[K + 1];
		int[] weight = new int[N];
		int[] value = new int[N];

		// 1부터 N까지 물건의 무게와 가치 받기
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(stk.nextToken());
			value[i] = Integer.parseInt(stk.nextToken());
		}

		// 물건 0번째부터 차례대로 max 비교
		for (int i = 0; i < N; i++) {
			// K부터 시작하기에 backpack[j]과 backpack[j-wight[i]] 모두 i-1번의 값 => 1차원 배열 가능
			for (int j = K; j - weight[i] >= 0; j--) {
				// i물건을 넣지 않은 경우(j-1번째에 저장된 최댓값)와 i물건과 i무게를 제한 무게의 최댓값의 가치의 합을 비교
				backpack[j] = Math.max(backpack[j], backpack[j - weight[i]] + value[i]);
			}
		}

		bw.append(Integer.toString(backpack[K])); // K무게의 최댓값 출력
		bw.flush();
		br.close();
		bw.close();
	}
}
