// 양쪽 포인터를 사용해서 중간에 가까워지도록 서서히 좁혀나가는 방식
// start와 end 중 사전순으로 더 빠른 문자열로 집어넣기
// 문자가 같다면 다른 문자가 나올 때까지, 그 이전 또는 이후의 문자끼리 비교

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] stringS = new String[n];
		String stringT = "";
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stringS[i] = st.nextToken();
		}
		int start = 0;
		int end = n - 1;
		int size = 0; // stringT의 개행 문자를 제외한 글자수 변수
		while (start <= end) {
			String s_start = stringS[start];
			String s_end = stringS[end];
			if (s_start.compareTo(s_end) < 0) { // s_start가 사전순으로 앞서면
				stringT += stringS[start++];
			} else if (s_start.compareTo(s_end) > 0) { // s_end가 사전순으로 앞서면
				stringT += stringS[end--];
			} else { // 두 문자가 같으면
				int left = start;
				int right = end;
				boolean check = false; // 두 문자의 다름을 체크하는 변수
				while (left <= right) {
					if (stringS[left].compareTo(stringS[right]) < 0) {
						stringT += stringS[start++];
						check = true;
						break;
					} else if (stringS[left].compareTo(stringS[right]) > 0) {
						stringT += stringS[end--];
						check = true;
						break;
					} else {
						left++;
						right--;
					}
				}
				if (!check) {
					stringT += stringS[start++];
				}
			}
			size++;
			if (size % 80 == 0) {
				stringT += "\n";
			}
		}
		System.out.println(stringT);
	}
}
