import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 일자 + 소요일 = 마감일과 이득 고려
 *
 * 일자별로 순서대로 일이 주어진다.
 * 현재 상담이 진행중이면 일을 고를 수 없다.
 *
 * 선택해야하는 날과 종료되는 날짜를 받아서 가치를 리턴하도록
 * dp[i] 은 i일 이전까지의 최대 이득을 의미한다.
 */

public class Main {
    static int N;
    static int[] times, values;
    static int[] dp;

    public static void solve(){
        int max = dp[0];
        // N일을 모두 고려하면서
        for(int i = 1; i <= N; i++){
            // 종료일 + 1, 인덱스 이전까지의 최대값을 의미하기 때문에 -1을 해주지 않는다
            int endTime = i + times[i];
            // 최대값 계속 갱신. 이것이 핵심이었다. i번째 일의 상담을 선택하지 않았을 때의 최대값은 배열에서 ~i 최대값이다. ~i에서 테이블 내용이 채워진 곳 중에서 가장 오른쪽에 있는 값이 아니라.
            if(max < dp[i]) max = dp[i];
            if(endTime > N+1) continue;
            // 그래서 해당 작업을 추가할 때 조건에 맞도록 오늘 이전의 값들 중에서 최대값 또는 해당 빼고 최대값
            dp[endTime] = Integer.max(max + values[i], dp[endTime]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());

        times = new int[N+1];
        values = new int[N+1];
        dp = new int[N+2];

        for(int i = 1 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }
        solve();
        int ans = 0;
        for(int i =0 ; i <= N+1; i++){
            if(ans < dp[i]) ans = dp[i];
        }
        System.out.println(ans);
    }
}
