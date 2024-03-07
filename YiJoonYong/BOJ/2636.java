import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);

        int[][] board = new int[row][col];
        for (int i = 0; i < row; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < col; j++) {
                board[i][j] = split[j].charAt(0) - '0';
            }
        }

        int leftCheese = 0;
        int time = 0;
        while (true) {
            int cheeseCount = meltingCheese(board, row, col);
            if (cheeseCount == 0)
                break;
            leftCheese = cheeseCount;
            time++;
        }
        System.out.println(time);
        System.out.println(leftCheese);
    }

    private static int meltingCheese(int[][] board, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        boolean[][] isVisited = new boolean[row][col];
        isVisited[0][0] = true;

        int cheeseCount = 0;
        while(!queue.isEmpty()) {
            int[] target = queue.poll();

            for (int i = 0; i < 4; i++) {
                int r = target[0] + dr[i];
                int c = target[1] + dc[i];
                if (r < 0 || row <= r)
                    continue;
                if (c < 0 || col <= c)
                    continue;
                if (isVisited[r][c])
                    continue;

                isVisited[r][c] = true;
                if (board[r][c] == 1) { // melting cheese check
                    board[r][c] = 0;
                    cheeseCount++;
                    continue;
                }
                queue.add(new int[]{r, c});
            }
        }
        return cheeseCount;
    }
}