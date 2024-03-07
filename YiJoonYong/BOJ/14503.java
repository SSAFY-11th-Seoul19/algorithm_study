import java.io.*;

class Main {
    static int N, M;   // 방의 크기
    static char[][] room;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // ===초기화===
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        split = br.readLine().split(" ");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);
        int look = Integer.parseInt(split[2]);
        Robot robot = new Robot(row, col, look);

        room = new char[N][M];
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                room[i][j] = line.charAt(j<<1);
            }
        }
        // ===초기화 끝===

        robot.start();

        System.out.println(robot.cleanCount);
    }

    static class Robot {
        private int r, c;
        private int look;
        public int cleanCount = 0;
        public Robot(int r, int c, int look) {
            this.r = r;
            this.c = c;
            this.look = look;
        }

        public void start() {
            //로직 시작
            while (true) {
                // case1
                clean();

                // case2
                if (isAroundClean()) {
                    turn(2);
                    if (!move()) {
                        return;
                    }
                    turn(2);
                    continue;
                }

                // case3
                turn(3);
                if (isFrontClean(look)) {
                    continue;
                }
                move();
            }
        }

        private boolean move() {
            int nr = r + dr[look];
            int nc = c + dc[look];

            if (isWall(nr, nc)) {
                return false;
            }

            r = nr;
            c = nc;
            return true;
        }

        private boolean isAroundClean() {
            for (int i = 0; i < 4; i++) {
                boolean isDirty = !isFrontClean(i);
                if (isDirty) {
                    return false;
                }
            }
            return true;
        }

        private boolean isFrontClean(int look) {
            int nr = r + dr[look];
            int nc = c + dc[look];
            if (isWall(nr, nc)) {
                return true;
            }
            return room[nr][nc] != '0';
        }

        private void turn(int degree) {
            // degree->angle : 1->90, 2->180, 3->270, 4->360
            look = (look+degree)%4;
        }

        private boolean isWall(int nr, int nc) {
            return room[nr][nc] == '1';
        }

        private void clean() {
            if (room[r][c] == '0') {
                room[r][c] = '2';
                cleanCount++;
            }
        }
    }
}