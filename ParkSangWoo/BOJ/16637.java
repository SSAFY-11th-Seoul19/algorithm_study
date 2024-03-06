import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.io.IOException;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int N, operNum;
	static String[] str;
	static boolean[] isVisited;
	static Queue<Object> queue = new ArrayDeque<>();
	static long ans;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		operNum = N / 2;
		isVisited = new boolean[operNum];

		str = new String[N];
		str = br.readLine().split("");

		ans = Integer.MIN_VALUE;
		subSet(0);

		bw.write(Long.toString(ans));
		bw.flush();
		bw.close();
		br.close();
	}

	public static void subSet(int cnt) {
		if (cnt == operNum || cnt == operNum + 1) {
			String[] string = Arrays.copyOf(str, N);
			for (int i = 0; i < operNum; i++) {
				if (!isVisited[i]) {
					continue;
				}
				string[2 * i] = Long.toString(
						arithmetic(Integer.parseInt(str[2 * i]), str[2 * i + 1], Integer.parseInt(str[2 * i + 2])));
				string[2 * i + 1] = " ";
				string[2 * i + 2] = " ";
			}
			for (int i = 0; i < N; i++) {
				if (string[i].equals(" ")) {
					continue;
				}
				if (i % 2 == 0) {
					queue.add(Long.parseLong(string[i]));
					continue;
				}
				queue.add(string[i]);
			}
			ans = Math.max(arithmeticQue(queue), ans);
			return;
		}

		isVisited[cnt] = true;
		subSet(cnt + 2);
		isVisited[cnt] = false;
		subSet(cnt + 1);
	}

	public static long arithmetic(long a, String oper, long b) {
		if (oper.equals("+")) {
			return a + b;
		}
		if (oper.equals("-")) {
			return a - b;
		}
		if (oper.equals("*")) {
			return a * b;
		}
		return 0;
	}

	public static long arithmeticQue(Queue que) {
		int size = que.size() / 2;
		long a = (long) que.poll();

		for (int i = 0; i < size; i++) {
			a = arithmetic(a, (String) que.poll(), (long) que.poll());
		}
		return a;
	}
}
