import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

// boj14503 - 로봇청소기
public class Main {
    static final int DIRTY=0, WALL=1, CLEAN=2;
    static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1}; // 북 동 남 서
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static int N, M;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(stk.nextToken());
        int y = Integer.parseInt(stk.nextToken());
        int d = Integer.parseInt(stk.nextToken());

        board = new int[N][M];
        for (int i=0; i<N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        // end input

        int cnt = 0;
        play : while (true) {
            // 현재 칸이 더러우면 청소
            if (board[x][y] == DIRTY) {
                board[x][y] = CLEAN;
                cnt++;
                continue;
            }
            // 깨끗하면 주변 4칸 중 더러운 곳이 있는지 확인
            int nx, ny;
            if (isDirty4Way(x, y)) {
                // 있다면 반시계 90도 회전 -> 더러우면 -> 한 칸 전진 // 안더러우면 또 90도 회전
                while (true) {
                    d = rotate(d);
                    nx = x + dx[d];
                    ny = y + dy[d];
                    if (board[nx][ny] == DIRTY) break;
                }

            } else {
                // 없다면 한 칸 후진(방향은 같고 이전칸으로)
                int back_dir = back(d);
                nx = x + dx[back_dir];
                ny = y + dy[back_dir];
                // 후진 불가하면 break
                if (nx<0 || nx>=N || ny<0 || ny>=M) break play;
                if (board[nx][ny] == WALL) break play;
            }
            // 현재 위치 갱신 후 while문 처음으로 되돌아가기
            x = nx;
            y = ny;
        }
        System.out.println(cnt);
    }


    // 주변 네 칸에 더러운 곳이 있는지 확인
    static boolean isDirty4Way(int x, int y) {
        for (int w=0; w<4; w++) {
            int nx = x + dx[w];
            int ny = y + dy[w];
            if (nx<0 || nx>=N || ny<0 || ny>=M) continue;
            if (board[nx][ny] == DIRTY) return true;
        }
        return false;
    }

    // 후진 방향
    static int back(int dir) {
        // 북(0)->남(2), 동(1)->서(3), 남(2)->북(0), 서(3)->동(1)
        return (dir + 2) % 4;
    }

    // 반시계 90도 회전한 방향
    static int rotate(int dir) {
        // 북(0)->서(3), 동(1)->북(0), 남(2)->동(1), 서(3)->남(2)
        return (dir + 3) % 4;
    }

}
