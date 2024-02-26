import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] graph;
    static int[][] groupGraph;
    static boolean[][] visited;
    static int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 그룹: 구역 크기 저장
    static Map<Integer, Integer> numberOfAreasMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int answer = 0;

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        visited = new boolean[n][n];
        groupGraph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int repeat = 0; repeat < 4; repeat++) {
            // 그룹을 정하고 그룹:개수 맵을 저장해준다.
            visited = new boolean[n][n];
            groupGraph = new int[n][n];
            int groupIdx = 0;
            numberOfAreasMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        getAreaNumbers(i, j, groupIdx);
                        groupIdx += 1;
                    }
                }
            }

            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        answer += getAnswer(i, j);
                    }
                }
            }
            rotate();
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void rotate() {
        int size = n / 2;
        int[][] startPlus = new int[][]{{0, 0}, {0, size + 1}, {size + 1, 0}, {size + 1, size + 1}};
        for (int[] sp : startPlus) {
            rotate90(sp[0], sp[1], size);
        }
        rotateCross();
    }

    private static void rotate90(int sy, int sx, int size) {
        int[][] copy = new int[size][size];
        for (int y = sy; y < sy + size; y++) {
            for (int x = sx; x < sx + size; x++) {
                copy[y - sy][x - sx] = graph[y][x];
            }
        }
        int[][] copiedCopy = new int[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                copiedCopy[c][size - r - 1] = copy[r][c];
            }
        }

        for (int y = sy; y < sy + size; y++) {
            for (int x = sx; x < sx + size; x++) {
                graph[y][x] = copiedCopy[y - sy][x - sx];
            }
        }
    }

    private static void rotateCross() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = graph[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            copy[i][n / 2] = graph[n / 2][n - 1 - i];
        }
        for (int i = 0; i < n; i++) {
            copy[n / 2][i] = graph[i][n / 2];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n / 2 || j == n / 2) {
                    graph[i][j] = copy[i][j];
                }
            }
        }
    }

    private static void getAreaNumbers(int sy, int sx, int groupIdx) {
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{sy, sx});
        visited[sy][sx] = true;
        groupGraph[sy][sx] = groupIdx;
        int kind = graph[sy][sx];
        int numberOfArea = 1;
        while (!que.isEmpty()) {
            int[] pos = que.removeFirst();
            for (int[] move : moves) {
                int ny = pos[0] + move[0];
                int nx = pos[1] + move[1];
                // 범위에 속하지 않으면 넘어가
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                    continue;
                }
                // 방문했거나 색깔이 다르면
                if (visited[ny][nx] || graph[ny][nx] != kind) {
                    continue;
                }
                // 색깔이 같다면 계속해서 확장
                numberOfArea += 1;
                visited[ny][nx] = true;
                groupGraph[ny][nx] = groupIdx;
                que.add(new int[]{ny, nx});
            }
        }
        numberOfAreasMap.put(groupIdx, numberOfArea);
    }

    private static int getAnswer(int sy, int sx) {
        Map<Integer, Integer> touches = new HashMap<>();
        Map<Integer, Integer> groupKinds = new HashMap<>();

        visited[sy][sx] = true;
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.add(new int[]{sy, sx});
        int curGroup = groupGraph[sy][sx];
        int kind = graph[sy][sx];
        int answer = 0;

        while (!que.isEmpty()) {
            int[] pos = que.removeFirst();
            for (int[] move : moves) {
                int ny = pos[0] + move[0];
                int nx = pos[1] + move[1];
                // 범위에 속하지 않으면 넘어가
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
                    continue;
                }
                int newGroup = groupGraph[ny][nx];
                // 방문했고 같은 곳이면 넘어가기
                if (visited[ny][nx] && newGroup == curGroup) {
                    continue;
                }
                if (visited[ny][nx] && newGroup != curGroup) {
                    touches.put(newGroup, touches.getOrDefault(newGroup, 0) + 1);
                    groupKinds.put(newGroup, graph[ny][nx]);
                    continue;
                }
                if (newGroup != curGroup) {
                    continue;
                }
                visited[ny][nx] = true;
                que.add(new int[]{ny, nx});
            }
        }

        for (int touchGroup : touches.keySet()) {
            int result = (numberOfAreasMap.get(curGroup) + numberOfAreasMap.get(touchGroup))
                    * kind
                    * groupKinds.get(touchGroup)
                    * touches.get(touchGroup);
            answer += result;
        }
        return answer;
    }
}
