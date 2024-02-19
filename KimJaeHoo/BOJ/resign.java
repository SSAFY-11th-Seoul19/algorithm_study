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
 */

public class Main {
    static int N;
    static int[] times, values;
    static int[] dp;

    public static int solve(int item, int curEndTime){
        if(dp[curEndTime] != -1)
            return dp[curEndTime];
        if(item == N+1) // 모든 아이템 선택이 끝난경우
            return 0;
        if(item <= curEndTime) // 현재 날짜에 이미 다른 상담이 잡혀있는경우
            return dp[curEndTime] = solve(item+1, curEndTime);
        if(item + times[item] - 1 > N) // 현재 날짜의 상담이 퇴사일 이후에 끝나는 경우
            return dp[curEndTime] = solve(item+1, curEndTime);

        return dp[curEndTime] = Integer.max(solve(item+1, curEndTime), values[item] + solve(item+1, item + times[item] - 1 ));

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());

        times = new int[N+1];
        values = new int[N+1];
        dp = new int[N+1];
        for(int i = 0; i <= N; i++){
            dp[i] = -1;
        }

        for(int i = 1 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve(1, 0));
//        System.out.println(dp[N][N]);
    }
}
