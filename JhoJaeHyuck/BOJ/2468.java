import java.util.*;
import java.io.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        int maxHeight = -1;
        int minHeight = 101;
        for (int i = 0; i < N; i++) {
            String[] token = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(token[j]);
                if (arr[i][j] > maxHeight) maxHeight = arr[i][j];
                if (arr[i][j] < minHeight) minHeight = arr[i][j];
            }
        }

        int ans = 1;
        for (int height = minHeight; height < maxHeight; height++) {
            boolean[][] v = new boolean[N][N];

            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (v[i][j] || arr[i][j] <= height) continue;

                    v[i][j] = true;
                    count++;

                    Queue<Point> q = new LinkedList<>();
                    q.add(new Point(i, j));

                    while (!q.isEmpty()) {
                        Point cp = q.poll();
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cp.x + dx[dir];
                            int ny = cp.y + dy[dir];

                            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                            if (v[nx][ny] || arr[nx][ny] <= height) continue;

                            v[nx][ny] = true;
                            q.add(new Point(nx, ny));
                        }
                    }
                }
            }

            if (count > ans) ans = count;
        }

        System.out.print(ans);
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
