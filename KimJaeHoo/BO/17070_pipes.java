import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 누워있는거 0, 세워져있는거 1, 대각선으로 되어 있는거 2
 dfs를 활용해서 풀었다.
 체크해야 하는 좌표와 갈 수 있는 좌표가 현재 상태(놓여져 있는 모양)에 의해서 결정된다.
 중복되는 코드가 여러군데 나올 것 같아서 분리를 했다.
 우선 현재 상태에 대한 변수, 그리고 영역에 대한 체크, 이동을 분리해서 조합시키는 방식으로 진행했다.

 현재 누워있다면
    오른쪽으로 이동, 누워있는 상태 A)
    대각선 아래로 이동, 대각선 상태 B)
 현재 세워져 있다면
    대각선 아래로 이동, 대각선 상태 B)
    아래로 이동, 세워져 있는 상태 C)
 현재 대각선이라면
    오른쪽으로 이동, 누워있는 상태 A)
    대각선 아래로 이동, 대각선 상태 B)
    아래로 이동, 세워져 있는 상태 C)

 그래서 대신에 플래그를 활용해서 다음과 같은 흐름으로 고쳤다.
    현재 누워있거나 대각선인 경우
        오른쪽으로 이동, 누워있는 상태 A)
    모든 경우
        대각선 아래로 이동, 대각선 상태 B)
    현재 세워져 있거나 대각선인 경우
        아래로 이동, 세워져 있는 상태 C)
 미리 갈 수 있는 지 확인하는것도 추가함.
 
 * */

public class Main {
    static int N;
    static int ans;
    static int[] dR = {0, 1, 1};
    static int[] dC = {1, 0, 1};
    static int[][] graph;

    public static void solve(int curR, int curC, int type){
        if(curR == N && curC == N){
            ans++;
            return;
        }

        int newR, newC;
        boolean[] checks = {false, false, false};

        for(int i = 0; i < 3 ; i++){
            newR = curR + dR[i];
            newC = curC + dC[i];
            if(newR > N || newC > N)
                continue;
            if(graph[newR][newC] == 1)
                continue;
            checks[i] = true;
        }

        if((type == 0 || type == 2) && checks[0])
            solve(curR, curC +1 , 0);
        if((type == 1 || type == 2) && checks[1])
            solve(curR + 1, curC , 1);
        if(checks[0] && checks[1] && checks[2])
            solve(curR + 1, curC + 1, 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        graph = new int[N+1][N+1];


        for(int i = 1 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(1, 2, 0);
        System.out.println(ans);
    }
}
