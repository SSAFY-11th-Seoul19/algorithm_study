import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
class Main {
    static final short[] dx = {-1, 1, 0, 0};
    static final short[] dy = {0, 0, -1, 1};
    static short N;
    static short[][] land;
    static short[][] visitedInfo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Short.parseShort(br.readLine());
        land = new short[N][N];

        int minHeight = 100;
        int maxHeight = 1;
        for (short i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (short j = 0; j < N; j++) {
                land[i][j] =  Short.parseShort(line[j]);
                minHeight = Math.min(minHeight, land[i][j]);
                maxHeight = Math.max(maxHeight, land[i][j]);
            }
        }

        Queue<Point> queue = new ArrayDeque<>();
        visitedInfo = new short[N][N];
        int maxAreaCount = 1;
        for (short rain = (short)minHeight; rain < maxHeight; rain++) {
            int areaCount = 0;
            // find safeArea starting point
            for (short i = 0; i < N; i++) {
                for (short j = 0; j < N; j++) {
                    if (isVisited(i, j, rain)) {
                        continue;
                    }

                    areaCount++;
                    findCurrentArea(queue, new Point(i, j), rain);
                }
            }
            maxAreaCount = Math.max(maxAreaCount, areaCount);
        }

        System.out.println(maxAreaCount);
    }

    private static void findCurrentArea(Queue<Point> queue, Point startPoint, short rain) {
        queue.add(startPoint);
        visitedInfo[startPoint.x][startPoint.y] = rain;

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for (short d = 0; d < 4; d++) {
                Point np = new Point(p.x + dx[d],p.y + dy[d]);

                if (!isSafePoint(np, rain)) {
                    continue;
                }

                visitedInfo[np.x][np.y] = rain;
                queue.add(np);
            }
        }
    }

    private static boolean isVisited(short r, short c, short rain) {
        return land[r][c] <= rain || visitedInfo[r][c] == rain;
    }

    private static boolean isSafePoint(Point np, short rain) {
        if (np.x < 0 || N <= np.x) {
            return false;
        }
        if (np.y < 0 || N <= np.y) {
            return false;
        }
        if (isVisited(np.x, np.y, rain)) {
            return false;
        }
        return true;
    }

    static class Point {
        short x, y;
        public Point(short x, short y) {
            this.x = x;
            this.y = y;
        }
        public Point(int x, int y) {
            this.x = (short) x;
            this.y = (short) y;
        }
    }
}