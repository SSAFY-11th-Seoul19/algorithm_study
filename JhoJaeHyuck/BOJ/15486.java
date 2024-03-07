import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] T, V, dp;

    // N의 크기가 굉장히 크기 때문에 NlogN 안에 연산을 끝내야 하고,
    // 가치의 최대 series 문제이기 때문에 dp 문제임을 유추할 수 있다
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        // 걸리는 시간과 가치에 대한 1차원 배열 각각 선언
        T = new int[N+2];
        V = new int[N+2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+2];
        // 앞에서부터 시작하면 생각할게 너무 많은데,
        // 뒤에서부터 시작하면 끝나는 시간에 맞춰 최대치 계산하기가 편하다
        for (int i = N; i > 0; i--) {
            if (T[i] + i > N + 1) { // 퇴사 이후에 끝나면 계산할 필요 없음
                dp[i] = dp[i+1];
                continue;
            }
            // 다음 날 최대치 그대로 가져온 값 vs 현재 가치 + 기존 가치에 지금의 시간을 뺀 값
            dp[i] = Math.max(dp[i+1], V[i] + dp[i + T[i]]);
        }

        System.out.print(dp[1]);
    }
}
