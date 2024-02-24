import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// boj.17070.파이프옮기기1
public class Main {
    static final int GARO=0, SAERO=1, DAEGAK=2;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static int N, cnt;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for(int i=0; i<N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        cnt = 0;
        dfs(0,1,GARO); // 파이프 끝 지점(0,1)만 생각, 방향은 가로
        System.out.println(cnt);
    }

    static void dfs(int x, int y, int dir) {
        if (x == N-1 && y == N-1) {
            // 도착한 것
            cnt++;
            return;
        }

        switch (dir) {
            case GARO: // 파이프가 가로 방향일 경우, 갈 수 있는 경우는 가로, 대각선
                if (y + 1 < N && board[x][y + 1] == 0) { // 가로
                    dfs(x, y + 1, GARO);
                }
                break;
            case SAERO: // 파이프가 세로 방향일 경우, 갈 수 있는 경우는 세로, 대각선
                if (x + 1 < N && board[x + 1][y] == 0) { // 세로
                    dfs(x + 1, y, SAERO);
                }
                break;
            case DAEGAK: // 파이프가 대각선일 경우, 갈 수 있는 경우는 가로, 세로, 대각선
                if (y + 1 < N && board[x][y + 1] == 0) { // 가로
                    dfs(x, y + 1, GARO);
                }
                if (x + 1 < N && board[x + 1][y] == 0) { // 세로
                    dfs(x + 1, y, SAERO);
                }
                break;
        }
        // 파이프가 어떤 방향이든지, 대각선은 공통으로 갈 수 있어서 아래로 뺐음
        if (y + 1 < N && x + 1 < N && board[x][y + 1] == 0 && board[x + 1][y] == 0 && board[x + 1][y + 1] == 0) {
            dfs(x + 1, y + 1, DAEGAK);
        }
    }
}

