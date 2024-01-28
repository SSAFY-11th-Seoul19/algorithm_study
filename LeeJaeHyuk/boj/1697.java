import java.io.*;
import java.util.*;

public class Study1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int MAX = 100_000;
        int[] position = new int[MAX+1];
        Arrays.fill(position, Integer.MAX_VALUE);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        position[N] = 0;

        while(!queue.isEmpty()) {
            int present = queue.poll();

            if(present == M) break;

            int[] next = new int[3];
            next[0] = present + 1;
            next[1] = present - 1;
            next[2] = present * 2;

            for(int nextMove : next) {
                if(nextMove < 0 || nextMove > MAX) continue;

                if(position[nextMove] > position[present] + 1) {
                    position[nextMove] = position[present] + 1;
                    queue.add(nextMove);
                }
            }
        }

        System.out.println(position[M]);
    }
}