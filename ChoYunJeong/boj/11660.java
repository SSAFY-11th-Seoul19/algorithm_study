import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력
		StringBuilder sb = new StringBuilder(); // 출력을 저장할 sb
		StringTokenizer st = new StringTokenizer(br.readLine()); // br로 N과 M 읽어오기
		int N = Integer.parseInt(st.nextToken()); // 수의 개수 N 입력
		int M = Integer.parseInt(st.nextToken()); // 합의 횟수 M 입력
		int[][] nums = new int[N][N + 1]; // 수를 받을 배열
		int tmp = 0; // 누적합 저장을 위한 변수

		// nums로 입력 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); // 한 줄 저장 == 한 행
			for (int j = 1; j <= N; j++) {
				nums[i][j] = tmp + Integer.parseInt(st.nextToken());
				tmp = nums[i][j];
			}
			tmp = 0;
		}
		// 출력 추가하기
		for (int count = 0; count < M; count++) {
			int sum = 0;
			int sub = 0;
			st = new StringTokenizer(br.readLine()); // br로 st에 i번째와 j번째 수 받기
			int x1 = Integer.parseInt(st.nextToken()); // 시작 x 받기
			int y1 = Integer.parseInt(st.nextToken()); // 시작 y 받기
			int x2 = Integer.parseInt(st.nextToken()); // 종료 x 받기
			int y2 = Integer.parseInt(st.nextToken()); // 종료 y 받기

			for (int i = x1; i <= x2; i++) {
				sum = sum + nums[i - 1][y2] - nums[i-1][y1-1];
			}
			sb.append(sum).append("\n");
		}

		bw.append(sb); // bw에 sb 저장
		bw.flush(); // bw 출력
		br.close(); // 입력 종료
		bw.close(); // 출력 종료
	}

}
