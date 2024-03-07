import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br; // 입력을 위한 br
	static BufferedWriter bw; // 출력을 위한 bw
	static StringBuilder sb; // 출력을 저장할 sb
	static StringTokenizer stk; // 문자열을 자를 stk
	static int K;
	static int[] S;
	static int[] lotto;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in)); // 입력
		bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력
		sb = new StringBuilder(); // sb 초기화
		String str = "";

		while ((str = br.readLine()) != null && str.length() > 0 && !str.equals("0")) {
			stk = new StringTokenizer(str);
			K = Integer.parseInt(stk.nextToken()); // 집합 안의 수 개수
			S = new int[K]; // 집합 S
			lotto = new int[6]; // 로또 6자리

			for (int i = 0; i < K; i++)
				S[i] = Integer.parseInt(stk.nextToken());

			getLotto(0, 0);
			sb.append("\n");

		}
		bw.append(sb);
		bw.flush(); // bw 총 출력
		br.close(); // 입력 종료
		bw.close(); // 출력 종료
	}

	static void getLotto(int index, int count) {
		if (count == 6) { // lotto 다 차면
			for (int i = 0; i < count; i++) {
				sb.append(lotto[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		if (index == K) { // 집합 S의 끝가지 가면
			return;
		}

		for (int i = index; i < K; i++) {
			lotto[count] = S[i];
			getLotto(i + 1, count + 1);
		}
	}

}
