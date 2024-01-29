import java.util.*;
import java.io.*;

class Main {
    static int N, M, H;
    static int ans;
    static int tomato;

    static Queue<Point> q = new LinkedList<>();
    static int[][][] arr;
    static boolean[][][] v;

    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        M = Integer.parseInt(str[0]);
        N = Integer.parseInt(str[1]);
        H = Integer.parseInt(str[2]);

        arr = new int[H][N][M];
        v = new boolean[H][N][M];
        tomato = 0;

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                str = br.readLine().split(" ");
                for (int m = 0; m < M; m++) {
                    int t = Integer.parseInt(str[m]);

                    switch (t) {
                        case 1:
                            q.add(new Point(h, n, m));
                            v[h][n][m] = true;
                            arr[h][n][m] = 0;
                            break;
                        case 0:
                            arr[h][n][m] = 0x7FFFFFFF;
                            tomato++;
                            break;
                        case -1:
                            arr[h][n][m] = -1;
                            break;
                    }
                }
            }
        }

        ans = 0;
        if (tomato == 0) {
            System.out.println(ans);
            return;
        }

        while (!q.isEmpty()) {
            Point p = q.poll();
            int cx = p.x;
            int cy = p.y;
            int cz = p.z;

            for (int dir = 0; dir < 6; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                int nz = cz + dz[dir];

                if (nx < 0 || nx >= H || ny < 0 || ny >= N || nz < 0 || nz >= M) continue;
                if (v[nx][ny][nz] || arr[nx][ny][nz] == -1) continue;

                v[nx][ny][nz] = true;
                arr[nx][ny][nz] = arr[cx][cy][cz] + 1;
                q.add(new Point(nx, ny, nz));
                tomato--;
                ans = Math.max(ans, arr[nx][ny][nz]);
            }
        }

        if (tomato != 0) System.out.println(-1);
        else System.out.println(ans);
    }

    static class Point {
        int x;
        int y;
        int z;
        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
