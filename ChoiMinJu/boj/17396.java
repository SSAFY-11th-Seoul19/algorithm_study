
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// boj 백도어
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static int N;
    static ArrayList<ArrayList<Node>> adj;
    static long[] dist; // N이 최대 100000, t이 최대 100000 이기 때문에 N * t = 10000000000 이므로 int 범위를 벗어난다. long으로
    static int[] sight;

    static class Node implements Comparable<Node> {
        int num;
        long weight;
        public Node(int num, long weight) {
            this.num = num;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node other) {
            if (this.weight > other.weight) return 1; // weight이 작을수록 우선순위가 높다
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        // 상대 시야에 있는 노드로 이어지는 edge는 나중에 가중치 10000으로 설정하자
        sight = new int[N];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sight[i] = Integer.parseInt(stk.nextToken());
        }

        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int time = Integer.parseInt(stk.nextToken());
            adj.get(start).add(new Node(end, time));
            adj.get(end).add(new Node(start, time)); // 연결은 양방향
        }

        dist = new long[N]; // 최단거리를 저장할 배열
        // 최소 거리 정보를 담을 배열을 초기화 한다.
        Arrays.fill(dist, Long.MAX_VALUE);

        // 출발 지점의 비용은 0으로 시작한다.
        dist[0] = 0;

        dijkstra(0);

        if (dist[N-1] != Long.MAX_VALUE) System.out.println(dist[N-1]);
        else System.out.println("-1");

    }

    static void dijkstra(int start) {
        PriorityQueue<Node> que = new PriorityQueue<>(); // 우선순위 큐
        boolean[] visited = new boolean[N];

        que.offer(new Node(start, 0));

        while (!que.isEmpty()) {

            Node cur = que.poll();

            if (visited[cur.num]) continue;
            visited[cur.num] = true;

            for (int i = 0; i < adj.get(cur.num).size(); i++) {
                Node next = adj.get(cur.num).get(i);
                if (next.num != N-1 && sight[next.num] == 1) continue; // 시야에 보이는 노드는 pass (넥서스는 제외)
                if (dist[next.num] > dist[cur.num] + next.weight) {
                    dist[next.num] = dist[cur.num] + next.weight;
                    que.offer(new Node(next.num, dist[next.num]));
                }
            }
        }
    }
}
