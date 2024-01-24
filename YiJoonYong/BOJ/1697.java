import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static final int MAX = 100001;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        int[] timeArr = new int[MAX];
        boolean[] isVisited = new boolean[MAX];
        isVisited[N] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);

        int minTime = 0;
        while (!queue.isEmpty()) {
            int currPos = queue.poll();

            int xs = currPos - 1;   // -1
            int xp = currPos + 1;   // +1
            int xm = currPos * 2;   // *2
            int addTime = timeArr[currPos]+1;

            if (currPos == K) {
                minTime = timeArr[currPos];
                break;
            }

            if ((xs >= 0) && !isVisited[xs]) {
                queue.add(xs);
                isVisited[xs] = true;
                timeArr[xs] = addTime;
            }

            if ((xp < MAX) && !isVisited[xp]) {
                queue.add(xp);
                isVisited[xp] = true;
                timeArr[xp] = addTime;
            }

            if ((xm < MAX) && !isVisited[xm]) {
                queue.add(xm);
                isVisited[xm] = true;
                timeArr[xm] = addTime;
            }
        }

        System.out.println(minTime);
    }
}
