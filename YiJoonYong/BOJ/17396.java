import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br;
    static final long INF = 10_000_000_001L;
    static ArrayList<Node>[] nodeInfo;
    static long[] dist;
    static boolean[] isMovable;
    static int N, M;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]); // 노드 수
        M = Integer.parseInt(split[1]); // 간선 수

        initIsMovable();
        initNodeINfo();
        initDist();

        dijkstra(0);

        System.out.println((dist[N-1] == INF) ? -1 : dist[N-1]);
    }
    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.w));
        dist[start] = 0;
        pq.add(new Node(start, dist[start]));

        while (!pq.isEmpty()) {
            Node next = pq.poll();

            // 기존 경로가 더 작다면 탐색이 의미 없음
            if (dist[next.v] < next.w) {
                continue;
            }

            for (Node n : nodeInfo[next.v]) {
                if (!isMovable[next.v]) {
                    continue;
                }

                long nDist = dist[next.v] + n.w;
                if (nDist < dist[n.v]) {
                    dist[n.v] = nDist;
                    pq.add(new Node(n.v, nDist));
                }
            }
        }
    }
    private static void initDist() {
        dist = new long[N];
        Arrays.fill(dist, INF);
    }

    private static void initNodeINfo() throws IOException {
        nodeInfo = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            nodeInfo[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            String[] split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            int t = Integer.parseInt(split[2]);

            nodeInfo[a].add(new Node(b, t));
            nodeInfo[b].add(new Node(a, t));
        }
    }

    private static void initIsMovable() throws IOException {
        isMovable = new boolean[N];
        String line = br.readLine();
        for (int i = 0; i < N; i++) {
            isMovable[i] = line.charAt(i << 1) == '0';
        }
        isMovable[N-1] = true;  // 넥서스는 움직일 수 있음
    }

    static class Node {
        int v;
        long w;
        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }
}
