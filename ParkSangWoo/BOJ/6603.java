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
		StringBuilder sb = new StringBuilder();
		int n = 0;
		int[] arr;
		int[] ans;
		int cnt;

		while (true) {
			StringTokenizer stn = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stn.nextToken());
			if (n == 0) {
				break;
			}
			arr = new int[n];
			ans = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(stn.nextToken());
			} // arr 생성

			for (int i = 63; i < ((1 << n) - (1 << (n - 6)) + 1); i++) {
				cnt = 0;
				
				for (int col = 0; col < n; col++) {
					ans[col] = 0;
				}
				
				for (int j = 0; j < n; j++) { // 부분집합 애들 ans 배열에 넣기
					if ((i & (1 << j)) != 0) {
						ans[j] = arr[j];
						cnt++;
					}
				}
				if (cnt != 6) {
					continue;
				}
				for (int col = 0; col < n; col++) {
					if (ans[col] == 0) {
						continue;
					}
					sb.append(ans[col]).append(" ");
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
