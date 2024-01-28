import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertical = Integer.parseInt(st.nextToken()), horizontal = Integer.parseInt(st.nextToken());
        int[][] graph = new int[vertical][horizontal];

        for (int v = 0; v <vertical; v++) {
            st = new StringTokenizer(br.readLine());
            for (int h = 0; h < horizontal; h++) {
                graph[v][h] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0, prevCheese = 0;
        int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        LinkedList<int[]> que = new LinkedList<>();
        LinkedList<int[]> melted = new LinkedList<>();
        melted.add(new int[]{0, 0});
        boolean[][] visited = new boolean[vertical][horizontal];
        visited[0][0] = true;
        while (true) {
            que = melted;
            melted = new LinkedList<>();;
            int airSize = que.size(); 
            while(!que.isEmpty()) {
                int[] cPos = que.removeFirst();
                for (int[] move: moves) {
                    int ny = move[0] + cPos[0], nx = move[1] + cPos[1];
                    if (ny < 0 || ny >= vertical || nx < 0 || nx >= horizontal) {
                        continue;
                    }
                    if (visited[ny][nx]) {
                        continue;
                    }
                    visited[ny][nx] = true;
                    // 치즈가 있는 부분일 시
                    if (graph[ny][nx] == 1) {
                        melted.add(new int[]{ny, nx});
                        continue;
                    }
                    que.add(new int[]{ny, nx});
                }
            }

            // 녹을 치즈가 없는 경우
            if (melted.size() == 0) {
                System.out.println(time);
                System.out.println(prevCheese);
                return;
            }
            time += 1;
            prevCheese = melted.size();
        }
    }
}
