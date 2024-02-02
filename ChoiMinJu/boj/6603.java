import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// boj6603 - 로또
public class Main {
    static final int LOTTO_SIZE = 6;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer stk;
    static int[] allNums, res;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        while (true) {
            stk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stk.nextToken());

            if (N == 0) {
                break;
            }

            allNums = new int[N];
            visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                allNums[i] = Integer.parseInt(stk.nextToken());
            }

            res = new int[LOTTO_SIZE];
            dfs(0, 0);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int L, int start) {
        if (L == LOTTO_SIZE) {
            for (int i=0; i<LOTTO_SIZE; i++) {
                sb.append(res[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
      
        for (int i=start; i<N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            res[L] = allNums[i];
            dfs(L+1, i+1);
            visited[i] = false;
        }
    }


}
