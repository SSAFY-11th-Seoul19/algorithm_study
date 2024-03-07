import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] moves = new int[][]{{-1, 0},{0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 0이 청소 x 빈 칸, 1인 경우 벽이 있다.
        boolean[][] visited = new boolean[n][m];
        int answer = 0;
        while (true) {
            // 청소 안한 곳이면 청소해
            if (!visited[y][x]) {
                visited[y][x] = true;
                answer += 1;
            }
            boolean hasNotClean = false;
            for (int[] move: moves) {
                int ny = move[0] + y;
                int nx = move[1] + x;
                // 칸을 넘어가면 pass
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                    continue;
                }
                // 벽이라면 넘어가
                if (graph[ny][nx] == 1) {
                    continue;
                }
                // 벽이 아니고 방문하지 않았다면
                // 청소 안한 경우
                if (graph[ny][nx] == 0 && !visited[ny][nx]) {
                    hasNotClean = true;
                    break;
                }
            }
            // 청소 안한 칸이 있는 경우
            if (hasNotClean) {
                d -= 1;
                d = (d + 4) % 4;
                int ny = y + moves[d][0];
                int nx = x + moves[d][1];
                // 갈 수 없는 곳
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                    continue;
                }
                // 청소되지 않은 빈칸인경우
                if (graph[ny][nx] == 0 && !visited[ny][nx]) {
                    y = ny;
                    x = nx;
                }
                continue;
            }
            // 청소 안한 칸이 없는 경우
            int ny = y - moves[d][0];
            int nx = x - moves[d][1];

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                break;
            }

            if (graph[ny][nx] == 1) {
                break;
            }
            y = ny;
            x = nx;

        }

        System.out.println(answer);
    }
}