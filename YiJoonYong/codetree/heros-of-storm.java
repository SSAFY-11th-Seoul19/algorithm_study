import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m, t;
    static int[][] map;
    static List<Coord> stormList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 방 row
        m = Integer.parseInt(st.nextToken());   // 방 col
        t = Integer.parseInt(st.nextToken());   // time

        map = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] < 0) {
                    stormList.add(new Coord(i, j)); // 0번째 인덱스가 윗 폭풍, 1번째 인덱스가 아랫 폭풍
                }
            }
        }

        while (t-- > 0) {
            splashDust();
            stormingTop();
            stormingBott();
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    ans+=map[i][j];
                }
            }
        }
        System.out.println(ans);
    }

    public static void stormingTop() {
        Coord cTop = stormList.get(0);
        int d = 0;
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        int nr = cTop.r-2;
        int nc = cTop.c;

        while (map[nr][nc] >= 0) {
            map[nr + dr[d] * -1][nc + dc[d] * -1] = map[nr][nc];

            if (isOutOfBound(nr+dr[d], nc+dc[d]) || nr+dr[d]-1 == cTop.r) {
                d++;
            }
            nr += dr[d];
            nc += dc[d];
        }
        map[cTop.r][cTop.c+1] = 0;
    }
    public static void stormingBott() {
        Coord cBott = stormList.get(1);

        int d = 0;
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        int nr = cBott.r+2;
        int nc = cBott.c;

        while (map[nr][nc] >= 0) {

            map[nr + dr[d] * -1][nc + dc[d] * -1] = map[nr][nc];
            if (isOutOfBound(nr+dr[d], nc+dc[d]) || nr+dr[d]+1 == cBott.r) {
                d++;
            }
            nr += dr[d];
            nc += dc[d];
        }
        map[cBott.r][cBott.c+1] = 0;
    }
    public static void splashDust() {
        int[][] splashMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] < 0) {    // 폭풍일 경우 해당 칸 확산 취소
                    continue;
                }

                int splashOffset = map[i][j] / 5;
                int splashCount = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = i+dr[d];
                    int nc = j+dc[d];

                    if (isOutOfBound(nr, nc) || isInsideStorm(nr, nc)) {
                        continue;
                    }

                    splashMap[nr][nc] += splashOffset;
                    splashCount++;
                }
                splashMap[i][j] += map[i][j] - (splashOffset * splashCount);
            }
        }

        splashMap[stormList.get(0).r][stormList.get(0).c] = -1;
        splashMap[stormList.get(1).r][stormList.get(1).c] = -1;
        map = splashMap;
    }

    public static boolean isOutOfBound(int r, int c) {
        return r<0 || n<=r || c<0 || m<=c;
    }
    public static boolean isInsideStorm(int r, int c) {
        Coord cTop = stormList.get(0);
        Coord cBott = stormList.get(1);
        return r==cTop.r&&c==cTop.c || r==cBott.r&&c==cBott.c ;
    }

    static class Coord {
        int r, c;

        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
