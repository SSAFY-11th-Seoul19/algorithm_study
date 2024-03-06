import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, artScore;
    static int[][] image, groupMap;
    static boolean[][] visited;
    static List<Group> groupList;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        image = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                image[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        run();
        for (int i = 0; i < 3; i++) {
            turnImage();
            run();
        }

        System.out.println(artScore);
    }

    private static void run() {
        groupList = new ArrayList<>();
        groupMap = new int[N][N];
        visited = new boolean[N][N];
        findGroup();
        calcScore();
    }

    private static void findGroup() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }
                bfs(i, j, groupList.size());
            }
        }
    }

    public static void bfs(int r, int c, int groupNum) {
        Queue<Coord> queue = new ArrayDeque<>();
        queue.offer(new Coord(r, c));
        visited[r][c] = true;
        groupMap[r][c] = groupNum;
        Group currGroup = new Group(image[r][c]);
        groupList.add(currGroup);

        while (!queue.isEmpty()) {
            Coord target = queue.poll();

            // 그룹에 추가
            currGroup.elementList.add(target);
            currGroup.count++;

            for (int i = 0; i < 4; i++) {
                int nr = target.r + dr[i];
                int nc = target.c + dc[i];
                if (nr < 0 || N <= nr || nc < 0 || N <= nc) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                if (image[r][c] != image[nr][nc]) {
                    continue;
                }

                queue.offer(new Coord(nr, nc));
                visited[nr][nc] = true;
                groupMap[nr][nc] = groupNum;
            }
        }
    }

    private static void calcScore() {
        for (int group1No = 0; group1No < groupList.size(); group1No++) {

            int[] adjArr = new int[groupList.size()];
            Group group1 = groupList.get(group1No);
            for (Coord coord : group1.elementList) {
                for (int j = 0; j < 4; j++) {
                    int nr = coord.r + dr[j];
                    int nc = coord.c + dc[j];
                    if (nr < 0 || N <= nr || nc < 0 || N <= nc) {
                        continue;
                    }

                    int group2No = groupMap[nr][nc];
                    if (group1No < group2No) {
                        adjArr[group2No]++;
                    }
                }
            }

            for (int i = group1No+1; i < groupList.size(); i++) {
                if (adjArr[i] == 0) {
                    continue;
                }
                Group group2 = groupList.get(i);
                artScore += (group1.count + group2.count) * group1.value * group2.value * adjArr[i];
            }
        }
    }

    public static void turnImage() {
        int[][] turnedImage = new int[N][N];
        int mid = N>>1;
        turnCross(turnedImage, mid);
        turnFourSquare(turnedImage, mid);
        image = turnedImage;
    }
    public static void turnFourSquare(int[][] turnedImage, int len) {
        turnSquare(turnedImage, len, 0, 0);
        turnSquare(turnedImage, len, 0, len+1);
        turnSquare(turnedImage, len, len+1, 0);
        turnSquare(turnedImage, len, len+1, len+1);
    }
    private static void turnSquare(int[][] turnedImage, int len, int sr, int sc) {
        int fixRow = sr;
        int fixCol = sc+len-1;
        for (int r = sr; r < sr+len; r++) {
            int dr = r-sr;
            for (int c = sc; c < sc+len; c++) {
                int dc = c-sc;
                turnedImage[fixRow+dc][fixCol-dr] = image[r][c];
            }
        }
    }
    public static void turnCross(int[][] turnedImage, int mid) {
        for (int i = 0; i < N; i++) {
            turnedImage[mid][i] = image[i][mid];
        }
        for (int i = 0; i < N; i++) {
            turnedImage[N-1-i][mid] = image[mid][i];
        }
    }

    static class Group {
        int value, count;
        List<Coord> elementList;
        public Group(int value) {
            this.value = value;
            elementList = new ArrayList<>();
        }
    }
    static class Coord {
        int r, c;
        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
