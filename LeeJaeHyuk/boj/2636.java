import java.util.*;
import java.io.*;

public class Study2 {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int R, C;
    static int[][] map;

    static class Point {
        int y, x;
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int r=0; r<R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int count = 0;

        while(!isEmpty()) {
            count = 0;
            //
            for(int r=0; r<R; r++) {
                for(int c=0; c<C; c++) {
                    if(map[r][c] == -1) {
                        map[r][c] = 0;
                    }
                }
            }
            //
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(0, 0));
            map[0][0] = -1;

            while(!queue.isEmpty()) {
                int size = queue.size();

                for(int i=0; i<size; i++) {
                    Point p = queue.poll();

                    for(int j=0; j<4; j++) {
                        int nextY = p.y + dy[j];
                        int nextX = p.x + dx[j];

                        if(!check(nextY, nextX)) continue;

                        if(map[nextY][nextX] == 0) {
                            map[nextY][nextX] = -1;
                            queue.add(new Point(nextY, nextX));
                        }
                        else if(map[nextY][nextX] == 1) {
                            map[nextY][nextX] = 0;
                            map[nextY][nextX] = -1;
                            count += 1;
                        }
                    }
                }
            }
            time += 1;

//			System.out.println("============================");
//			for(int x=0; x<R; x++) {
//				for(int y=0; y<C; y++) {
//					System.out.print(map[x][y] + " ");
//				}
//				System.out.println();
//			}
        }
        System.out.println(time);
        System.out.println(count);

    }

    public static boolean check(int y, int x) {
        if(y >= 0 && y < R && x >= 0 && x < C) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty() {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] == 1)
                    return false;
            }
        }
        return true;
    }
}
