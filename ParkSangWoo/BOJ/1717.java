import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stn;
	static int n, m, parent;
	static int[] arrCheck;

	public static void main(String[] args) throws IOException {
		stn = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stn.nextToken());
		m = Integer.parseInt(stn.nextToken());
		arrCheck = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			arrCheck[i] = i;
		}

		for (int i = 0; i < m; i++) {
			stn = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(stn.nextToken());
			int a = Integer.parseInt(stn.nextToken());
			int b = Integer.parseInt(stn.nextToken());

			if (type == 0) {
				union(a, b);
				continue;
			}
			sb.append(find(a, b)).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void union(int i, int j) {
		findParent(i);
		int A = parent;
		findParent(j);
		int B = parent;
		arrCheck[B] = arrCheck[A];
	}

	public static void findParent(int i) {
		if (arrCheck[i] == i) {
			parent = i;
			return;
		}
		findParent(arrCheck[i]);
		arrCheck[i] = parent;
	}

	public static String find(int i, int j) {
		findParent(i);
		int A = parent;
		findParent(j);
		int B = parent;
		if (arrCheck[A] != arrCheck[B]) {
			return "NO";
		}
		return "YES";
	}
}
