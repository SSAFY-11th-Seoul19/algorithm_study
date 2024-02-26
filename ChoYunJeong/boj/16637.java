/*
 * 중첩된 괄호는 사용X => 괄호가 존재하지 않아도 됨
 * 괄호 안에는 연산자 하나!
 * 연산자 우선순위는 모두 동일 => 곱하기가 먼저가 아니다..! => 문제를,,제대로,,읽자,,
 * 나누기 연산X => 0으로 나누는 경우 or 소수 문제X
 * 최댓값을 구하므로, 브루트포스 알고리즘~>
 */

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N; // 수식의 개수
	static int max = Integer.MIN_VALUE; // 최댓값 => 초기화는 최솟값
	static ArrayList<Integer> nums = new ArrayList<>();
	static ArrayList<Character> operator = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0)
				nums.add(input[i] - '0');
			else
				operator.add(input[i]);
		}

		dfs(0, nums.get(0)); // 0번째 index와 초기 합(계산 전이므로 처음 숫자)
		System.out.println(max);
	}

	// index로 숫자와 연산자의 index 처리를 한번에 함 => index 범위 잘 체크
	public static void dfs(int index, int sum) {
		if (index >= N / 2) { // 연산자+숫자의 합이 N이므로, 절반 이상 => 연산 없음
			max = Math.max(sum, max); // 최댓값 갱신
			return;
		}
		// 괄호를 안 친 상태로 index만 증가 => 현재 연산을 처리하고 처리한 값을 sum으로 넘김
		dfs(index + 1, calculate(index, sum, nums.get(index + 1)));
		// 괄호를 친 상태로 계산
		if (index + 1 < N / 2) { // 괄호를 치고 계산할 값이 뒤에 더 있다면
			// 현재 숫자와 연산자는 두고, 다음 연산자와 다음 숫자와 다다음 숫자를 계산 => 괄호를 친 것
			int num = calculate(index + 1, nums.get(index + 1), nums.get(index + 2));
			// 괄호를 쳐서 미리 연산했으므로 index+2, 현재 숫자와 미리 연산한 값을 더해서 sum으로 넘김
			dfs(index + 2, calculate(index, sum, num));
		}
	}

	// char로 되어있는 연산자에 따른 사칙연산 수행
	public static int calculate(int index, int a, int b) {
		char op = operator.get(index);
		if (op == '+') {
			return a + b;
		}
		if (op == '*') {
			return a * b;
		}
		if (op == '-') {
			return a - b;
		}
		return 0;
	}

}
