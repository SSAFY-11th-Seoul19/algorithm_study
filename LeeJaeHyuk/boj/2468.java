import java.util.*;
import java.io.*;

public class Study2 {

    static class Point {
        int y, x;
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for(int water=0; water<=100; water++) {
            boolean[][] visited = new boolean[N+1][N+1];

            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(map[i][j] <= water) {
                        visited[i][j] = true;
                    }
                }
            }

            int tempCount = 0;
            int[] dy = {-1, 0, 1, 0};
            int[] dx = {0, 1, 0, -1};

            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(visited[i][j] == false) {
                        Queue<Point> queue = new LinkedList<>();
                        queue.add(new Point(i, j));
                        visited[i][j] = true;

                        while(!queue.isEmpty()) {
                            Point p = queue.poll();

                            for(int k=0; k<4; k++) {
                                int nextY = p.y + dy[k];
                                int nextX = p.x + dx[k];

                                if(nextX < 1 || nextY < 1 || nextX > N || nextY > N) continue;

                                if(visited[nextY][nextX] == false) {
                                    visited[nextY][nextX] = true;
                                    queue.add(new Point(nextY, nextX));
                                }
                            }
                        }
                        tempCount += 1;
                    }

                }
            }

            count = Math.max(count, tempCount);

        }

        System.out.println(count);
    }
}
