import java.util.*;
import java.io.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        int N, M;

        String[] token = br.readLine().split(" ");
        N = Integer.parseInt(token[0]);
        M = Integer.parseInt(token[1]);

        int[][] arr = new int[N][M];

        // Air = 0, Cheese = 1
        int cheese = 0;
        for (int i = 0; i < N; i++) {
            token = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(token[j]);
                if (arr[i][j] == 1) cheese++;
            }
        }

        int count = 0; // how much cheese melt in a time
        int time = 0;
        while (cheese > 0) {
            count = 0;
            time++;

            Queue<Point> q = new LinkedList<>();
            boolean[][] v = new boolean[N][M];

            // Point(0, 0) is always a hole
            q.add(new Point(0, 0));
            v[0][0] = true;

            // BFS
            while (!q.isEmpty()) { // until all cheese melt
                Point cp = q.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cp.x + dx[dir];
                    int ny = cp.y + dy[dir];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if (v[nx][ny]) continue;

                    v[nx][ny] = true;
                    if (arr[nx][ny] == 0) q.add(new Point(nx, ny));
                    else { // if cheese, it melts
                        cheese--;
                        count++;
                        arr[nx][ny] = 0; // make it into a hole
                    }
                }
            }
        }

        System.out.println(time);
        System.out.print(count);
    }

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
