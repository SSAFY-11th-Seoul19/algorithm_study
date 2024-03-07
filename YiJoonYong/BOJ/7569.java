import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static int M, N, H;

    //               위층, 아래층, 뒤, 앞, 왼쪽, 오른쪽
    static int[] dh = {1,   -1,   0, 0,   0,  0};
    static int[] dr = {0,    0,  -1, 1,   0,  0};
    static int[] dc = {0,    0,   0, 0,  -1,  1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        H = Integer.parseInt(split[2]); // 층
        N = Integer.parseInt(split[1]); // 행
        M = Integer.parseInt(split[0]); // 열

        boolean[][][] isVisited = new boolean[H][N][M];
        Queue<int[]> queue = new LinkedList<>();
        init(isVisited, queue, br);

        int day = -1;
        int tomatoCount = queue.size();
        while (!queue.isEmpty()) {
            // 산 사이클 : 전날 익었던 토마토를 몽땅 꺼냄 -> 인접한 토마토를 몽땅 큐에 넣음
            int cycle = tomatoCount;
            tomatoCount = doBfsCycle(cycle, queue, isVisited);
            day++;
        }
        if (findLeftoverTomato(isVisited))
            day = -1;
        System.out.println(day);
    }

    private static int doBfsCycle(int cycle, Queue<int[]> queue, boolean[][][] isVisited) {
        int tomatoCount = 0;
        for (int c = 0; c < cycle; c++) {
            int[] target = queue.poll();

            for (int i = 0; i < 6; i++) {
                int hei = target[0] + dh[i];
                int row = target[1] + dr[i];
                int col = target[2] + dc[i];

                if (!checkBoundary(hei, row, col)) // 경계 체크
                    continue;
                if (isVisited[hei][row][col])   // 이미 방문했는지 체크
                    continue;

                isVisited[hei][row][col] = true;
                queue.add(new int[]{hei, row, col});
                tomatoCount++;
            }
        }
        return tomatoCount;
    }

    public static boolean findLeftoverTomato(boolean[][][] isVisited) {
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N ; n++) {
                for (int m = 0; m < M; m++) {
                    if (!isVisited[h][n][m]) return true;
                }
            }
        }
        return false;
    }
    public static boolean checkBoundary(int h, int r, int c) {
        if (h < 0 || H <= h) return false;
        if (r < 0 || N <= r) return false;
        if (c < 0 || M <= c) return false;
        return true;
    }
    public static void init(boolean[][][] isVisited, Queue<int[]> queue, BufferedReader br) throws IOException {
        // 초기화 및 큐 세팅
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N ; n++) {
                String[] split = br.readLine().split(" ");
                for (int m = 0; m < M; m++) {
                    int tomato = Integer.parseInt(split[m]);

                    if (tomato != 0) {
                        if (tomato == 1)    // 익은 토마토는 좌표를 큐에 넣음
                            queue.add(new int[]{h, n, m});
                        isVisited[h][n][m] = true;  // 익은 토마토 & 빈 공간 방문 차단
                    }
                }
            }
        }
    }
}
