import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    private static int[][] moves = new int[][]{
            {0, 0, 1}, {0, 0, -1},
            {0, 1, 0}, {0, -1, 0},
            {1, 0, 0}, {-1, 0, 0}};
    private static int vertical, horizontal, height;

    public static void main(String[] args) throws IOException {
        int[][][] graph = inputGraph();
        // 1은 익은거 , 0은 안익은 거, -1은 아무것도 없는 거
        int ripe = 0;
        int unRipe = 0;
        int answer = 0;
        // {h, y, x, time}
        LinkedList<int[]> que = new LinkedList<>();
        for (int h = 0; h < height; h++) {
            for (int y = 0; y < vertical; y++) {
                for (int x = 0; x < horizontal; x++) {
                    if (graph[h][y][x] == 1) {
                        que.add(new int[]{h, y, x, 0});
                        ripe += 1;
                    }
                    if (graph[h][y][x] == 0) {
                        unRipe += 1;
                    }
                }
            }
        }
        if (unRipe == 0) {
            System.out.println(0);
            return;
        }
        if (ripe == 0 && unRipe > 0) {
            System.out.println(-1);
            return;
        }

        while (!que.isEmpty()) {
            int[] node = que.removeFirst();
            for (int[] move: moves) {
                int nh = move[0] + node[0];
                int ny = move[1] + node[1];
                int nx = move[2] + node[2];
                int newTime = node[3] + 1;
                if (nh < 0 || nh >= height || ny < 0 || ny >= vertical || nx < 0 || nx >= horizontal) {
                    continue;
                }
                if (graph[nh][ny][nx] == 1 || graph[nh][ny][nx] == -1) {
                    continue;
                }
                answer = Math.max(answer, newTime);
                graph[nh][ny][nx] = 1;
                unRipe -= 1;
                que.add(new int[]{nh, ny, nx, newTime});
            }
        }

        if (unRipe == 0) {
            System.out.println(answer);
            return;
        }
        System.out.println(-1);
    }

    private static int[][][] inputGraph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        horizontal = inputs[0];
        vertical = inputs[1];
        height = inputs[2];
        int[][][] graph = new int[height][vertical][horizontal];
        for (int h = 0; h < height; h++) {
            for (int v = 0; v < vertical; v++) {
                graph[h][v] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }
        return graph;
    }
}