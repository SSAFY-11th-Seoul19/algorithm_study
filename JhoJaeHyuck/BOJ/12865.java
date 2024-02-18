import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] W;
    static int[] V;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 무게 저장배열과 가치 저장배열을 따로 선언
        W = new int[N+1];
        V = new int[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+1][M+1];
        // Weight w / Stuff s
        // 가방의 무게가 w인 경우, s 물건을 넣을 지 말지 결정
        for (int w = 1; w <= M; w++) {
            for (int s = 1; s <= N; s++) {
              
                // 만약 총 공간보다 물건의 무게가 더 많이 나가면 이전 값 그대로 가져옴
                if (W[s] > w) {
                    dp[s][w] = dp[s-1][w];
                    continue;
                }
              
                // 그게 아니라면 지금 물건을 넣는 경우가 더 큰지 확인하고 갱신한다
                // max의 2번째가 이전 값에서 내 무게를 빼고 내 가치를 더한 것
                dp[s][w] = Math.max(dp[s-1][w], dp[s-1][w - W[s]] + V[s]);
            }
        }

        System.out.print(dp[N][M]);
    }
}
