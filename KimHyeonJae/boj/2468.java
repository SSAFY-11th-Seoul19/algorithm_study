import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    private static int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][n];
        int answer = 1;
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxHeight = Math.max(maxHeight, graph[i][j]);
            }
        }
        // 입력끝
        for (int height = 1; height < maxHeight; height++) {
            answer = Math.max(answer, countArea(graph, height));
        }
        System.out.println(answer);
    }

    private static final int countArea(int[][] graph, int height) {
        int length = graph.length;
        boolean[][] visited = new boolean[length][length];

        int result = 0;
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                if (graph[y][x] <= height || visited[y][x]) {
                    continue;
                }
                result += 1;

                LinkedList<int[]> que = new LinkedList<>();
                que.add(new int[]{y, x});
                visited[y][x] = true;
                while (!que.isEmpty()) {
                    int[] position = que.removeFirst();
                    for (int[] move : moves) {
                        int ny = position[0] + move[0];
                        int nx = position[1] + move[1];
                        if (ny < 0 || ny >= length || nx < 0 || nx >= length) {
                            continue;
                        }
                        if (graph[ny][nx] <= height || visited[ny][nx]) {
                            continue;
                        }
                        visited[ny][nx] = true;
                        que.add(new int[]{ny, nx});
                    }
                }
            }
        }
        return result;
    }
}