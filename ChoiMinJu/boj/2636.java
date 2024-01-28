package ChoiMinJu.boj.boj2636;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static int N, M;
    public static int[][] board, visited;
    public static int[] dx = {0,-1,0,1};
    public static int[] dy = {1,0,-1,0};
    public static int area = 0; // 모두 녹기 한 시간 전 남아있는 칸

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken()); // 세로 13
        M = Integer.parseInt(stk.nextToken()); // 가로 12

        board = new int[N][M];
        visited = new int[N][M];

        for (int i=0; i<N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                board[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        // end input

        int time = 1; // 치즈가 모두 녹는데 걸리는 시간
        while (true) {
            area = 0;
            DFS(0, 0);
            if (check()) break;
            time++;
        }

        System.out.println(time);
        System.out.println(area);
    } // end main

    public static void DFS(int x, int y) {
        visited[x][y] = 1;
        System.out.println("(" + x + " " + y + ")");
        for (int w=0; w<4; w++) {
            int xx = x + dx[w];
            int yy = y + dy[w];
            if (xx<0 || xx>=N || yy<0 || yy>=M) continue;
            if (visited[xx][yy] == 1) continue;
            if (board[xx][yy] == 1) { // 치즈 모서리
                area++;
                board[xx][yy] = 0;
                visited[xx][yy] = 1;
            }
            if (board[xx][yy] == 0) {
                visited[xx][yy] = 1;
                DFS(xx, yy); // 계속 실행
            }
        }
    }

    public static void removeAll() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == 2) board[i][j] = 0;
            }
        }
    }

    public static void printBoard() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // board가 0으로만 이루어져 있는지 체크 -> 1이 하나라도 있으면 더..
    public static boolean check() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (board[i][j] == 1) return false;
            }
        }
        return true;
    }
}
