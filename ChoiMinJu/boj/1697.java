import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static int MAX_SIZE = 100_000;
    static int N, K;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        dist = new int[MAX_SIZE+1]; // 입력 최댓값
        Arrays.fill(dist, -1); // 방문여부 및 카운트 체크용
        bfs();
        System.out.println(dist[K]);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(N); // 수빈이의 위치부터 시작
        dist[N] = 0; // 방문 처리

        while (!q.isEmpty()) {
            if (dist[K] != -1) break; // 동생을 잡았다
            int cur = q.poll();
            if (cur-1 >= 0 && dist[cur-1] == -1) {
                q.add(cur-1);
                dist[cur-1] = dist[cur] + 1; // 1초 후
            }
            if (cur+1 <= MAX_SIZE && dist[cur+1] == -1) {
                q.add(cur+1);
                dist[cur+1] = dist[cur] + 1;
            }
            if (cur*2 <= MAX_SIZE && dist[cur*2] == -1) {
                q.add(2*cur);
                dist[2*cur] = dist[cur] + 1;
            }
        }

    }
}
