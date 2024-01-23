// 양쪽 포인터를 사용해서 0에 가까워지도록 서서히 좁혀나가는 방식
// start와 end의 합이 작아지면 min 갱신

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] portion = new int[n];
		for (int i = 0; i < n; i++) {
			portion[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0;
		int end = n - 1;
		int min = Integer.MAX_VALUE;
		while (true) {
			if (start == end) {
				break;
			}
			int sum = portion[start] + portion[end];
			if (Math.abs(sum) <= Math.abs(min)) { // min에는 절댓값이 아닌 값이 저장되어있으므로, 비교할 땐 절댓값으로 치환
				min = sum;
			}
			if (sum < 0) {
				start++;
			} else {
				end--;
			}
			
		}
		System.out.println(min);
	}
}
