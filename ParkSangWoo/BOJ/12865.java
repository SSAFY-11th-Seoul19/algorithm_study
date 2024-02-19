import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stn;
	static List<int[]> list;
	static int N, K;
	static int[] thing;
	static int[][] table;

	public static void main(String[] args) throws IOException {
		stn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stn.nextToken());
		K = Integer.parseInt(stn.nextToken());
		list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			stn = new StringTokenizer(br.readLine());
			list.add(new int[] { Integer.parseInt(stn.nextToken()), Integer.parseInt(stn.nextToken()) });
		}

		table = new int[N + 1][K + 1];

		for (int j = K; j >= list.get(0)[0]; j--) {
			table[1][j] = list.get(0)[1];
		}
		for (int i = 2; i <= N; i++) {
			thing = list.get(i - 1);
			for (int j = K; j >= 1; j--) {
				if (j < thing[0]) {
					table[i][j] = table[i - 1][j];
					continue;
				}
				table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - thing[0]] + thing[1]);
			}
		}

		bw.write(Integer.toString(table[N][K]));
		bw.flush();
		bw.close();
	}

}
