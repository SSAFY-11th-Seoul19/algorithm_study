import java.util.*;
import java.io.*;

public class Study1 {
    static int H, N, M;
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int[] dy = {1, 0, -1, 0 ,0, 0};
    static int[] dx = {0, 1, 0, -1, 0, 0};

    static class Point {
        int h, y, x;
        Point(int h, int y, int x) {
            this.h = h;
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        Queue<Point> queue = new LinkedList<>();
        int[][][] map = new int[H][N][M];

        for(int h=0; h<H; h++) {
            for(int y=0; y<N; y++) {
                st = new StringTokenizer(br.readLine());
                for(int x=0; x<M; x++) {
                    map[h][y][x] = Integer.parseInt(st.nextToken());
                    if(map[h][y][x] == 1) {
                        queue.add(new Point(h,y,x));
                        map[h][y][x] = -1;
                    }
                }
            }
        }

        int count = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                Point p = queue.poll();

                for(int j=0; j<6; j++) {
                    int nextH = p.h + dz[j];
                    int nextY = p.y + dy[j];
                    int nextX = p.x + dx[j];

                    if(check(nextH, nextY, nextX) && map[nextH][nextY][nextX] == 0) {
                        queue.add(new Point(nextH, nextY, nextX));
                        map[nextH][nextY][nextX] = -1;
                    }
                }
            }
            count += 1;
        }

        for(int h=0; h<H; h++) {
            for(int y=0; y<N; y++) {
                for(int x=0; x< M; x++) {
                    if(map[h][y][x] == 0) {
                        count = -1;
                    }
                }
            }
        }

        System.out.println(count);
    }

    public static boolean check(int z, int y, int x) {
        if(z >= 0 && z < H && y >= 0 && y < N && x >= 0 && x < M)
            return true;
        return false;
    }
}