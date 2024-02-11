import java.io.*;
import java.util.ArrayList;

public class Main {
    static final int ROCK = -1, RED_BOMB = 0, EMPTY = -2;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] isVisited;
    static int score, n, m;
    static BombGroup targetBombGroup, candiBombGroup;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        map = new int[n][n];

        // -1, 0, m이하의 숫자로만 이루어진 격자가 주어짐
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        while (true) {
            isVisited = new boolean[n][n];
            targetBombGroup = new BombGroup();

            // 크기가 가장 큰 폭탄 묶음 찾기
            findLargestBombGroup();
            if (targetBombGroup.normalList.size() + targetBombGroup.redList.size() < 2) {
                break;
            }

            clearBomb();
            applyGravity();
            turnMap();
            applyGravity();

            // 점수 산정
            int bombGroupSize = targetBombGroup.normalList.size() + targetBombGroup.redList.size();
            score += bombGroupSize*bombGroupSize;
        }

        System.out.println(score);
    }

    private static void turnMap() {
        int[][] turnedMap = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = n-1; c >= 0; c--) {
                turnedMap[n-1-c][r] = map[r][c];
            }
        }
        map = turnedMap;
    }

    private static void applyGravity() {
        for (int c = 0; c < n; c++) {
            int emptyCount = 0; // counting empty space
            for (int r = n-1; r >= 0; r--) {
                if (map[r][c] == EMPTY) {
                    emptyCount++;
                    continue;
                }
                if (map[r][c] == ROCK ) {
                    emptyCount = 0;
                    continue;
                }
                if (emptyCount > 0) {   // 빈 공간이 있을 경우에만 하강
                    map[r+emptyCount][c] = map[r][c];
                    map[r][c] = EMPTY;
                }
            }
        }
    }

    private static void findLargestBombGroup() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isVisited[i][j]) {  // 이미 그룹으로 찾아졌다면 pass
                    continue;
                }
                if (map[i][j] == RED_BOMB || map[i][j] == ROCK || map[i][j] == EMPTY) {  // 붉은 폭탄 or 돌이면 초기 탐색 pass
                    continue;
                }

                // find candidate bomb group
                candiBombGroup = new BombGroup();
                findBombGroup(i, j, map[i][j]);

                // reset red bomb visited info
                for (Coord coord : candiBombGroup.redList) {
                    isVisited[coord.r][coord.c] = false;
                }

                // 그룹 크기가 2 미만이면 그룹이 아님 -> continue
                int candiGroupSize = candiBombGroup.normalList.size() + candiBombGroup.redList.size();
                if (candiGroupSize <= 1) {
                    continue;
                }

                // calc largest bomb group
                compareCandidate2TargetGroup(candiGroupSize);
            }
        }
    }

    private static void compareCandidate2TargetGroup(int candiGroupSize) {
        int targetGroupSize = targetBombGroup.normalList.size() + targetBombGroup.redList.size();
        if (candiGroupSize < targetGroupSize) { // 기존 타겟 그룹이 더 클 경우 변화 없음
            return;
        }
        if (targetGroupSize < candiGroupSize) {
            targetBombGroup = candiBombGroup;
            return;
        }

        // if targetGroupSize == candiGroupSize
        // then compare red bomb size
        if (candiBombGroup.redList.size() > targetBombGroup.redList.size()) {
            return;
        }
        if (candiBombGroup.redList.size() < targetBombGroup.redList.size()) {
            targetBombGroup = candiBombGroup;
            return;
        }

        // if targetRedSize == candiRedSize
        // then compare max row
        if (targetBombGroup.controlPoint.r > candiBombGroup.controlPoint.r) {
            return;
        }
        if (targetBombGroup.controlPoint.r < candiBombGroup.controlPoint.r) {
            targetBombGroup = candiBombGroup;
            return;
        }

        // if targetMaxRow == candiMaxRow
        // then compare min col
        if (targetBombGroup.controlPoint.c < candiBombGroup.controlPoint.c) {
            return;
        }
        if (targetBombGroup.controlPoint.c > candiBombGroup.controlPoint.c) {
            targetBombGroup = candiBombGroup;
        }
    }

    public static void findBombGroup(int r, int c, int color) {
        isVisited[r][c] = true;

        if (map[r][c] == RED_BOMB) {
            candiBombGroup.redList.add(new Coord(r, c));
        }
        else {
            candiBombGroup.normalList.add(new Coord(r, c));

            // calc control point
            if (candiBombGroup.controlPoint.r < r) {
                candiBombGroup.controlPoint.r = r;
                candiBombGroup.controlPoint.c = c;
            }
            else if (candiBombGroup.controlPoint.r == r) {
                if (candiBombGroup.controlPoint.c > c) {
                    candiBombGroup.controlPoint.c = c;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int nr = r+dr[i];
            int nc = c+dc[i];

            if (isOutOfBoundary(nr, nc) || isVisited[nr][nc]) {
                continue;
            }
            if (map[nr][nc] != RED_BOMB && map[nr][nc] != color) {
                continue;
            }
            if (map[nr][nc] == ROCK || map[nr][nc] == EMPTY) {
                isVisited[nr][nc] = true;
                continue;
            }

            findBombGroup(r+dr[i], c+dc[i], color);
        }
    }

    private static void clearBomb() {
        for (Coord coord : targetBombGroup.normalList) {
            map[coord.r][coord.c] = EMPTY;
        }
        for (Coord coord : targetBombGroup.redList) {
            map[coord.r][coord.c] = EMPTY;
        }
    }

    private static boolean isOutOfBoundary(int nr, int nc) {
        return nr < 0 || n <= nr || nc < 0 || n <= nc;
    }

    static class Coord {
        int r, c;
        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class BombGroup {
        ArrayList<Coord> normalList;  // 일반 폭탄 좌표
        ArrayList<Coord> redList;  // 빨강 폭탄 좌표
        Coord controlPoint; // 기준점
        public BombGroup() {
            this.normalList = new ArrayList<>();
            this.redList = new ArrayList<>();
            this.controlPoint = new Coord(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }
}