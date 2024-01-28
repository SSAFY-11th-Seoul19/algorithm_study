import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// boj7569 - 토마토
public class Main {
    static final int RIPENED = 1, UNRIPENED = 0, EMPTY = -1;
    static int[] dx = {0,0,-1,0,1}, dy = {0,1,0,-1,0}, dz = {1,-1};
    static int[][][] tomatoes, visited;
    static int M, N, H;
    static int cycle = -1; // 한 사이클
    //    static int unRipenedCnt = 0; // 익지않은 토마토 수
    static LinkedList<int[]> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        M = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        H = Integer.parseInt(stk.nextToken());

        tomatoes = new int[N][M][H];
        visited = new int[N][M][H];
        for (int k=0; k<H; k++) {
            for (int i = 0; i < N; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    tomatoes[i][j][k] = Integer.parseInt(stk.nextToken());
                }
            }
        }
        // end input

        for (int k=0; k<H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tomatoes[i][j][k] == RIPENED) {
                        que.add(new int[]{i,j,k}); // 초기 큐 세팅
                    }
//                    else if (tomatoes[i][j][k] == UNRIPENED) { // 익지 않은 토마토 수
//                        unRipenedCnt++;
//                    }
                }
            }
        }

        if (isFullyRipen()) {
            System.out.println("0"); // 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력
            return;
        }

        bfs();

        if (isFullyRipen()) System.out.println(cycle);
        else System.out.println("-1");
    }

    static void bfs() {
        while (!que.isEmpty()) {
            int qSize = que.size(); // 한 싸이클을 측정하기 위해
            while (qSize > 0) {
                int[] cPos = que.removeFirst();
                visited[cPos[0]][cPos[1]][cPos[2]] = 1;

                for (int w = 0; w < 5; w++) { // way
                    int nx = cPos[0] + dx[w];
                    int ny = cPos[1] + dy[w];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    int nz;
                    if (w == 0) { // 위 아래 탐방
                        for (int v = 0; v < 2; v++) { // vertical
                            nz = cPos[2] + dz[v];
                            if (nz < 0 || nz >= H) continue;
                            if (visited[nx][ny][nz] == 1) continue;
                            if (tomatoes[nx][ny][nz] == EMPTY) continue; // 토마토가 없으면 전달 못함
                            visited[nx][ny][nz] = 1;
                            tomatoes[nx][ny][nz] = RIPENED; // 익음
//                            unRipenedCnt--;
                            que.add(new int[]{nx, ny, nz});
                        }
                    } else {
                        nz = cPos[2];
                        if (visited[nx][ny][nz] == 1) continue;
                        if (tomatoes[nx][ny][nz] == EMPTY) continue; // 토마토가 없으면 전달 못함
                        visited[nx][ny][nz] = 1;
                        tomatoes[nx][ny][nz] = RIPENED; // 익음
//                        unRipenedCnt--;
                        que.add(new int[]{nx, ny, nz});
                    }
                }
                qSize--;
            }
            cycle++;
        }

    }

    // 모든 토마토가 익었는지
    static boolean isFullyRipen() {
        for (int k=0; k<H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tomatoes[i][j][k] == 0) return false;
                }
            }
        }
        return true;
    }
}
