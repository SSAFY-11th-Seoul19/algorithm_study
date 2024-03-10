import java.util.*;

class Solution {
    final int MAX = 1_000_000;
    int N;
    int donut = 0;
    int stick = 0;
    int eight = 0;
    ArrayList<Integer>[] links;
    int[] indegree;
    int[] outdegree;
    boolean[] visited;
    int connectedIdx;
    
    public int[] solution(int[][] edges) {
        N = edges.length;
        links = new ArrayList[MAX + 1];
        indegree = new int[MAX + 1];
        outdegree = new int[MAX + 1];

        for (int i = 1; i <= MAX; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int from = edges[i][0];
            int to = edges[i][1];

            links[from].add(to);
            indegree[to]++;
            outdegree[from]++;
        }

        for (int i = 1; i <= MAX; i++) {
            if (indegree[i] == 0 && outdegree[i] >= 2) {
                connectedIdx = i;
                break;
            }
        }

        visited = new boolean[MAX + 1];
        for(int start:links[connectedIdx]){
            indegree[start]--;
            find(start);
        }

        return new int[]{connectedIdx, donut, stick, eight};
    }

    public void find(int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(start);

        int linkCnt = 0;
        int nodeCnt = 0;
        while (!dq.isEmpty()) {
            int now = dq.poll();

            nodeCnt++;
            visited[now] = true;
            for (int next : links[now]) {
                linkCnt++;

                if(!visited[next]){
                    dq.add(next);
                }
            }
        }
        
        if(nodeCnt == linkCnt){
            donut++;
            return;
        }
        
        if(nodeCnt-1 == linkCnt){
            stick++;
            return;
        }
        
        eight++;
    }
}

