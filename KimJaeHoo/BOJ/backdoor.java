import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    static int N, M, a, b, t, totalVisited, curIdx, nextIdx;
    static long curTime, nextTime;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PriorityQueue<Node> pq;
    static ArrayDeque<Node> graph[];
    static long[] shortest;
    static boolean[] visited, detected;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        shortest = new long[N]; //  0번 노드에서 i번 노드까지의 최단거리 표
        detected = new boolean[N]; // 각각의 노드들이 적의 시야에 보이는지에 대한 여부
        graph = new ArrayDeque[N]; // 인접리스트로 구현한 그래프
        pq = new PriorityQueue<>(); // 우선순위 큐 사용 예정
        st= new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            graph[i] = new ArrayDeque<Node>(); // 겸사겸사 그래프 초기화
            shortest[i] = Long.MAX_VALUE; // 최단가리를 맨 처음에 무한으로 전부 초기화
            detected[i] = Integer.parseInt(st.nextToken()) == 1 ? true : false; // 시야정보 입력받기
        }
        for(int i = 0 ;i < M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            if((detected[b] && b < N-1) || (detected[a] && a < N-1)) // 만약 목적지가 아닌데 적의 시야에 보인다면 탐색에서 완전히 배제
                continue;
            graph[a].add(new Node(t, b)); // 양방향 그래프 이므로
            graph[b].add(new Node(t, a));
        }

        pq.add(new Node(0, 0)); // 시작점부터 노드 시작
        shortest[0] = 0; // 우선 처음 노드의 최단거리는 0으로 놓고 시작한다.
        while(!pq.isEmpty()){ // 우선순위큐에 값이 없을 때까지 반복한다.
            Node curNode = pq.poll(); // 현재 노드. 가장 최단 시간 정보를 갖고 있는 노드
            curTime = curNode.x; // 현재노드까지의 최단시간
            curIdx = curNode.y; // 현재노드의 번호

            // 만약 현재노드까지의 최단시간이 이미 기록된 최단시간보다 더 큰 경우 이미 더 작은 최단시간 노드가 우선순위큐 위에서 뽑혀서 작업을 맞췄다는 뜻.
            if(curTime > shortest[curIdx])
                continue;

            // 여태까지 나온 노드중에서 가장 최단시간인 경우 해당 노드의 인접노드들 찾는다.
            for(Node node : graph[curIdx]){
                nextTime = node.x;
                nextIdx = node.y;

                // 만약 현재노드를 통해 가능 시간이 기존의 최단거리표보다 더 짧다면 인접 노드의 최단거리 표를 업데이트하고 큐에 집어넣는다.
                if(nextTime + shortest[curIdx] < shortest[nextIdx]){
                    shortest[nextIdx] = nextTime + shortest[curIdx];
                    pq.add(new Node(shortest[nextIdx], nextIdx));
                }
            }
        }

        // 모든 작업이 완료됐다면 마지막 노드의 최단거리를 체크한다. 그런데 최초의 최대값이 그대로 있다면 -1을 출력
        if(shortest[N-1]== Long.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(shortest[N-1]);
    }

    // long값을 입력받도록 커스텀 클래스 구현
    static class Node implements Comparable<Node>{
        public long x;
        public int  y;
        public Node(long x, int y){
            this.x = x;
            this.y = y;
        }
        // 비교연산자 오버라이딩
        @Override
        public int compareTo(Node o) {
            if(this.x > o.x)
                return 1;
            else
                return -1;
        }

    }
}
