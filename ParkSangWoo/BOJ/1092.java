import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stn;
	static StringBuilder sb = new StringBuilder();
	static int N, M, time, cnt;
	static int[] crane, box, cranePoint;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());

		crane = new int[N];
		stn = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			crane[i] = Integer.parseInt(stn.nextToken());
		}

		M = Integer.parseInt(br.readLine());

		box = new int[M];
		stn = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			box[i] = Integer.parseInt(stn.nextToken());
		}

		Arrays.sort(crane);
		Arrays.sort(box);
		isVisited = new boolean[M];
		if (box[M - 1] > crane[N - 1]) {
			bw.write(Integer.toString(-1));
			bw.flush();
			bw.close();
			br.close();
			return;
		}
		cranePoint = new int[N];
		Arrays.fill(cranePoint, M - 1);
		loop1: while (cnt < M) {
			for (int i = N - 1; i >= 0; i--) {
				for (int j = cranePoint[i]; j >= 0; j--) {
					if (crane[i] < box[j] || isVisited[j]) {
						cranePoint[i] = j - 1;
						continue;
					}
					isVisited[j] = true;
					cnt++;
					break;
				}
				if (cnt != M) {
					continue;
				}
				time++;
				break loop1;
			}
			time++;
		}

		sb.append(time);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
