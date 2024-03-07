import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);

        boolean[] isVisited = new boolean[100_001];
        isVisited[N] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N, 0});

        while (!queue.isEmpty()) {
            int[] X = queue.poll();
            if (X[0] == K) {
                System.out.println(X[1]);
                return;
            }

            int way1 = X[0]-1;
            int way2 = X[0]+1;
            int way3 = X[0]*2;
            int[] way = {way1, way2, way3};

            for (int w : way) {
                if (w < 0 || 100_000 < w || isVisited[w]) {
                    continue;
                }

                queue.add(new int[]{w, X[1]+1});
                isVisited[w] = true;
            }
        }
    }
}
