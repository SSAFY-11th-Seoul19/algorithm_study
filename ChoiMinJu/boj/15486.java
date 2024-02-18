import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// boj.15486.퇴사2
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static int N;
    static int[][] tasks;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        tasks = new int[N+1][2];
        for (int i=0; i<N; i++) {
            stk = new StringTokenizer(br.readLine());
            tasks[i][0] = Integer.parseInt(stk.nextToken());
            tasks[i][1] = Integer.parseInt(stk.nextToken());
        }

        // 마지막 날(N-1일)에 만약 T=1 이라면 일을 할 수 있기 때문에 dp 배열을 N번째 인덱스까지 받아야 한다
        int[] dp = new int[N+1]; // index 일에 얻을 수 있는 최대 금액
        int max = 0; // i일까지의 최대 수익

        for (int i=0; i<=N; i++) { // N까지 포함
            int T = tasks[i][0], P = tasks[i][1];
            max = Math.max(dp[i], max); // i일까지의 수익(dp[i])이 최대일 때 max값 갱신
            int next = i + T; // i일에 일을 하면 끝나는 날짜
            if (next <= N) { // 다 끝낼 수 있는 일이라면
                // 상담 끝나는 날짜에 '현재 날짜에 기록된 금액' 과 '현재 날짜까지의 최대 금액 + 현재 상담 금액' 중 더 큰 금액으로 갱신
                dp[next] = Math.max(dp[next], max + P);
            }
        }

        System.out.println(dp[N]); // 최대 금액
    }
}

