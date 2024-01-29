import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// boj2468 - 안전 영역
public class Main {
    final static int SUBMERGED = 1, LIVE=0;
    static int partial = 0; // 부분의 수
    static int max_partial = -1; // 최대 부분의 수
    static int N;
    static int[][] board, visited, copiedBoard;
    static int[] dx={0,-1,0,1}, dy={1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        int MAX = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(stk.nextToken());
                if (board[i][j] > MAX) MAX = board[i][j];
            }
        }
        // end input

        for (int i=0; i<=MAX; i++) { // 아무 지역도 물에 잠기지 않을 수도 있다.
            partial = 0;
            solution(i);
            if (partial > max_partial) max_partial = partial;
        }
        System.out.println(max_partial);
    }

    static void solution(int height) {
        copiedBoard = new int[N][N];
        visited = new int[N][N]; // 기준 height이 달라질 때마다 초기화 필요
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] <= height) copiedBoard[i][j] = SUBMERGED;
                else copiedBoard[i][j] = LIVE;
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (visited[i][j] == 1) continue;
                if (copiedBoard[i][j] == SUBMERGED) continue;
                partial++;
                dfs(i, j);
            }
        }
    }

    static void dfs(int x, int y) {
        if (visited[x][y] == 1) return;
        visited[x][y] = 1;
        for (int w=0; w<4; w++) {
            int nx = x+dx[w], ny = y+dy[w];
            if (nx<0 || nx>=N || ny<0 || ny>=N) continue;
            if (visited[nx][ny] == 1) continue;
            if (copiedBoard[nx][ny] == SUBMERGED) continue;
            dfs(nx, ny);
        }
    }
}
