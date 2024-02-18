import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// boj.12856.평범한 배낭
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;

    public static void main(String[] args) throws IOException {
        stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken()); // 물품의 수
        int K = Integer.parseInt(stk.nextToken()); // 버틸 수 있는 무게

        int[][] items = new int[N][2];
        for (int i=0; i<N; i++) {
            stk = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(stk.nextToken()); // 무게
            items[i][1] = Integer.parseInt(stk.nextToken()); // 가치
        }
        
        // 최대 제한 무게가 index 일 때 담을 수 있는 최대 가치
        int[] dp = new int[K+1];

        // 각각의 item을 순회히며 dp 갱신 (dp -첫 번째 item만 고려된 상태, 두 번째 item까지 고려된 상태, .. , N번째 item까지 고려된 상태)
        for (int i=0; i<N; i++) {
            int W = items[i][0]; // 아이템 무게
            int V = items[i][1]; // 아이템 가치
            for (int j=K; j>=W; j--) { // 한 번 선택한 아이템을 다시 선택할 수 없기 때문에 거꾸로 순회해야 한다
                dp[j] = Math.max(dp[j], dp[j-W]+V); // 해당 아이템을 선택하지 않았을 때보다 선택했을 때 가치가 더 크다면 dp 갱신
            }
        }

        System.out.println(dp[K]);
    }

}
