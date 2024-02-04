import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[] lotto, arr;
    static int N;

    static void backtrack(int idx, int cnt) {
        if (cnt == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(lotto[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < N; i++) {
            lotto[cnt] = arr[i];
            backtrack(i+1, cnt+1);
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            if (N == 0) break;

            arr = new int[N];
            lotto = new int[6];

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            backtrack(0, 0);

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
