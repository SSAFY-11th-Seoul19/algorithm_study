import java.util.*;
import java.io.*;

public class 17070 {

    static int N;
    static int[][] map;
    static Point[][] count;
    static class Point {
        long vertical = 0;
        long horizontal = 0;
        long across = 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 원본 맵
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 해당 인덱스까지 파이프가 도달할 수 있는 경우의 수들 저장
        count = new Point[N+1][N+1];
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=N; j++) {
                count[i][j] = new Point();
            }
        }

        count[1][2].horizontal = 1;

        for(int i=1; i<=N; i++) {
            for(int j=2; j<=N; j++) {
                if(map[i][j] == 1) continue;

                if(checkPosition(i+1, j)) {
                    count[i+1][j].vertical += count[i][j].vertical;
                    count[i+1][j].vertical += count[i][j].across;
                }

                if(checkPosition(i, j+1)) {
                    count[i][j+1].horizontal += count[i][j].horizontal;
                    count[i][j+1].horizontal += count[i][j].across;
                }

                if(checkPosition(i, j+1)
                        && checkPosition(i+1, j)
                        && checkPosition(i+1, j+1)) {
                    count[i+1][j+1].across += count[i][j].across;
                    count[i+1][j+1].across += count[i][j].vertical;
                    count[i+1][j+1].across += count[i][j].horizontal;

                }
            }
        }

        System.out.println(count[N][N].across + count[N][N].horizontal + count[N][N].vertical);
    }

    public static boolean checkPosition(int y, int x) {
        if (y <= 0 || x <= 0 || x > N || y > N)
            return false;

        if (map[y][x] == 1)
            return false;

        return true;
    }
}
