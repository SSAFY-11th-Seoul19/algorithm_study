import java.util.*;
import java.io.*;

public class 14503 {
    static int[][] map;
    static int N, M;
    static int startY, startX, startD;

    // 상 우 하 좌
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    // 7시 20분 ~ 7시 43분
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 0 -> 청소되지않은 빈칸, 1-> 벽
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        startY = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());
        startD = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        map[startY][startX] = 2;

        while(true) {
            System.out.println("현재: y: " + startY + ", x: " + startX);
            // 근처에 청소해야하는 빈칸이 있는 경우
            if(haveToCleanZone(startY, startX)) {
                // 반시계 90도 회전
                startD = (startD + 3) % 4;
                // 바라보는 방향을 기준으로 앞쪽 같이 청소되어있지 않은 빈칸이면 전진 -> 1번
                int nextY = startY + dy[startD];
                int nextX = startX + dx[startD];
                if(check(nextY, nextX) && map[nextY][nextX] == 0) {
                    map[nextY][nextX] = 2;
                    startY = nextY;
                    startX = nextX;
                }

            }
            // 근처에 청소해야하는 빈칸이 없는 경우
            else {
                // 바라보는 방향 유지한채 한칸 후진 -> 1번
                int nextY = startY - dy[startD];
                int nextX = startX - dx[startD];

                // 후진했는데 벽이면 반복문 탈출
                if(check(nextY, nextX) && map[nextY][nextX] == 1) {
                    break;
                }
                else {
                    startY = nextY;
                    startX = nextX;
                }
            }
        }

        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 2) {
                    count+=1;
                }
            }
        }
        System.out.println(count);


    }

    public static boolean haveToCleanZone(int y, int x) {
        for(int i=0; i<4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];

            if(check(nextY, nextX)) {
                if(map[nextY][nextX] == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean check(int y, int x) {
        if(y < 0 || x < 0 || y >= N || x >= M) {
            return false;
        }
        return true;
    }
}