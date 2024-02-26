import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int V = 0;
    private static final int H = 1;
    private static final int D = 2;

    static int n;
    static int[][] graph;
    static int[][][] dp;
    static int[][] moves = new int[][]{{1, 0}, {0, 1}, {1, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[3][n][n];
        dp[H][0][1] = 1;
        for (int i = 2; i < n; i++) {
            if (graph[0][i] == 1) {
                break;
            }
            dp[H][0][i] = 1;
        }

        for (int y = 1; y < n; y++) {
            for (int x = 0; x < n; x++) {
                // 현재 위치가 벽일 경우 논외
                if (graph[y][x] == 1) {
                    continue;
                }
                // 가로, 세로, 대각선 -> 대각선
                if (x > 0 && graph[y - 1][x] == 0 && graph[y][x - 1] == 0 && graph[y - 1][x - 1] == 0) {
                    for (int d = 0; d < 3; d++) {
                        dp[D][y][x] += dp[d][y - moves[D][0]][x - moves[D][1]];
                    }
                }
                // 가로, 대각선 -> 가로
                if (x > 0 && graph[y][x - 1] == 0) {
                    dp[H][y][x] += dp[H][y - moves[H][0]][x - moves[H][1]];
                    dp[H][y][x] += dp[D][y - moves[H][0]][x - moves[H][1]];
                }
                // 세로 -> 세로
                if (y > 0 && graph[y - 1][x] == 0) {
                    dp[V][y][x] += dp[V][y - moves[V][0]][x - moves[V][1]];
                    dp[V][y][x] += dp[D][y - moves[V][0]][x - moves[V][1]];
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 3; i++) {
            answer += dp[i][n - 1][n - 1];
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
